package com.example.ticketing;

public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final int ticketReleaseRate;

    public Vendor(TicketPool ticketPool, int ticketReleaseRate) {
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    @Override
    public void run() {
        synchronized (ticketPool) {
            while (!ticketPool.isInitialized()) {
                try {
                    ticketPool.wait(); // Wait until initialization is complete
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }

        // Begin adding tickets at intervals after initialization
        while (true) {
            ticketPool.addTickets(1);

            try {
                Thread.sleep(ticketReleaseRate);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
