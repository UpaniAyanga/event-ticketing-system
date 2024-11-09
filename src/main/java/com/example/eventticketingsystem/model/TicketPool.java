package com.example.eventticketingsystem.model;

import java.util.ArrayList;
import java.util.List;

public class TicketPool {
    private final List<Ticket> tickets;
    private final int maxTicketCapacity;
    private final int totalTicketsInEvent;
    private int currentTicketCount = 0;
    private int totalTicketsAdded = 0;

    public TicketPool(int maxTicketCapacity, int totalTicketsInEvent) {
        this.maxTicketCapacity = maxTicketCapacity;
        this.totalTicketsInEvent = totalTicketsInEvent;
        this.tickets = new ArrayList<>();
    }

    public synchronized boolean canAddTickets(int tickets) {
        return currentTicketCount + tickets <= maxTicketCapacity && totalTicketsAdded + tickets <= totalTicketsInEvent;
    }

    public synchronized void addTickets(Ticket ticket) {
        if (canAddTickets(1)) {
            tickets.add(ticket);
            currentTicketCount ++;
            totalTicketsAdded ++;
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

    public Ticket getTicketById(String ticketId) {
        for (Ticket ticket : tickets) {
            if (ticket.getTicketId().equals(ticketId)) {
                return ticket;
            }
        }
        return null; // Return null if ticket not found
    }

    public int getCurrentTicketCount() {
        return currentTicketCount;
    }

    public List<Ticket> getAllTickets() {
        return new ArrayList<>(tickets);
    }
}
