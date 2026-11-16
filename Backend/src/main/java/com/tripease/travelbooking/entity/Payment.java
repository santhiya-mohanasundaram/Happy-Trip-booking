package com.tripease.travelbooking.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String bookingId;

    @Column(nullable = false, length = 60)
    private String paymentMethod;

    @Column(length = 80)
    private String transactionRef;

    @Column(nullable = false)
    private Double amount;

    @Column(length = 30)
    private String status = "SUCCESS"; // SUCCESS, REFUNDED, FAILED

    @Column(name = "payment_date")
    private LocalDateTime paymentDate = LocalDateTime.now();

    public Payment() {}

    public Payment(String bookingId, String paymentMethod, String transactionRef, Double amount, String status) {
        this.bookingId = bookingId;
        this.paymentMethod = paymentMethod;
        this.transactionRef = transactionRef;
        this.amount = amount;
        this.status = status != null ? status : "SUCCESS";
        this.paymentDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getTransactionRef() {
        return transactionRef;
    }

    public void setTransactionRef(String transactionRef) {
        this.transactionRef = transactionRef;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
}

