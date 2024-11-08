package com.example.eventticketingsystem.cli;

import model.TicketPool;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TicketSystemCLI {

    private boolean systemRunning = false;
    private Scanner scanner = new Scanner(System.in);


    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;
    private TicketPool ticketPool;

    public static void main(String[] args) {
        TicketSystemCLI ticketSystemCLI = new TicketSystemCLI();
        ticketSystemCLI.configureApplication();
    }

    public void configureApplication() {

        while (true) {
            System.out.println("\n--- Event Ticketing System ---");
            System.out.println("1. Start System");
            System.out.println("2. Stop System");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    startSystem();
                    break;
                case 2:
                    stopSystem();
                    break;
                case 3:
                    exitSystem();
                    return;
                default:
                    System.out.println("Invalid choice. Please select 1, 2, or 3.");
            }
        }
    }

    private void startSystem() {
        if (systemRunning) {
            System.out.println("System is already running.");
            return;
        }
        System.out.println("Starting the system...");

        System.out.println("Enter Total Tickets: ");
        totalTickets = scanner.nextInt();

        System.out.println("Enter Ticket Release Rate: ");
        ticketReleaseRate = scanner.nextInt();

        System.out.println("Enter Customer Retrieval Rate: ");
        customerRetrievalRate = scanner.nextInt();

        System.out.println("Enter Max Ticket Capacity: ");
        maxTicketCapacity = scanner.nextInt();

        systemRunning = true;
        saveConfigAsJson();
        System.out.println("System started with the configured parameters.");
    }

    private void stopSystem() {
        if (!systemRunning) {
            System.out.println("System is not running.");
            return;
        }
        System.out.println("Stopping the system...");
        systemRunning = false;
    }

    private void exitSystem() {
        System.out.println("Exiting the system...");
        System.exit(0);
    }

    public void saveConfigAsJson() {
        SystemConfig config = new SystemConfig(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
        Gson gson = new Gson();
        String jsonConfig = gson.toJson(config);

        // Save to file
        try (FileWriter writer = new FileWriter("config.json")) {
            writer.write(jsonConfig);
            System.out.println("Configuration saved to config.json");
        } catch (IOException e) {
            System.err.println("Error saving configuration: " + e.getMessage());
        }
    }

    private static class SystemConfig {
        private int totalTickets;
        private int ticketReleaseRate;
        private int customerRetrievalRate;
        private int maxTicketCapacity;

        public SystemConfig(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity) {
            this.totalTickets = totalTickets;
            this.ticketReleaseRate = ticketReleaseRate;
            this.customerRetrievalRate = customerRetrievalRate;
            this.maxTicketCapacity = maxTicketCapacity;
        }

    }
}
