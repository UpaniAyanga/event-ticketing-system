package com.example.eventticketingsystem.cli;

import com.example.eventticketingsystem.model.Config;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Scanner;
@Component
public class ConfigManager {

    private Config config = new Config();
    public void configInputs () {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the total number of tickets in the event: ");
        config.setTotalEventTickets(validatePositiveInteger(scanner.nextLine()));

        System.out.println("Enter the maximum ticket capacity in the event pool: ");
        config.setMaxTicketCapacity(validatePositiveInteger(scanner.nextLine()));

        System.out.println("Enter the release rate of tickets by Vendor: ");
        config.setTicketReleaseRate(validatePositiveInteger(scanner.nextLine()));

        System.out.println("Enter the retrieval rate of tickets by Customer: ");
        config.setTicketRetrievalRate(validatePositiveInteger(scanner.nextLine()));

        System.out.println("Enter the number of Vendors for simulation: ");
        config.setVendorCount(validatePositiveInteger(scanner.nextLine()));

        System.out.println("Enter the number of Customers for simulation: ");
        config.setCustomerCount(validatePositiveInteger(scanner.nextLine()));

        saveConfigToFile();

        System.out.println("Configurations saved successfully.");
    }

    public void saveConfigToFile() {
        try {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("config.json"), config);
    } catch (Exception e) {
        System.err.println("Error saving configuration to file: " + e.getMessage());
    }
    }

    private int validatePositiveInteger(String input) {
        try {
            int value = Integer.parseInt(input);
            if (value <= 0) {
                throw new IllegalArgumentException("Value must be positive.");
            }
            return value;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input. Please enter a valid integer.");
        }
    }

    public Config getConfig() {
        return config;
    }
}
