package com.example.eventticketingsystem.cli;
import com.example.eventticketingsystem.config.ConfigManager;
import com.example.eventticketingsystem.service.TicketSystemService;
import java.util.Scanner;

public class TicketSystemCLI {
    public static void main(String[] args) {
        ConfigManager configManager = new ConfigManager();
        TicketSystemService ticketSystemService = new TicketSystemService(configManager);
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
                        ticketSystemService.startSystem();
                        break;
                    case "2":
                        ticketSystemService.stopSystem();
                        break;
                    case "3":
                        ticketSystemService.startSimulation();
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
