package com.example.eventticketingsystem.config;

public class VendorConfig {
    private int ticketReleaseRate;
    private int totalVendorTickets;

    public VendorConfig(int ticketReleaseRate, int totalVendorTickets) {
        this.ticketReleaseRate = ticketReleaseRate;
        this.totalVendorTickets = totalVendorTickets;
    }

    // Getters and setters
    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getTotalVendorTickets() {
        return totalVendorTickets;
    }

    public void setTotalVendorTickets(int totalVendorTickets) {
        this.totalVendorTickets = totalVendorTickets;
    }
}
