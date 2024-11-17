package com.example.eventticketingsystem.service;

import com.example.eventticketingsystem.interfaces.TicketSystemInterface;
import com.example.eventticketingsystem.config.ConfigManager;
import com.example.eventticketingsystem.config.SimulationManager;
import com.example.eventticketingsystem.model.Config;
import com.example.eventticketingsystem.model.TicketPool;
import org.springframework.stereotype.Service;

@Service
public class TicketSystemService implements TicketSystemInterface {

    private enum SystemState {INITIAL, RUNNING, STOPPED}
    private SystemState systemState = SystemState.INITIAL;

    private final ConfigManager configManager;
    private SimulationManager simulationManager;

    public TicketSystemService(ConfigManager configManager) {
        this.configManager = configManager;
    }

    @Override
    public void startSystem() {
        if (systemState == SystemState.RUNNING) {
            System.out.println("System is already running.");
            return;
        }
        System.out.println("Starting the system...");
        systemState = SystemState.RUNNING;

        // Configure inputs
        configManager.configInputs();
        Config config = configManager.getConfig();

        // Initialize TicketPool and SimulationManager
        TicketPool ticketPool = new TicketPool(config.getMaxTicketCapacity(), config.getTotalEventTickets());
        simulationManager = new SimulationManager(ticketPool);

        // Generate vendors and customers
        System.out.println("Generating vendors...");
        simulationManager.generateVendors(config.getVendorCount(), config.getTotalEventTickets(), config.getTicketReleaseRate());

        System.out.println("Generating customers...");
        simulationManager.generateCustomers(config.getCustomerCount(), config.getTicketRetrievalRate());

        System.out.println("System started. Ready to run simulation.");
    }

    @Override
    public void stopSystem() {
        if (systemState == SystemState.RUNNING) {
            System.out.println("Stopping the system...");
            systemState = SystemState.STOPPED;
            simulationManager.resetSimulation();
            System.out.println("System stopped successfully.");
        } else {
            System.out.println("System is not running.");
        }
    }

    @Override
    public void startSimulation() {
        if (systemState == SystemState.RUNNING) {
            System.out.println("Starting simulation...");
            simulationManager.startSimulation();
        } else {
            System.out.println("System is not running. Please start the system first.");
        }
    }
}
