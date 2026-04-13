package com.example.aisaas.service;

import com.example.aisaas.entity.Tenant;
import com.example.aisaas.entity.Ticket;
import com.example.aisaas.repository.TenantRepository;
import com.example.aisaas.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TenantRepository tenantRepository;

    public Ticket createTicket(String title, String description, Long tenantId) {
        Tenant tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new RuntimeException("Tenant not found"));

        Ticket ticket = Ticket.builder()
                .title(title)
                .description(description)
                .status("OPEN")
                .createdAt(LocalDateTime.now())
                .tenant(tenant)
                .build();

        return ticketRepository.save(ticket);
    }

    public List<Ticket> getTicketsByTenant(Long tenantId) {
        return ticketRepository.findByTenantId(tenantId);
    }

    public Ticket resolveTicket(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        ticket.setStatus("RESOLVED");
        return ticketRepository.save(ticket);
    }
}

