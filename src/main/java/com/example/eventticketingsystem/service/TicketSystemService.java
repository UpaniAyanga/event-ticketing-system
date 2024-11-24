package com.example.eventticketingsystem.service;

import com.example.eventticketingsystem.cli.ConfigManager;
import com.example.eventticketingsystem.cli.SimulationManager;
import com.example.eventticketingsystem.model.Config;
import com.example.eventticketingsystem.model.TicketPool;
import org.springframework.stereotype.Service;

@Service
public class TicketSystemService {

    private enum SystemState {INITIALIZED, SIMULATION_RUNNING, STOPPED}
    private SystemState systemState = SystemState.STOPPED;

    private final ConfigManager configManager;
    private SimulationManager simulationManager;

    public TicketSystemService(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public void loadConfig(Config config) {
        configManager.setConfigFromApi(config);
        initializeSystem();
    }

    public void initializeSystem() {
        Config config = configManager.getConfig();
        TicketPool ticketPool = new TicketPool(config.getMaxTicketCapacity(), config.getTotalEventTickets());
        simulationManager = new SimulationManager(ticketPool);

        simulationManager.generateVendors(config.getVendorCount(), config.getTotalEventTickets(), config.getTicketReleaseRate());
        simulationManager.generateCustomers(config.getCustomerCount(), config.getTicketRetrievalRate());

        systemState = SystemState.INITIALIZED;
        System.out.println("System initialized. Ready to start the simulation.");
    }

    public void startSimulation() {
        if (systemState != SystemState.INITIALIZED) {
            throw new IllegalStateException("System is not ready. Please load configuration first.");
        }
        simulationManager.startSimulation();
        systemState = SystemState.SIMULATION_RUNNING;
        System.out.println("Simulation started.");
    }

    public void stopSimulation() {
        if (systemState != SystemState.SIMULATION_RUNNING) {
            throw new IllegalStateException("Simulation is not running.");
        }
        simulationManager.resetSimulation();
        systemState = SystemState.INITIALIZED;
        System.out.println("Simulation stopped.");
    }
}
