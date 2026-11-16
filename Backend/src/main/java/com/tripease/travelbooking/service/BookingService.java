package com.tripease.travelbooking.service;

import com.tripease.travelbooking.dto.BookingRequest;
import com.tripease.travelbooking.entity.Booking;
import com.tripease.travelbooking.entity.Cancellation;
import com.tripease.travelbooking.entity.Payment;
import com.tripease.travelbooking.entity.Ticket;
import com.tripease.travelbooking.exception.ResourceNotFoundException;
import com.tripease.travelbooking.repository.BookingRepository;
import com.tripease.travelbooking.repository.CancellationRepository;
import com.tripease.travelbooking.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;
    private final CancellationRepository cancellationRepository;
    private final TicketService ticketService;
    private final Random random = new Random();

    public BookingService(BookingRepository bookingRepository,
                          PaymentRepository paymentRepository,
                          CancellationRepository cancellationRepository,
                          TicketService ticketService) {
        this.bookingRepository = bookingRepository;
        this.paymentRepository = paymentRepository;
        this.cancellationRepository = cancellationRepository;
        this.ticketService = ticketService;
    }

    public synchronized String generateUniqueBookingId() {
        int year = LocalDate.now().getYear();
        String bookingId;
        do {
            int randomCode = 1000 + random.nextInt(9000);
            bookingId = "TE-" + year + "-" + randomCode;
        } while (bookingRepository.existsById(bookingId));
        return bookingId;
    }

    @Transactional
    public Booking createBooking(BookingRequest req) {
        String bookingId = generateUniqueBookingId();

        Booking booking = new Booking();
        booking.setId(bookingId);
        booking.setUserId(req.getUserId());
        booking.setPackageId(req.getPackageId());
        booking.setBookingType(req.getBookingType() != null ? req.getBookingType() : "PACKAGE");
        booking.setPackageTitle(req.getPackageTitle());
        booking.setDestination(req.getDestination());
        booking.setImage(req.getImage());
        booking.setTravelDate(req.getTravelDate());

        // Calculate return date if omitted (default + 5 days)
        if (req.getReturnDate() != null && !req.getReturnDate().isBlank()) {
            booking.setReturnDate(req.getReturnDate());
        } else {
            try {
                LocalDate start = LocalDate.parse(req.getTravelDate());
                booking.setReturnDate(start.plusDays(5).toString());
            } catch (Exception e) {
                booking.setReturnDate(req.getTravelDate());
            }
        }

        booking.setTravelers(req.getTravelers() != null ? req.getTravelers() : 2);
        booking.setGuestName(req.getGuestName());
        booking.setGuestEmail(req.getGuestEmail());
        booking.setGuestPhone(req.getGuestPhone());
        booking.setDepartureCity(req.getDepartureCity());
        booking.setSpecialRequests(req.getSpecialRequests());
        booking.setTotalAmount(req.getTotalAmount());
        booking.setDiscountApplied(req.getDiscountApplied() != null ? req.getDiscountApplied() : 0.0);
        booking.setStatus("Upcoming");
        booking.setPaymentStatus("Paid");
        booking.setPaymentMethod(req.getPaymentMethod() != null ? req.getPaymentMethod() : "UPI / Net Banking");
        booking.setCreatedAt(LocalDate.now().toString());

        Booking savedBooking = bookingRepository.save(booking);

        // Record Payment
        String txnRef = "TXN-" + System.currentTimeMillis();
        Payment payment = new Payment(
                bookingId,
                savedBooking.getPaymentMethod(),
                txnRef,
                savedBooking.getTotalAmount(),
                "SUCCESS"
        );
        paymentRepository.save(payment);

        // Automatically Generate Confirmed Travel Ticket
        Ticket ticket = ticketService.generateTicketForBooking(savedBooking);
        savedBooking.setTicketId(ticket.getTicketId());
        savedBooking = bookingRepository.save(savedBooking);

        return savedBooking;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAllByOrderByCreatedAtDesc();
    }

    public List<Booking> getBookingsByEmail(String email) {
        return bookingRepository.findByGuestEmailOrderByCreatedAtDesc(email);
    }

    public List<Booking> getBookingsByIdentifier(String identifier) {
        if (identifier == null || identifier.isBlank()) {
            return bookingRepository.findAllByOrderByCreatedAtDesc();
        }
        String clean = identifier.trim();
        List<Booking> byEmail = bookingRepository.findByGuestEmailOrderByCreatedAtDesc(clean);
        if (!byEmail.isEmpty()) return byEmail;
        return bookingRepository.findAllByOrderByCreatedAtDesc().stream()
                .filter(b -> (b.getGuestPhone() != null && b.getGuestPhone().contains(clean)) ||
                             (b.getGuestEmail() != null && b.getGuestEmail().equalsIgnoreCase(clean)))
                .toList();
    }

    public Booking getBookingById(String id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with ID: " + id));
    }

    @Transactional
    public Booking updateBookingStatus(String id, String newStatus) {
        Booking booking = getBookingById(id);
        booking.setStatus(newStatus);
        if ("Cancelled".equalsIgnoreCase(newStatus)) {
            booking.setPaymentStatus("Refund Initiated");
            List<Payment> payments = paymentRepository.findByBookingId(id);
            for (Payment p : payments) {
                p.setStatus("REFUNDED");
                paymentRepository.save(p);
            }
            cancellationRepository.save(new Cancellation(id, "Cancelled by user / admin", booking.getTotalAmount(), "Refund Initiated"));
        }
        return bookingRepository.save(booking);
    }

    @Transactional
    public Booking cancelBooking(String id) {
        return cancelBooking(id, "Customer request via My Trips");
    }

    @Transactional
    public Booking cancelBooking(String id, String reason) {
        Booking booking = getBookingById(id);
        booking.setStatus("Cancelled");
        booking.setPaymentStatus("Refund Initiated");

        List<Payment> payments = paymentRepository.findByBookingId(id);
        for (Payment p : payments) {
            p.setStatus("REFUNDED");
            paymentRepository.save(p);
        }

        String cancelReason = (reason != null && !reason.isBlank()) ? reason : "Customer request via My Trips";
        cancellationRepository.save(new Cancellation(id, cancelReason, booking.getTotalAmount(), "Refund Initiated"));

        return bookingRepository.save(booking);
    }

    @Transactional
    public void deleteBooking(String id) {
        Booking booking = getBookingById(id);
        List<Payment> payments = paymentRepository.findByBookingId(id);
        paymentRepository.deleteAll(payments);
        cancellationRepository.findByBookingId(id).ifPresent(cancellationRepository::delete);
        bookingRepository.delete(booking);
    }

    public List<Payment> getPaymentsForBooking(String bookingId) {
        return paymentRepository.findByBookingId(bookingId);
    }

    public List<Cancellation> getAllCancellations() {
        return cancellationRepository.findAllByOrderByCancelledAtDesc();
    }
}
