package com.example.eventticketingsystem.cli;

import com.example.eventticketingsystem.config.Config;
import com.example.eventticketingsystem.config.VendorConfig;
import com.example.eventticketingsystem.config.CustomerConfig;
import com.example.eventticketingsystem.config.ConfigManager;
import com.example.eventticketingsystem.model.Vendor;
import com.example.eventticketingsystem.model.Customer;
import com.example.eventticketingsystem.model.TicketPool;

import java.util.ArrayList;
import java.util.List;

public class SystemManager {
    private boolean systemRunning = false;
    private TicketPool ticketPool;
    private ConfigManager configManager = new ConfigManager();

    public void startSimulation(Config eventConfig, List<VendorConfig> vendorConfigs, List<CustomerConfig> customerConfigs) {
        if (systemRunning) {
            System.out.println("System is already running.");
            return;
        }

        // Initialize ticket pool with max capacity and total tickets
        this.ticketPool = new TicketPool(eventConfig.getMaxTicketCapacity(), eventConfig.getTotalTickets());
        systemRunning = true;

        // Start vendor threads using VendorConfig
        List<Thread> vendorThreads = new ArrayList<>();
        for (VendorConfig vendorConfig : vendorConfigs) {
            Thread vendorThread = new Thread(new Vendor(vendorConfig.getTicketReleaseRate(), vendorConfig.getTotalVendorTickets(), ticketPool));
            vendorThreads.add(vendorThread);
            vendorThread.start();
        }

        // Start customer threads using CustomerConfig
        List<Thread> customerThreads = new ArrayList<>();
        for (CustomerConfig customerConfig : customerConfigs) {
            Thread customerThread = new Thread(new Customer(customerConfig.getTicketRetrievalRate(), ticketPool));
            customerThreads.add(customerThread);
            customerThread.start();
        }

        // Save the simulation configuration details
        //configManager.saveSimulationDetails(vendorConfigs, customerConfigs);

        System.out.println("Simulation started with configured vendors and customers.");
    }

    public void stopSimulation() {
        if (!systemRunning) {
            System.out.println("System is not running.");
            return;
        }
        System.out.println("Stopping the system...");
        systemRunning = false;
        System.out.println("System stopped.");
    }
}
