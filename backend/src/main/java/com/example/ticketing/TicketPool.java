package com.example.ticketing;

import java.util.ArrayList;
import java.util.List;

public class TicketPool {
    private final List<Ticket> tickets;
    private final int maxCapacity;
    private boolean initialized = false;

    public TicketPool(int maxCapacity) {
        this.tickets = new ArrayList<>();
        this.maxCapacity = maxCapacity;
    }

    public synchronized void addTickets(int numberOfTickets) {
        for (int i = 0; i < numberOfTickets; i++) {
            while (tickets.size() >= maxCapacity) {
                System.out.println("Maximum number of tickets reached. Vendor waiting.");
                try {
                    wait(); // Wait until a ticket is purchased
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return; // Exit if interrupted
                }
            }

            Ticket ticket = new Ticket();
            tickets.add(ticket);
            System.out.println("Ticket added: " + ticket.getId());
            notifyAll(); // Notify customers that a ticket is available
        }
    }

    public synchronized void initializeTickets(int initialCount) {
        for (int i = 0; i < initialCount; i++) {
            addTickets(1); // Adds one ticket at a time
        }
        initialized = true;
        notifyAll(); // Notify that initialization is complete
    }

    public synchronized Ticket purchaseTicket() {
        while (tickets.isEmpty()) {
            System.out.println("No tickets available. Customer waiting.");
            try {
                wait(); // Wait for a ticket to become available
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }
        Ticket ticket = tickets.remove(0);
        notifyAll(); // Notify vendors that tickets are now available
        return ticket;
    }

    public synchronized boolean isInitialized() {
        return initialized;
    }
}
