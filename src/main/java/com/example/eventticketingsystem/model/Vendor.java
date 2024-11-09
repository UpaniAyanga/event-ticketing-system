package com.example.eventticketingsystem.model;
import java.util.concurrent.atomic.AtomicInteger;

public class Vendor implements Runnable {
    private static final AtomicInteger vendorCounter = new AtomicInteger(0);
    private final String vendorId;
    private final int ticketReleaseRate;
    private final int vendorTotalTickets;
    private final TicketPool ticketPool;

    public Vendor(int ticketReleaseRate, int vendorTotalTickets, TicketPool ticketPool) {
        this.vendorId = "V" + vendorCounter.incrementAndGet();
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

                    for (int i = 0; i < ticketsToAdd; i++) {
                        Ticket newTicket = new Ticket(vendorId, vendorTotalTickets);
                        ticketPool.addTickets(newTicket);

                        System.out.println("Vendor ID: " + vendorId + " is adding Ticket ID: " + newTicket.getTicketId());
                    }

                    ticketsAdded += ticketsToAdd;
                    System.out.println("Vendor added " + ticketsToAdd + " tickets. Total tickets in pool: " + ticketPool.getCurrentTicketCount());
                } else {
                    System.out.println("Vendor paused - pool at max capacity or total event limit reached.");
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Vendor thread interrupted: " + e.getMessage());
            }
        }
        System.out.println("Vendor finished adding tickets.");
    }
}
