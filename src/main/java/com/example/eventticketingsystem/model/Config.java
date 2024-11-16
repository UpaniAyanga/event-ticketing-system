package com.example.eventticketingsystem.model;

public class Config {
    private int totalEventTickets;
    private int maxTicketCapacity;
    private int ticketReleaseRate;
    private int ticketRetrievalRate;
    private int vendorCount;
    private int customerCount;

    public Config() {
    }
    public int getTotalEventTickets() {
        return totalEventTickets;
    }

    public void setTotalEventTickets(int totalEventTickets) {
        this.totalEventTickets = totalEventTickets;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getTicketRetrievalRate() {
        return ticketRetrievalRate;
    }

    public void setTicketRetrievalRate(int ticketRetrievalRate) {
        this.ticketRetrievalRate = ticketRetrievalRate;
    }

    public int getVendorCount() {
        return vendorCount;
    }

    public void setVendorCount(int vendorCount) {
        this.vendorCount = vendorCount;
    }

    public int getCustomerCount() {
        return customerCount;
    }

    public void setCustomerCount(int customerCount) {
        this.customerCount = customerCount;
    }
}
