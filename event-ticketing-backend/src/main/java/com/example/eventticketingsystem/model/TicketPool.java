package com.example.eventticketingsystem.model;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.LinkedList;
import java.util.List;

public class TicketPool {
    private final int maxTicketCapacity;
    private final int totalEventTickets;
    private int totalTicketsProduced = 0;
    private int totalTicketsConsumed = 0;
    private final List<Ticket> tickets = new LinkedList<>();
    private static final Logger logger = LogManager.getLogger();

    public TicketPool(int maxTicketCapacity, int totalEventTickets) {
        this.maxTicketCapacity = maxTicketCapacity;
        this.totalEventTickets = totalEventTickets;
    }

    public synchronized void addTickets(int count, String vendorId, int releaseRate) {
        while (tickets.size() + count > maxTicketCapacity || totalTicketsProduced + count > totalEventTickets) {
            if (totalTicketsProduced >= totalEventTickets) {
                notifyAll();
                return;
            }
            try {
                logger.info("Vendor " + vendorId + " waiting for space in the pool...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IllegalStateException("Thread interrupted while waiting to add tickets.");
            }
        }

        int ticketsToAdd = count;
        while (ticketsToAdd > 0) {
            int ticketsAddedPerTime = Math.min(ticketsToAdd, releaseRate);
            logger.info("Vendor " + vendorId + " is adding " + ticketsAddedPerTime + " tickets.");

            for (int i = 0; i < ticketsAddedPerTime; i++) {
                tickets.add(new Ticket(vendorId));
            }

            totalTicketsProduced += ticketsAddedPerTime;
            ticketsToAdd -= ticketsAddedPerTime;

            System.out.println("Vendor " + vendorId + " added " + ticketsAddedPerTime + " tickets. Current pool size: " + tickets.size());

            if (ticketsToAdd > 0) {
                try {
                    System.out.println("Vendor " + vendorId + " waiting to add more tickets...");
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new IllegalStateException("Thread interrupted while waiting to add more tickets.");
                }
            }
        }

        notifyAll();
    }


    public synchronized void removeTickets(int count, String customerId, int retrievalRate) {
        while (tickets.size() < count) {
            if (totalTicketsProduced >= totalEventTickets && tickets.isEmpty()) {
                System.out.println("Customer " + customerId + ": All tickets have been sold out.");
                notifyAll();
                return;
            }
            try {
                System.out.println("Customer " + customerId + " waiting for tickets...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IllegalStateException("Thread interrupted while waiting for tickets.");
            }
        }

        int ticketsToRetrieve = count;
        while (ticketsToRetrieve > 0) {
            int ticketsBoughtPerTime = Math.min(ticketsToRetrieve, retrievalRate);
            System.out.println("Customer " + customerId + " retrieving " + ticketsBoughtPerTime + " tickets.");

            for (int i = 0; i < ticketsBoughtPerTime; i++) {
                tickets.remove(0);
            }

            ticketsToRetrieve -= ticketsBoughtPerTime;
            totalTicketsConsumed += ticketsBoughtPerTime;
            System.out.println("Customer " + customerId + " retrieved " + ticketsBoughtPerTime + " tickets. Remaining tickets to retrieve: " + ticketsToRetrieve);

            if (ticketsToRetrieve > 0) {
                try {
                    System.out.println("Customer " + customerId + " waiting to retrieve more tickets...");
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new IllegalStateException("Thread interrupted while waiting to retrieve more tickets.");
                }
            }
        }
        notifyAll();
    }



    public synchronized int getTicketCount() {
        return tickets.size();
    }

    public synchronized void clearPool() {
        tickets.clear();
    }
}
