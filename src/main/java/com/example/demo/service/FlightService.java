package com.example.demo.service;

import com.example.demo.dao.AirCompanyRepository;
import com.example.demo.dao.AirPlaneRepository;
import com.example.demo.dao.BadRequestException;
import com.example.demo.dao.FlightRepository;
import com.example.demo.dto.FlightCreateDto;
import com.example.demo.dto.FlightNoStatusCreateDro;
import com.example.demo.entity.Flight;
import com.example.demo.entity.FlightStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@Service
public class FlightService implements IFlightService {

    FlightRepository flightRepository;
    AirCompanyRepository airCompanyRepository;
    AirPlaneRepository airPlaneRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository,
                         AirCompanyRepository airCompanyRepository,
                         AirPlaneRepository airPlaneRepository) {
        this.flightRepository = flightRepository;
        this.airCompanyRepository = airCompanyRepository;
        this.airPlaneRepository = airPlaneRepository;
    }

    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @Override
    public List<Flight> getAllFlightsByStatus(FlightStatus flightStatus, String companyName) {
        System.out.println(flightStatus);
        return flightRepository.findFlightByStatus(flightStatus, companyName);

    }

    @Override
    public Flight addFlight(FlightCreateDto flight) {
        return flightRepository.saveAndFlush(createFlight(flight));
    }

    @Override
    public Flight addNewFlightWithStatusPending(FlightNoStatusCreateDro flight) {

        Flight newFlight = new Flight();

        newFlight.setFlight_status(FlightStatus.PENDING);

        airCompanyRepository.findAll().forEach(company -> {
            if (company.getId() == flight.getAirCompanyId()) {
                newFlight.setAirCompany(company);
            }
        });

        airPlaneRepository.findAll().forEach(airPlane -> {
            if (airPlane.getId() == flight.getAirplaneId()) {
                newFlight.setAirplane(airPlane);
            }
        });

        newFlight.setDep_country(flight.getDep_country());
        newFlight.setDest_country(flight.getDest_country());
        newFlight.setDistance(flight.getDistance());
        newFlight.setStarted_at(flight.getStarted_at());
        newFlight.setEst_flight_time(flight.getEst_flight_time());
        newFlight.setEnded_at(flight.getEnded_at());
        newFlight.setDelay_started_at(flight.getDelay_started_at());
        newFlight.setCreated_at(flight.getCreated_at());
        return flightRepository.saveAndFlush(newFlight);
    }

    public List<Flight> getActiveFlightsStarted24hAgo () {
        return flightRepository.getActiveFlightsStarted24hAgo();
    }

    private Flight createFlight(FlightCreateDto flight) {
        Flight newFlight = new Flight();

        int newStatus = -1;
        for (int i = 0; i < FlightStatus.values().length; i++) {
            if (FlightStatus.values()[i].ordinal() == flight.getFlight_status()) {
                newFlight.setFlight_status(FlightStatus.values()[i]);
                newStatus = FlightStatus.values()[i].ordinal();
            }
        }
        if (newStatus == -1) {
            throw new BadRequestException("not correct flight STATUS");
        }

        airCompanyRepository.findAll().forEach(company -> {
            if (company.getId() == flight.getAirCompanyId()) {
                newFlight.setAirCompany(company);
            }
        });

        airPlaneRepository.findAll().forEach(airPlane -> {
            if (airPlane.getId() == flight.getAirplaneId()) {
                newFlight.setAirplane(airPlane);
            }
        });

        newFlight.setDep_country(flight.getDep_country());
        newFlight.setDest_country(flight.getDest_country());
        newFlight.setDistance(flight.getDistance());
        newFlight.setStarted_at(flight.getStarted_at());
        newFlight.setEst_flight_time(flight.getEst_flight_time());
        newFlight.setEnded_at(flight.getEnded_at());
        newFlight.setDelay_started_at(flight.getDelay_started_at());
        newFlight.setCreated_at(flight.getCreated_at());
        return newFlight;
    }
}
