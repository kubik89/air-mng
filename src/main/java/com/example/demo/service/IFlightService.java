package com.example.demo.service;

import com.example.demo.dto.FlightCreateDto;
import com.example.demo.entity.Flight;
import com.example.demo.entity.FlightStatus;

import java.util.List;

public interface IFlightService {

    List<Flight> getAllFlights();
    List<Flight> getAllFlightsByStatus(FlightStatus flightStatus, String companyName);
    Flight addFlight(FlightCreateDto flight);

    Flight addNewFlightWithStatusPending(FlightCreateDto flight);
}
