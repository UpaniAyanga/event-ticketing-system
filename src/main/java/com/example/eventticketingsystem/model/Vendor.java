package com.example.eventticketingsystem.model;
import com.example.eventticketingsystem.model.TicketPool;

public class Vendor implements Runnable {
    private final int ticketReleaseRate; // Tickets added per second
    private final int vendorTotalTickets; // Max tickets this vendor can add
    private final TicketPool ticketPool;

    public Vendor(int ticketReleaseRate, int vendorTotalTickets, TicketPool ticketPool) {
        this.ticketReleaseRate = ticketReleaseRate;
        this.vendorTotalTickets = vendorTotalTickets;
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        int ticketsAdded = 0;

        while (ticketsAdded < vendorTotalTickets) {
            synchronized (ticketPool) {
                if (ticketPool.canAddTickets(ticketReleaseRate)) {
                    int ticketsToAdd = Math.min(ticketReleaseRate, vendorTotalTickets - ticketsAdded);
                    ticketPool.addTickets(ticketsToAdd);
                    ticketsAdded += ticketsToAdd;
                    System.out.println("Vendor added " + ticketsToAdd + " tickets. Total tickets in pool: " + ticketPool.getCurrentTicketCount());
                } else {
                    System.out.println("Vendor paused - pool at max capacity or total event limit reached.");
                }
            }

            try {
                Thread.sleep(1000); // Release rate interval
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Vendor thread interrupted: " + e.getMessage());
            }
        }
        System.out.println("Vendor finished adding tickets.");
    }
}

