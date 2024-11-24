package com.example.eventticketingsystem.cli;

import com.example.eventticketingsystem.service.TicketSystemService;

import java.util.Scanner;

public class TicketSystemCLI {
    public static void main(String[] args) {
        ConfigManager configManager = new ConfigManager();
        TicketSystemService ticketSystemService = new TicketSystemService(configManager);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Real-Time Ticketing System (CLI).");

        while (true) {
            System.out.println("\n==============================");
            System.out.println("1. Load Configuration");
            System.out.println("2. Start Simulation");
            System.out.println("3. Stop Simulation");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        configManager.collectConfigFromCLI();
                        ticketSystemService.initializeSystem();
                        break;
                    case "2":
                        ticketSystemService.startSimulation();
                        break;
                    case "3":
                        ticketSystemService.stopSimulation();
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
}
