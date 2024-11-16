package com.example.eventticketingsystem.cli;

import com.example.eventticketingsystem.config.ConfigManager;
import com.example.eventticketingsystem.config.SimulationManager;
import com.example.eventticketingsystem.model.Config;
import com.example.eventticketingsystem.model.TicketPool;

import java.util.Scanner;

public class TicketSystemCLI {
    private enum SystemState {INITIAL, RUNNING, STOPPED};
    private SystemState systemState = SystemState.INITIAL;
    private ConfigManager configManager = new ConfigManager();
    private SimulationManager simulationManager;

    public static void main(String[] args) {
        TicketSystemCLI cli = new TicketSystemCLI();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n==============================");
            System.out.println("Real-Time Ticketing System");
            System.out.println("==============================");
            System.out.println("1. Start System");
            System.out.println("2. Stop System");
            System.out.println("3. Run Simulation");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        cli.startSystem();
                        break;
                    case "2":
                        cli.stopSystem();
                        break;
                    case "3":
                        cli.startSimulation();
                        break;
                    case "4":
                        System.out.println("Exiting the system. Goodbye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    public void startSystem() {
        if (systemState == SystemState.RUNNING) {
            System.out.println("System is already running.");
            return;
        }
        System.out.println("Starting the system...");
        systemState = SystemState.RUNNING;

        configManager.configInputs();
        Config config = configManager.getConfig();
        TicketPool ticketPool = new TicketPool(config.getMaxTicketCapacity(), config.getTotalEventTickets());

        simulationManager = new SimulationManager(ticketPool);

        System.out.println("Generating vendors...");
        simulationManager.generateVendors(config.getVendorCount(), config.getTotalEventTickets(), config.getTicketReleaseRate());

        System.out.println("Generating customers...");
        simulationManager.generateCustomers(config.getCustomerCount(), config.getTicketRetrievalRate());

        System.out.println("System started. Ready to run simulation.");
    }

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

    public void startSimulation() {
        if (systemState == SystemState.RUNNING) {
            System.out.println("Starting simulation...");
            simulationManager.startSimulation();
        } else {
            System.out.println("System is not running. Please start the system first.");
        }
    }
}
