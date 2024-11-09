package com.example.eventticketingsystem.config;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class ConfigManager {
    private static final String CONFIG_FILE = "config.json";
    private static final String VENDOR_CONFIG_FILE = "vendorConfig.json";
    private static final String CUSTOMER_CONFIG_FILE = "customerConfig.json";
    private final Gson gson = new Gson();
    // Save Config object to config.json file
    public void saveConfigAsJson(Config config) {
        saveToJsonFile(config, CONFIG_FILE);
    }

    // Save VendorConfig list to vendor_config.json file
    public void saveVendorConfigsAsJson(List<VendorConfig> vendorConfigs) {
        saveToJsonFile(vendorConfigs, VENDOR_CONFIG_FILE);
    }

    // Save CustomerConfig list to customer_config.json file
    public void saveCustomerConfigsAsJson(List<CustomerConfig> customerConfigs) {
        saveToJsonFile(customerConfigs, CUSTOMER_CONFIG_FILE);
    }

    private <T> void saveToJsonFile(T configData, String fileName) {
        String jsonData = gson.toJson(configData);

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(jsonData);
            System.out.println("Configuration saved to " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving configuration to " + fileName + ": " + e.getMessage());
        }
    }

    // Load Config from config.json file
    public Config loadConfig() {
        return loadFromJsonFile(CONFIG_FILE, Config.class);
    }

    // Load VendorConfig list from vendor_config.json file
    public List<VendorConfig> loadVendorConfigs() {
        return Collections.singletonList(loadFromJsonFile(VENDOR_CONFIG_FILE, VendorConfig.class));
    }

    // Load CustomerConfig list from customer_config.json file
    public List<CustomerConfig> loadCustomerConfigs() {
        return Collections.singletonList(loadFromJsonFile(CUSTOMER_CONFIG_FILE, CustomerConfig.class));
    }

    // Helper method to handle the JSON file reading and deserialization process
    private <T> T loadFromJsonFile(String fileName, Class<T> classType) {
        try {
            String jsonData = new String(Files.readAllBytes(Paths.get(fileName)));
            return gson.fromJson(jsonData, classType);
        } catch (IOException e) {
            System.err.println("Error loading configuration from " + fileName + ": " + e.getMessage());
            return null;
        }
    }
}
