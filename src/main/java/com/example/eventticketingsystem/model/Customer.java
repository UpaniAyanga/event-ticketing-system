package com.example.eventticketingsystem.model;
import com.example.eventticketingsystem.model.TicketPool;

public class Customer implements Runnable {
    private final int ticketRetrievalRate; // Tickets retrieved per second
    private final TicketPool ticketPool;

    public Customer(int ticketRetrievalRate, TicketPool ticketPool) {
        this.ticketRetrievalRate = ticketRetrievalRate;
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (ticketPool) {
                if (ticketPool.canRetrieveTickets(ticketRetrievalRate)) {
                    ticketPool.removeTickets(ticketRetrievalRate);
                    System.out.println("Customer retrieved " + ticketRetrievalRate + " tickets. Remaining tickets in pool: " + ticketPool.getCurrentTicketCount());
                } else {
                    System.out.println("Not enough tickets in pool. Ending Simulation.");
                    break;

                }
            }

            try {
                Thread.sleep(1000); // Retrieval rate interval
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Customer thread interrupted: " + e.getMessage());
            }
        }
    }
}