package com.example.ticketing;

public class Customer implements Runnable {
    private final TicketPool ticketPool; // Reference to the ticket pool
    private final int ticketPurchaseRate; // Rate at which tickets are purchased

    // Constructor to initialize the ticket pool and purchase rate
    public Customer(TicketPool ticketPool, int ticketPurchaseRate) {
        this.ticketPool = ticketPool;
        this.ticketPurchaseRate = ticketPurchaseRate;
    }

    // Override the run method to define the behavior of the thread
    @Override
    public void run() {
        while (true) {
            // Attempt to purchase a ticket from the ticket pool
            Ticket ticket = ticketPool.purchaseTicket();
            if (ticket != null) {
                System.out.println("Ticket purchased: " + ticket.getId());
            }

            // Sleep for the specified purchase rate duration
            try {
                Thread.sleep(ticketPurchaseRate);
            } catch (InterruptedException e) {
                // If interrupted, set the thread's interrupt flag and break the loop
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
