package com.example.eventticketingsystem.model;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Vendor implements Runnable {
    private static final AtomicInteger vendorCounter = new AtomicInteger(0);
    private final String vendorId;
    private final int ticketCount;
    private final TicketPool ticketPool;
    private final int releaseRate;

    public Vendor(TicketPool ticketPool, int ticketCount, int releaseRate) {
        this.vendorId = "Vend" + vendorCounter.incrementAndGet();
        this.ticketCount = ticketCount;
        this.ticketPool = ticketPool;
        this.releaseRate = releaseRate;
    }

    @Override
    public void run() {
        try {
            int ticketsAdded = 0;
            while (ticketsAdded < ticketCount) {
                int ticketsToAdd = Math.min(releaseRate, ticketCount - ticketsAdded);
                ticketPool.addTickets(ticketsToAdd, vendorId, releaseRate);
                ticketsAdded += ticketsToAdd;

                System.out.println("Vendor " + vendorId + " released " + ticketsToAdd
                        + " tickets. Total released: " + ticketsAdded
                        + "/" + ticketCount);

                if (ticketsAdded < ticketCount) {
                    Thread.sleep(1000);
                }
            }
            System.out.println("Vendor " + vendorId + " finished releasing all " + ticketCount + " tickets.");
        } catch (InterruptedException e) {
            System.out.println("Vendor " + vendorId + " stopped.");
            Thread.currentThread().interrupt();
        }
    }

    public String getVendorId() {
        return vendorId;
    }

    public int getTicketCount() {
        return ticketCount;
    }
}
