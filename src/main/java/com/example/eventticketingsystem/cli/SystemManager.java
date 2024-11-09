package com.example.eventticketingsystem.cli;

import com.example.eventticketingsystem.config.Config;
import com.example.eventticketingsystem.config.ConfigManager;

public class SystemManager {
    private boolean systemRunning = false;
    private Config config;
    private ConfigManager configManager = new ConfigManager();

    boolean startSystem(Config config) {
        if (systemRunning) {
            System.out.println("System is already running.");
            return false;
        }

        systemRunning = true;
        configManager.saveConfigAsJson(this.config);
        System.out.println("System started with the configured parameters.");
        return false;
    }
    void stopSystem() {
        if (!systemRunning) {
            System.out.println("System is not running.");
            return;
        }
        System.out.println("Stopping the system...");
        systemRunning = false;
        System.out.println("System stopped.");
    }

}
