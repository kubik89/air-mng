package com.example.demo.controller;

import com.example.demo.dto.FlightCreateDto;
import com.example.demo.dto.FlightNoStatusCreateDro;
import com.example.demo.dto.FlightStatusTimeDto;
import com.example.demo.entity.Flight;
import com.example.demo.entity.FlightStatus;
import com.example.demo.service.IFlightService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/flight")
@RestController
@Slf4j
public class FlightController {

    @Autowired
    private IFlightService iFlightService;

    private static Logger logger = LoggerFactory.getLogger(FlightController.class);

    @GetMapping
    public List<Flight> getAllFlights() {
        return iFlightService.getAllFlights();
    }

    @GetMapping("/{flightStatus}/{companyName}")
    public List<Flight> getFlightsByStatus(@PathVariable FlightStatus flightStatus, @PathVariable String companyName) {
        return iFlightService.getAllFlightsByStatus(flightStatus, companyName);
    }

    @GetMapping("/activeFlights24hAgo")
    public List<Flight> getActiveFlightsStarted24hAgo() {
        return iFlightService.getActiveFlightsStarted24hAgo();
    }

    @GetMapping("/compFlDiffTime")
    public List<Flight> getCompletedFlightsWithDiffInTime() {
        return iFlightService.getCompFlightsWithDiffTime();
    }


    @PostMapping
    public Flight addFlight(@RequestBody FlightCreateDto flight) {
        return iFlightService.addFlight(flight);
    }

    @PostMapping("/addNewFlightPending")
    public Flight addNewFlightWithStatusPending(@RequestBody FlightNoStatusCreateDro flight) {
        return iFlightService.addNewFlightWithStatusPending(flight);
    }

    @PostMapping("/setStatus")
    public void setFlightStatus(@RequestBody FlightStatusTimeDto status) {
        iFlightService.setStatus(status);
    }
}
