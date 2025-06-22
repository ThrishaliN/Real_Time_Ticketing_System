package com.example.ticketing;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    // Properties mapped to JSON fields
    @JsonProperty("totalTickets")
    private int totalTickets;

    @JsonProperty("ticketReleaseRate")
    private int ticketReleaseRate;

    @JsonProperty("customerRetrievalRate")
    private int customerRetrievalRate;

    @JsonProperty("maxTicketCapacity")
    private int maxTicketCapacity;

    // Configuration file name
    private final String propertiesFile = "config.txt";

    // Constructor to load configuration on object creation
    public Configuration() {
        loadConfiguration();
    }

    // Method to load configuration from the properties file
    public void loadConfiguration() {
        Properties props = new Properties();

        File configFile = new File(propertiesFile);
        if (!configFile.exists()) {
            // If config file does not exist, set default properties
            setDefaultProperties(props);
        } else {
            // Load properties from the file
            try (FileInputStream input = new FileInputStream(propertiesFile)) {
                props.load(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Retrieve values from properties
        totalTickets = Integer.parseInt(props.getProperty("totalTickets", "0"));
        ticketReleaseRate = Integer.parseInt(props.getProperty("ticketReleaseRate", "0"));
        customerRetrievalRate = Integer.parseInt(props.getProperty("customerRetrievalRate", "0"));
        maxTicketCapacity = Integer.parseInt(props.getProperty("maxTicketCapacity", "0"));
    }

    // Method to save configuration to the properties file
    public void saveConfiguration() {
        Properties props = new Properties();
        props.setProperty("totalTickets", String.valueOf(totalTickets));
        props.setProperty("ticketReleaseRate", String.valueOf(ticketReleaseRate));
        props.setProperty("customerRetrievalRate", String.valueOf(customerRetrievalRate));
        props.setProperty("maxTicketCapacity", String.valueOf(maxTicketCapacity));

        // Write properties to a file
        try (FileOutputStream output = new FileOutputStream(propertiesFile)) {
            props.store(output, "Configuration Properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to set default properties and save them to the file
    private void setDefaultProperties(Properties props) {
        props.setProperty("totalTickets", "0");
        props.setProperty("ticketReleaseRate", "0");
        props.setProperty("customerRetrievalRate", "0");
        props.setProperty("maxTicketCapacity", "0");
        saveConfiguration();
    }

    // Setter methods
    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    // Getter methods
    public int getTotalTickets() {
        return totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }
}
