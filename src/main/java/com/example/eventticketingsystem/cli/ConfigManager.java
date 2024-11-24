package com.example.eventticketingsystem.cli;

import com.example.eventticketingsystem.model.Config;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Scanner;

@Component
public class ConfigManager {

    private Config config = new Config();

    // CLI-specific method to collect configurations
    public void collectConfigFromCLI() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the total number of tickets in the event: ");
        config.setTotalEventTickets(validatePositiveInteger(scanner.nextLine()));

        System.out.println("Enter the maximum ticket capacity in the event pool: ");
        config.setMaxTicketCapacity(validatePositiveInteger(scanner.nextLine()));

        System.out.println("Enter the release rate of tickets by vendors: ");
        config.setTicketReleaseRate(validatePositiveInteger(scanner.nextLine()));

        System.out.println("Enter the retrieval rate of tickets by customers: ");
        config.setTicketRetrievalRate(validatePositiveInteger(scanner.nextLine()));

        System.out.println("Enter the number of vendors for the simulation: ");
        config.setVendorCount(validatePositiveInteger(scanner.nextLine()));

        System.out.println("Enter the number of customers for the simulation: ");
        config.setCustomerCount(validatePositiveInteger(scanner.nextLine()));

        saveConfigToFile();
        System.out.println("Configuration saved successfully.");
    }

    // Spring-specific method to set configurations via REST
    public void setConfigFromApi(Config config) {
        this.config = config;
        saveConfigToFile();
    }

    public Config getConfig() {
        return config;
    }

    private void saveConfigToFile() {
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
}
