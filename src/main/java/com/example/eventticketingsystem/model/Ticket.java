package com.example.eventticketingsystem.model;
import java.util.concurrent.atomic.AtomicInteger;

public class Ticket {
    private final String ticketId;
    private final String vendorId;
    private static final AtomicInteger ticketCounter = new AtomicInteger(0);

    public Ticket(String vendorId) {
        this.vendorId = vendorId;
        this.ticketId = vendorId + "-T" + ticketCounter.incrementAndGet();
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getVendorId() {
        return vendorId;
    }
}
