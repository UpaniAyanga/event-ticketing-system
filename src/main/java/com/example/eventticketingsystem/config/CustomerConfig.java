package com.example.eventticketingsystem.config;

public class CustomerConfig {
    private int ticketRetrievalRate;

    public CustomerConfig(int ticketRetrievalRate) {
        this.ticketRetrievalRate = ticketRetrievalRate;
    }

    // Getters and setters
    public int getTicketRetrievalRate() {
        return ticketRetrievalRate;
    }

    public void setTicketRetrievalRate(int ticketRetrievalRate) {
        this.ticketRetrievalRate = ticketRetrievalRate;
    }
}
