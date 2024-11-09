package com.example.eventticketingsystem.cli;
import com.example.eventticketingsystem.config.Config;
import com.example.eventticketingsystem.config.ConfigManager;
import com.example.eventticketingsystem.config.CustomerConfig;
import com.example.eventticketingsystem.config.VendorConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicketSystemCLI {
    private Scanner scanner = new Scanner(System.in);
    private SystemManager systemManager = new SystemManager();
    private ConfigManager configManager = new ConfigManager();
    private List<VendorConfig> vendorConfigs = new ArrayList<>();
    private List<CustomerConfig> customerConfigs = new ArrayList<>();

    public static void main(String[] args) {
        TicketSystemCLI cli = new TicketSystemCLI();
        cli.runCLI();
    }

    public void runCLI() {
        Config eventConfig = getEventConfig();
        configManager.saveConfigAsJson(eventConfig);

        while (true) {
            System.out.println("\n--- Event Ticketing System ---");
            System.out.println("1. Add Vendor");
            System.out.println("2. Add Customer");
            System.out.println("3. Start Simulation");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    VendorConfig vendorConfig = getVendorConfig();
                    vendorConfigs.add(vendorConfig);
                    //configManager.saveVendorConfig(vendorConfig);
                    break;
                case 2:
                    CustomerConfig customerConfig = getCustomerConfig();
                    customerConfigs.add(customerConfig);
                    //configManager.saveCustomerConfig(customerConfig);
                    break;
                case 3:
                    systemManager.startSimulation(eventConfig, vendorConfigs, customerConfigs);
                    return;
                case 4:
                    exitSystem();
                    return;
                default:
                    System.out.println("Invalid choice. Please select between 1 and 4.");
            }
        }
    }

    private Config getEventConfig() {
        System.out.print("Enter Total Tickets in Event: ");
        int totalTickets = scanner.nextInt();

        System.out.print("Enter Max Ticket Capacity of the Pool: ");
        int maxTicketCapacity = scanner.nextInt();

        return new Config(totalTickets, maxTicketCapacity);
    }

    private VendorConfig getVendorConfig() {
        System.out.print("Enter Ticket Release Rate: ");
        int ticketReleaseRate = scanner.nextInt();

        System.out.print("Enter Total Tickets Vendor Will Sell: ");
        int vendorTotalTickets = scanner.nextInt();

        return new VendorConfig(ticketReleaseRate, vendorTotalTickets);
    }

    private CustomerConfig getCustomerConfig() {
        System.out.print("Enter Ticket Retrieval Rate: ");
        int ticketRetrievalRate = scanner.nextInt();

        return new CustomerConfig(ticketRetrievalRate);
    }

    private void exitSystem() {
        System.out.println("Exiting the system. Goodbye!");
        scanner.close();
    }
}
