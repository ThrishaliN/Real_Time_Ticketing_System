import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './App.css'; // Import the external CSS file

const App = () => {
    const [totalTickets, setTotalTickets] = useState('');
    const [ticketReleaseRate, setTicketReleaseRate] = useState('');
    const [customerRetrievalRate, setCustomerRetrievalRate] = useState('');
    const [maxTicketCapacity, setMaxTicketCapacity] = useState('');

    const API_URL = 'http://localhost:8080/api/ticketing';

    // Fetching current configuration
    const fetchConfig = async () => {
        try {
            const response = await axios.get(`${API_URL}/config`);
            const config = response.data;
            setTotalTickets(config.totalTickets);
            setTicketReleaseRate(config.ticketReleaseRate);
            setCustomerRetrievalRate(config.customerRetrievalRate);
            setMaxTicketCapacity(config.maxTicketCapacity);
        } catch (error) {
            console.error('Error fetching config:', error);
        }
    };

    useEffect(() => {
        fetchConfig(); 
    }, []);

    const handleSubmit = async (e) => {
        e.preventDefault();
        const data = {
            totalTickets: parseInt(totalTickets),
            ticketReleaseRate: parseInt(ticketReleaseRate),
            customerRetrievalRate: parseInt(customerRetrievalRate),
            maxTicketCapacity: parseInt(maxTicketCapacity),
        };

        console.log("Sending data:", data);

        try {
            await axios.post(`${API_URL}/start`, data);
            alert('Configuration updated and ticketing system started!');
        } catch (error) {
            console.error('Error in POST request:', error);
            if (error.response) {
                console.error('Response data:', error.response.data);
                alert(`Error: ${error.response.data}`);
            } else if (error.request) {
                alert('Error: No response from server.');
            } else {
                alert(`Error: ${error.message}`);
            }
        }
    };

    return (
        <div className="app-container">
            <h1 className="title">Ticketing System Configuration</h1>
            <form className="config-form" onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Total Tickets:</label>
                    <input
                        type="number"
                        value={totalTickets}
                        onChange={(e) => setTotalTickets(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Ticket Release Rate:</label>
                    <input
                        type="number"
                        value={ticketReleaseRate}
                        onChange={(e) => setTicketReleaseRate(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Customer Retrieval Rate:</label>
                    <input
                        type="number"
                        value={customerRetrievalRate}
                        onChange={(e) => setCustomerRetrievalRate(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Max Ticket Capacity:</label>
                    <input
                        type="number"
                        value={maxTicketCapacity}
                        onChange={(e) => setMaxTicketCapacity(e.target.value)}
                        required
                    />
                </div>
                <button type="submit" className="submit-button">Start Ticketing</button>
            </form>
        </div>
    );
};

export default App;
