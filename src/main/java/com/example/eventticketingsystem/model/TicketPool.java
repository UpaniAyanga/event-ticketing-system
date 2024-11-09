package com.example.eventticketingsystem.model;

public class TicketPool {
    private final int maxTicketCapacity;
    private final int totalTicketsInEvent;
    private int currentTicketCount = 0;
    private int totalTicketsAdded = 0;

    public TicketPool(int maxTicketCapacity, int totalTicketsInEvent) {
        this.maxTicketCapacity = maxTicketCapacity;
        this.totalTicketsInEvent = totalTicketsInEvent;
    }

    public synchronized boolean canAddTickets(int tickets) {
        return currentTicketCount + tickets <= maxTicketCapacity && totalTicketsAdded + tickets <= totalTicketsInEvent;
    }

    public synchronized void addTickets(int tickets) {
        if (canAddTickets(tickets)) {
            currentTicketCount += tickets;
            totalTicketsAdded += tickets;
        }
    }

    public synchronized boolean canRetrieveTickets(int tickets) {
        return currentTicketCount >= tickets;
    }

    public synchronized void removeTickets(int tickets) {
        if (canRetrieveTickets(tickets)) {
            currentTicketCount -= tickets;
        }
    }

    public int getCurrentTicketCount() {
        return currentTicketCount;
    }
}
