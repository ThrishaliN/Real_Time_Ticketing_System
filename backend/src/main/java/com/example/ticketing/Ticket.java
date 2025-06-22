package com.example.ticketing;

import java.util.UUID;

public class Ticket {
    private final String id;

    public Ticket() {
        this.id = "Ticket-" + UUID.randomUUID();
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return id;
    }
}
