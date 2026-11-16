package com.tripease.travelbooking.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String ticketId; // e.g. TKT-2026-8941

    @Column(nullable = false, length = 50)
    private String bookingId;

    @Column(length = 120)
    private String systemName = "TripEase Travel Booking System";

    @Column(nullable = false, length = 120)
    private String passengerName;

    @Column(length = 30)
    private String phoneNumber;

    @Column(length = 120)
    private String destination;

    @Column(length = 200)
    private String selectedPackage;

    @Column(length = 30)
    private String travelStartDate;

    @Column(length = 30)
    private String travelEndDate;

    private Integer numberOfTravellers = 1;

    private Double totalAmount;

    private Double discountApplied = 0.0;

    @Column(length = 30)
    private String paymentStatus = "Paid";

    @Column(length = 30)
    private String bookingStatus = "Confirmed";

    @Column(name = "booking_date")
    private LocalDateTime bookingDate = LocalDateTime.now();

    public Ticket() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTicketId() { return ticketId; }
    public void setTicketId(String ticketId) { this.ticketId = ticketId; }

    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }

    public String getSystemName() { return systemName; }
    public void setSystemName(String systemName) { this.systemName = systemName; }

    public String getPassengerName() { return passengerName; }
    public void setPassengerName(String passengerName) { this.passengerName = passengerName; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getSelectedPackage() { return selectedPackage; }
    public void setSelectedPackage(String selectedPackage) { this.selectedPackage = selectedPackage; }

    public String getTravelStartDate() { return travelStartDate; }
    public void setTravelStartDate(String travelStartDate) { this.travelStartDate = travelStartDate; }

    public String getTravelEndDate() { return travelEndDate; }
    public void setTravelEndDate(String travelEndDate) { this.travelEndDate = travelEndDate; }

    public Integer getNumberOfTravellers() { return numberOfTravellers; }
    public void setNumberOfTravellers(Integer numberOfTravellers) { this.numberOfTravellers = numberOfTravellers; }

    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }

    public Double getDiscountApplied() { return discountApplied; }
    public void setDiscountApplied(Double discountApplied) { this.discountApplied = discountApplied; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    public String getBookingStatus() { return bookingStatus; }
    public void setBookingStatus(String bookingStatus) { this.bookingStatus = bookingStatus; }

    public LocalDateTime getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDateTime bookingDate) { this.bookingDate = bookingDate; }
}

