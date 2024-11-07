package com.example.eventticketingsystem.cli;

import model.TicketPool;

import java.util.Scanner;

public class TicketSystemCLI {

    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;
    private TicketPool ticketPool;
    public static void main(String[] args) {
        TicketSystemCLI ticketSystemCLI = new TicketSystemCLI();
        ticketSystemCLI.configureApplication();
    }

    public void configureApplication() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Total Tickets: ");
        totalTickets = scanner.nextInt();

        System.out.println("Enter Ticket Release Rate: ");
        ticketReleaseRate = scanner.nextInt();

        System.out.println("Enter Customer Retrieval Rate: ");
        customerRetrievalRate = scanner.nextInt();

        System.out.println("Enter Max Ticket Capacity: ");
        maxTicketCapacity = scanner.nextInt();

        saveConfiguration();
        scanner.close();
    }

    public void saveConfiguration() {
        SystemConfig config = new SystemConfig(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
    }

}
