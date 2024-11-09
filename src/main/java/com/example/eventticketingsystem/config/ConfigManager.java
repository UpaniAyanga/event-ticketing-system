package com.example.eventticketingsystem.config;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ConfigManager {
    private static final String CONFIG_FILE = "config.json";

    public void saveConfigAsJson(Config config) {
        Gson gson = new Gson();
        String jsonConfig = gson.toJson(config);

        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            writer.write(jsonConfig);
            System.out.println("Configuration saved to " + CONFIG_FILE);
        } catch (IOException e) {
            System.err.println("Error saving configuration: " + e.getMessage());
        }
    }

    public Config loadConfig() {
        try {
            String jsonConfig = new String(Files.readAllBytes(Paths.get(CONFIG_FILE)));
            return new Gson().fromJson(jsonConfig, Config.class);
        } catch (IOException e) {
            System.err.println("Error loading configuration: " + e.getMessage());
            return null;
        }
    }
}
