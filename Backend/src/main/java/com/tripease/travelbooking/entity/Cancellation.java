package com.tripease.travelbooking.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cancellations")
public class Cancellation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String bookingId;

    @Column(columnDefinition = "TEXT")
    private String reason;

    private Double refundAmount;

    @Column(length = 30)
    private String refundStatus = "Initiated";

    @Column(name = "cancelled_at")
    private LocalDateTime cancelledAt = LocalDateTime.now();

    public Cancellation() {}

    public Cancellation(String bookingId, String reason, Double refundAmount, String refundStatus) {
        this.bookingId = bookingId;
        this.reason = reason;
        this.refundAmount = refundAmount;
        this.refundStatus = refundStatus != null ? refundStatus : "Initiated";
        this.cancelledAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public Double getRefundAmount() { return refundAmount; }
    public void setRefundAmount(Double refundAmount) { this.refundAmount = refundAmount; }

    public String getRefundStatus() { return refundStatus; }
    public void setRefundStatus(String refundStatus) { this.refundStatus = refundStatus; }

    public LocalDateTime getCancelledAt() { return cancelledAt; }
    public void setCancelledAt(LocalDateTime cancelledAt) { this.cancelledAt = cancelledAt; }
}

