package com.example.eventticketingsystem.model;
import java.util.UUID;
public class Ticket {
    private String ticketId;
    private String vendorId;
    private int ticketQuantity;

    public Ticket(String vendorId, int ticketQuantity) {
        this.ticketId = "T" + (int)(Math.random() * 1000);
        this.vendorId = vendorId;
        this.ticketQuantity = ticketQuantity;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public int getTicketQuantity() {
        return ticketQuantity;
    }

    public void setTicketQuantity(int ticketQuantity) {
        this.ticketQuantity = ticketQuantity;
    }

    public String toString() {
        return "Ticket ID: " + ticketId + ", Vendor ID: " + vendorId + ", Quantity: " + ticketQuantity;
    }
}
