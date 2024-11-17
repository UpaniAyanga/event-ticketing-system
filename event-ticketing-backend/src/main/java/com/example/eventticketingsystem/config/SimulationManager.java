package com.example.eventticketingsystem.config;

import com.example.eventticketingsystem.model.Customer;
import com.example.eventticketingsystem.model.TicketPool;
import com.example.eventticketingsystem.model.Vendor;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Random;

public class SimulationManager {
    private static final AtomicInteger ticket = new AtomicInteger(0);
    private final Random random = new Random();
    private final List<Vendor> vendors = new ArrayList<>();
    private final List<Customer> customers = new ArrayList<>();
    private final TicketPool ticketPool;


    public SimulationManager(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    public void generateVendors(int vendorCount, int totalEventTickets, int retrievalRate) {
        if (vendorCount <= 0 || totalEventTickets <= 0) {
            System.out.println("Invalid input: Vendor count and total tickets must be greater than zero.");
            return;
        }

        int baseTicketCount = totalEventTickets / vendorCount;
        int remainingTickets = totalEventTickets % vendorCount;

        for (int i = 0; i < vendorCount; i++) {
            int ticketCount = baseTicketCount;
            if (remainingTickets > 0) {
                ticketCount++;
                remainingTickets--;
            }

            Vendor vendor = new Vendor(ticketPool, ticketCount, retrievalRate);
            vendors.add(vendor);

            System.out.println("Generated Vendor: " + vendor.getVendorId() + " with " + vendor.getTicketCount() + " tickets.");
        }
    }


    public void generateCustomers(int customerCount, int retrievalRate) {
        for (int i = 0; i < customerCount; i++) {
            Customer customer = new Customer(ticketPool, retrievalRate );
            customers.add(customer);
            System.out.println("Generated Customer: " + customer.getCustomerId() + " buying  " + customer.getTicketCount() + " tickets.");
        }
    }

    public void startSimulation() {
        for (Vendor vendor : vendors) {
            Thread vendorThread = new Thread(vendor);
            vendorThread.start();
        }

        for (Customer customer : customers) {
            Thread customerThread = new Thread(customer);
            customerThread.start();
        }
    }

    public void resetSimulation() {
        vendors.clear();
        customers.clear();
        ticketPool.clearPool();
    }
}



