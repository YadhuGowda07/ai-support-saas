package com.example.aisaas.controller;

import com.example.aisaas.entity.Ticket;
import com.example.aisaas.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<Ticket> createTicket(
            @RequestBody Map<String, String> request) {
        Ticket ticket = ticketService.createTicket(
                request.get("title"),
                request.get("description"),
                Long.parseLong(request.get("tenantId"))
        );
        return ResponseEntity.ok(ticket);
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getTickets(
            @RequestParam Long tenantId) {
        return ResponseEntity.ok(ticketService.getTicketsByTenant(tenantId));
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<Ticket> resolveTicket(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.resolveTicket(id));
    }
}

