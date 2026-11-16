package com.tripease.travelbooking.controller;

import com.tripease.travelbooking.dto.ApiResponse;
import com.tripease.travelbooking.entity.Ticket;
import com.tripease.travelbooking.service.TicketService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<ApiResponse<Ticket>> getTicketByBookingId(@PathVariable String bookingId) {
        Ticket ticket = ticketService.getTicketByBookingId(bookingId);
        return ResponseEntity.ok(ApiResponse.ok("Ticket retrieved successfully", ticket));
    }

    @GetMapping("/{bookingId}/download")
    public ResponseEntity<byte[]> downloadTicketPdf(@PathVariable String bookingId) {
        byte[] pdfBytes = ticketService.generateTicketPdf(bookingId);
        Ticket ticket = ticketService.getTicketByBookingId(bookingId);

        String filename = "TripEase_Ticket_" + ticket.getTicketId() + ".pdf";

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(pdfBytes);
    }

    @GetMapping("/user")
    public ResponseEntity<ApiResponse<List<Ticket>>> getUserTickets(@RequestParam(required = false) String phone) {
        List<Ticket> list = (phone != null && !phone.isBlank())
                ? ticketService.getTicketsByPhone(phone)
                : ticketService.getAllTickets();
        return ResponseEntity.ok(ApiResponse.ok("Tickets retrieved", list));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Ticket>>> getAllTickets() {
        List<Ticket> list = ticketService.getAllTickets();
        return ResponseEntity.ok(ApiResponse.ok("All tickets retrieved", list));
    }
}
