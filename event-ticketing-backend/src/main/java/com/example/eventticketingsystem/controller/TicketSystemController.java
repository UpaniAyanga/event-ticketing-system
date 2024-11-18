package com.example.eventticketingsystem.controller;

import com.example.eventticketingsystem.service.TicketSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eventTicketingSystem")
public class TicketSystemController {

    private final TicketSystemService ticketSystemService;

    public TicketSystemController(TicketSystemService ticketSystemService) {
        this.ticketSystemService = ticketSystemService;
    }

    @PostMapping("/startSystem")
    @ResponseBody
    public String startSystem() {
        ticketSystemService.startSystem();
        return "System started";
    }

    @PostMapping("/stopSystem")
    @ResponseBody
    public String stopSystem() {
        ticketSystemService.stopSystem();
        return "System stopped";
    }

    @PostMapping("/startSimulation")
    @ResponseBody
    public String startSimulation() {
        ticketSystemService.startSimulation();
        return "Simulation started";
    }

}
