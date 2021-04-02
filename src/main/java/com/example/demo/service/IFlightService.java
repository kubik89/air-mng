package com.example.demo.service;

import com.example.demo.dto.FlightCreateDto;
import com.example.demo.dto.FlightNoStatusCreateDro;
import com.example.demo.dto.FlightStatusTimeDto;
import com.example.demo.entity.Flight;
import com.example.demo.entity.FlightStatus;

import java.util.List;

public interface IFlightService {

    List<Flight> getAllFlights();
    List<Flight> getAllFlightsByStatus(FlightStatus flightStatus, String companyName);
    Flight addFlight(FlightCreateDto flight);

    Flight addNewFlightWithStatusPending(FlightNoStatusCreateDro flight);
    List<Flight> getActiveFlightsStarted24hAgo ();
    void setStatus (FlightStatusTimeDto status);
    List<Flight> getCompFlightsWithDiffTime();
}
