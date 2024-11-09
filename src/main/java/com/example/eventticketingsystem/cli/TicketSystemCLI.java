package com.example.eventticketingsystem.cli;

import com.example.eventticketingsystem.config.Config;

import java.util.Scanner;

public class TicketSystemCLI {
    private Scanner scanner = new Scanner(System.in);
    private SystemManager systemManager = new SystemManager();

    public static void main(String[] args) {
        TicketSystemCLI cli = new TicketSystemCLI();
        cli.runCLI();
    }

    public void runCLI() {
        while (true) {
            System.out.println("\n--- Event Ticketing System ---");
            System.out.println("1. Start System");
            System.out.println("2. Stop System");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Config config = null;
                    if (!systemManager.startSystem(config)) {
                        config = getConfigFromUser();
                        systemManager.startSystem(config);
                    } else {
                        System.out.println("System is already running.");
                    }
                    break;
                case 2:
                    systemManager.stopSystem();
                    break;
                case 3:
                    exitSystem();
                    return;
                default:
                    System.out.println("Invalid choice. Please select 1, 2, or 3.");
            }
        }
    }

    private Config getConfigFromUser() {
        System.out.print("Enter Total Tickets in Event: ");
        int totalTickets = scanner.nextInt();

        System.out.print("Enter Max Ticket Capacity: ");
        int maxTicketCapacity = scanner.nextInt();

        return new Config(totalTickets, maxTicketCapacity);
    }

    private void exitSystem() {
        System.out.println("Exiting the system. Goodbye!");
        scanner.close();
    }
}
