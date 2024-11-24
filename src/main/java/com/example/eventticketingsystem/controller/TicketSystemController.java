package com.example.eventticketingsystem.controller;

import com.example.eventticketingsystem.model.Config;
import com.example.eventticketingsystem.service.TicketSystemService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event-ticketing")
public class TicketSystemController {

    private final TicketSystemService ticketSystemService;

    public TicketSystemController(TicketSystemService ticketSystemService) {
        this.ticketSystemService = ticketSystemService;
    }

    @PostMapping("/load-config")
    public String loadConfig(@RequestBody Config config) {
        ticketSystemService.loadConfig(config);
        return "Configuration loaded successfully.";
    }

    @PostMapping("/start-simulation")
    public String startSimulation() {
        ticketSystemService.startSimulation();
        return "Simulation started.";
    }

    @PostMapping("/stop-simulation")
    public String stopSimulation() {
        ticketSystemService.stopSimulation();
        return "Simulation stopped.";
    }
}
