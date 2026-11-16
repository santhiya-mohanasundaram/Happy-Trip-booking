package com.tripease.travelbooking.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.tripease.travelbooking.entity.Booking;
import com.tripease.travelbooking.entity.Ticket;
import com.tripease.travelbooking.exception.ResourceNotFoundException;
import com.tripease.travelbooking.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final com.tripease.travelbooking.repository.BookingRepository bookingRepository;
    private final Random random = new Random();

    public TicketService(TicketRepository ticketRepository,
                         com.tripease.travelbooking.repository.BookingRepository bookingRepository) {
        this.ticketRepository = ticketRepository;
        this.bookingRepository = bookingRepository;
    }

    public synchronized Ticket generateTicketForBooking(Booking booking) {
        // Return existing ticket if already generated
        Ticket existing = ticketRepository.findByBookingId(booking.getId()).orElse(null);
        if (existing != null) {
            return existing;
        }

        int year = LocalDate.now().getYear();
        String ticketId = "TKT-" + year + "-" + (1000 + random.nextInt(9000));

        Ticket ticket = new Ticket();
        ticket.setTicketId(ticketId);
        ticket.setBookingId(booking.getId());
        ticket.setSystemName("TripEase Travel Booking System");
        ticket.setPassengerName(booking.getGuestName());
        ticket.setPhoneNumber(booking.getGuestPhone());
        ticket.setDestination(booking.getDestination());
        ticket.setSelectedPackage(booking.getPackageTitle());
        ticket.setTravelStartDate(booking.getTravelDate());
        ticket.setTravelEndDate(booking.getReturnDate());
        ticket.setNumberOfTravellers(booking.getTravelers());
        ticket.setTotalAmount(booking.getTotalAmount());
        ticket.setDiscountApplied(booking.getDiscountApplied() != null ? booking.getDiscountApplied() : 0.0);
        ticket.setPaymentStatus(booking.getPaymentStatus());
        ticket.setBookingStatus("Confirmed");
        ticket.setBookingDate(LocalDateTime.now());

        return ticketRepository.save(ticket);
    }

    public Ticket getTicketByBookingId(String bookingId) {
        return ticketRepository.findByBookingId(bookingId)
                .orElseGet(() -> {
                    Booking booking = bookingRepository.findById(bookingId).orElse(null);
                    if (booking != null) {
                        return generateTicketForBooking(booking);
                    }
                    throw new ResourceNotFoundException("No confirmed ticket found for Booking ID: " + bookingId);
                });
    }

    public List<Ticket> getTicketsByPhone(String phone) {
        return ticketRepository.findByPhoneNumber(phone);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAllByOrderByBookingDateDesc();
    }

    public byte[] generateTicketPdf(String bookingId) {
        Ticket ticket = getTicketByBookingId(bookingId);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Document document = new Document(PageSize.A4, 36, 36, 36, 36);
            PdfWriter writer = PdfWriter.getInstance(document, out);
            document.open();

            // Brand Header Palette
            Color navyPrimary = new Color(15, 36, 68);
            Color orangeAccent = new Color(255, 107, 53);
            Color softBg = new Color(241, 245, 249);
            Color textDark = new Color(15, 23, 42);

            Font brandFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 22, orangeAccent);
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, navyPrimary);
            Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, textDark);
            Font regularFont = FontFactory.getFont(FontFactory.HELVETICA, 10, textDark);
            Font subFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Color.GRAY);

            // Header Banner
            PdfPTable headerTable = new PdfPTable(2);
            headerTable.setWidthPercentage(100);
            headerTable.setWidths(new float[]{60f, 40f});

            PdfPCell cell1 = new PdfPCell();
            cell1.setBorder(Rectangle.NO_BORDER);
            cell1.addElement(new Paragraph("TripEase", brandFont));
            cell1.addElement(new Paragraph("Official Travel Booking Ticket & Boarding Voucher", subFont));
            cell1.addElement(new Paragraph("System: TripEase Travel Booking System", subFont));
            headerTable.addCell(cell1);

            PdfPCell cell2 = new PdfPCell();
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
            Paragraph tktP = new Paragraph("TICKET ID: " + ticket.getTicketId(), boldFont);
            tktP.setAlignment(Element.ALIGN_RIGHT);
            cell2.addElement(tktP);
            Paragraph bkP = new Paragraph("BOOKING REF: " + ticket.getBookingId(), subFont);
            bkP.setAlignment(Element.ALIGN_RIGHT);
            cell2.addElement(bkP);
            Paragraph dateP = new Paragraph("Issued: " + ticket.getBookingDate().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm")), subFont);
            dateP.setAlignment(Element.ALIGN_RIGHT);
            cell2.addElement(dateP);
            headerTable.addCell(cell2);

            document.add(headerTable);
            document.add(new Paragraph(" "));

            // Status Ribbon Table
            PdfPTable ribbonTable = new PdfPTable(3);
            ribbonTable.setWidthPercentage(100);
            ribbonTable.setWidths(new float[]{33f, 33f, 34f});

            addRibbonCell(ribbonTable, "BOOKING STATUS", ticket.getBookingStatus().toUpperCase(), new Color(16, 185, 129), boldFont, subFont);
            addRibbonCell(ribbonTable, "PAYMENT STATUS", ticket.getPaymentStatus().toUpperCase(), new Color(2, 132, 199), boldFont, subFont);
            addRibbonCell(ribbonTable, "TOTAL PAID", "INR " + String.format("%.2f", ticket.getTotalAmount()), orangeAccent, boldFont, subFont);
            document.add(ribbonTable);
            document.add(new Paragraph(" "));

            // Passenger & Travel Details Box
            PdfPTable detailsTable = new PdfPTable(2);
            detailsTable.setWidthPercentage(100);
            detailsTable.setWidths(new float[]{50f, 50f});

            addDetailRow(detailsTable, "Lead Passenger Name:", ticket.getPassengerName(), boldFont, regularFont);
            addDetailRow(detailsTable, "Contact Phone:", ticket.getPhoneNumber(), boldFont, regularFont);
            addDetailRow(detailsTable, "Tour Package:", ticket.getSelectedPackage(), boldFont, regularFont);
            addDetailRow(detailsTable, "Destination:", ticket.getDestination(), boldFont, regularFont);
            addDetailRow(detailsTable, "Travel Start Date:", ticket.getTravelStartDate(), boldFont, regularFont);
            addDetailRow(detailsTable, "Travel End Date:", ticket.getTravelEndDate(), boldFont, regularFont);
            addDetailRow(detailsTable, "Number of Travellers:", String.valueOf(ticket.getNumberOfTravellers()) + " Person(s)", boldFont, regularFont);
            addDetailRow(detailsTable, "Discount Applied:", "INR " + String.format("%.2f", ticket.getDiscountApplied()), boldFont, regularFont);

            document.add(detailsTable);
            document.add(new Paragraph(" "));

            // Terms and Instructions Notice Box
            PdfPTable noticeTable = new PdfPTable(1);
            noticeTable.setWidthPercentage(100);
            PdfPCell noticeCell = new PdfPCell();
            noticeCell.setBackgroundColor(softBg);
            noticeCell.setPadding(10);
            noticeCell.setBorderColor(new Color(203, 213, 225));

            Paragraph noticeHeading = new Paragraph("Important Passenger Travel Guidelines:", boldFont);
            Paragraph noticeText1 = new Paragraph("1. Please present this official TripEase E-Ticket along with government-issued photo ID at hotel check-in and transit pickups.", subFont);
            Paragraph noticeText2 = new Paragraph("2. Standard hotel check-in is 14:00 hrs and check-out is 11:00 hrs. Dedicated 24/7 Concierge helpline: 1800-209-8747.", subFont);
            Paragraph noticeText3 = new Paragraph("3. Free cancellation applies up to 72 hours prior to travel departure date.", subFont);

            noticeCell.addElement(noticeHeading);
            noticeCell.addElement(noticeText1);
            noticeCell.addElement(noticeText2);
            noticeCell.addElement(noticeText3);
            noticeTable.addCell(noticeCell);

            document.add(noticeTable);
            document.close();

            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate PDF ticket: " + e.getMessage(), e);
        }
    }

    private void addRibbonCell(PdfPTable table, String label, String value, Color color, Font boldFont, Font subFont) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(new Color(248, 250, 252));
        cell.setPadding(8);
        cell.setBorderColor(new Color(226, 232, 240));
        cell.addElement(new Paragraph(label, subFont));
        Font valFont = new Font(boldFont.getFamily(), 12, Font.BOLD, color);
        cell.addElement(new Paragraph(value, valFont));
        table.addCell(cell);
    }

    private void addDetailRow(PdfPTable table, String label, String value, Font boldFont, Font regularFont) {
        PdfPCell cell = new PdfPCell();
        cell.setPadding(6);
        cell.setBorderColor(new Color(226, 232, 240));
        Paragraph p = new Paragraph();
        p.add(new Chunk(label + " ", boldFont));
        p.add(new Chunk(value != null ? value : "N/A", regularFont));
        cell.addElement(p);
        table.addCell(cell);
    }
}
