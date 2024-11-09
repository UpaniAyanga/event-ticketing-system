package com.example.eventticketingsystem.config;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigManager {
    public void saveConfigAsJson(Config config) {
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
}
