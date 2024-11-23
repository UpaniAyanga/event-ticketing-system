package com.example.eventticketingsystem.model;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Customer implements Runnable {
    private static final AtomicInteger customerCounter = new AtomicInteger(0);
    private final String customerId;
    private final int ticketCount; // Total tickets to retrieve
    private final TicketPool ticketPool;
    private final int retrievalRate;

    public Customer(TicketPool ticketPool, int retrievalRate) {
        this.customerId = "Cust" + customerCounter.incrementAndGet();
        this.ticketCount = new Random().nextInt(10) + 1;
        this.ticketPool = ticketPool;
        this.retrievalRate = retrievalRate;
    }

    @Override
    public void run() {
        try {
            int ticketsRetrieved = 0;
            while (ticketsRetrieved < ticketCount) {

                int ticketsToRetrieve = Math.min(retrievalRate, ticketCount - ticketsRetrieved);

                ticketPool.removeTickets(ticketsToRetrieve, customerId, retrievalRate);
                ticketsRetrieved += ticketsToRetrieve;

                System.out.println("Customer " + customerId + " retrieved " + ticketsRetrieved + "/" + ticketCount + " tickets.");

                if (ticketsRetrieved < ticketCount) {
                    Thread.sleep(1000);
                }
            }

            System.out.println("Customer " + customerId + " finished retrieving " + ticketCount + " tickets.");
        } catch (InterruptedException e) {
            System.out.println("Customer " + customerId + " stopped.");
            Thread.currentThread().interrupt();
        }
    }

    public String getCustomerId() {
        return customerId;
    }

    public int getTicketCount() {
        return ticketCount;
    }
}
