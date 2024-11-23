package com.example.eventticketingsystem;

import com.example.eventticketingsystem.cli.ConfigManager;
import com.example.eventticketingsystem.service.TicketSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Scanner;

@SpringBootApplication
public class EventTicketingSystemApplication {
	@Autowired
	public EventTicketingSystemApplication() {
		ConfigManager configManager = new ConfigManager();
		TicketSystemService ticketSystemService = new TicketSystemService(configManager);
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("\n==============================");
			System.out.println("Real-Time Ticketing System");
			System.out.println("==============================");
			System.out.println("1. Start System");
			System.out.println("2. Stop System");
			System.out.println("3. Run Simulation");
			System.out.println("4. Exit");
			System.out.print("Enter your choice: ");

			String choice = scanner.nextLine();

			try {
				switch (choice) {
					case "1":
						ticketSystemService.startSystem();
						break;
					case "2":
						ticketSystemService.stopSystem();
						break;
					case "3":
						ticketSystemService.startSimulation();
						break;
					case "4":
						System.out.println("Exiting the system. Goodbye!");
						System.exit(0);
						break;
					default:
						System.out.println("Invalid choice. Please try again.");
				}
			} catch (Exception e) {
				System.err.println("Error: " + e.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(EventTicketingSystemApplication.class, args);
	}
}
