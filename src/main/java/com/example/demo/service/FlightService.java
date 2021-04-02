package com.example.demo.service;

import com.example.demo.controller.AirCompanyController;
import com.example.demo.dao.AirCompanyRepository;
import com.example.demo.dao.AirPlaneRepository;
import com.example.demo.dao.BadRequestException;
import com.example.demo.dao.FlightRepository;
import com.example.demo.dto.FlightCreateDto;
import com.example.demo.dto.FlightNoStatusCreateDro;
import com.example.demo.dto.FlightStatusTimeDto;
import com.example.demo.entity.Flight;
import com.example.demo.entity.FlightStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FlightService implements IFlightService {

    FlightRepository flightRepository;
    AirCompanyRepository airCompanyRepository;
    AirPlaneRepository airPlaneRepository;

    private static Logger logger = LoggerFactory.getLogger(AirCompanyController.class);

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

        Flight flight1 = setCompanyForFlight(flight.getAirCompanyId());
        newFlight.setAirCompany(flight1.getAirCompany());
//        airCompanyRepository.findAll().forEach(company -> {
//            if (company.getId() == flight.getAirCompanyId()) {
//                newFlight.setAirCompany(company);
//            }
//        });

        Flight flight2 = setAirplane(flight.getAirplaneId());
        newFlight.setAirplane(flight2.getAirplane());
//        airPlaneRepository.findAll().forEach(airPlane -> {
//            if (airPlane.getId() == flight.getAirplaneId()) {
//                newFlight.setAirplane(airPlane);
//            }
//        });

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

    public List<Flight> getActiveFlightsStarted24hAgo() {
        return flightRepository.getActiveFlightsStarted24hAgo();
    }

    @Override
    public void setStatus(FlightStatusTimeDto status) {

        int newStatus = -1;
        for (int i = 0; i < FlightStatus.values().length; i++) {
            if (FlightStatus.values()[i].ordinal() == status.getFlightStatus()) {
                newStatus = FlightStatus.values()[i].ordinal();
                if (flightRepository.existsById(status.getFlightId())) {
                    if (status.getFlightStatus() == 0) {
                        Flight flight = flightRepository.findById(status.getFlightId()).get();
                        flight.setFlight_status(FlightStatus.values()[i]);
                        flight.setDelay_started_at(status.getTime());
                        flightRepository.saveAndFlush(flight);
                    } else if (status.getFlightStatus() == 1) {
                        Flight flight = flightRepository.findById(status.getFlightId()).get();
                        flight.setFlight_status(FlightStatus.values()[i]);
                        flight.setEnded_at(status.getTime());
                        flightRepository.saveAndFlush(flight);
                    } else if (status.getFlightStatus() == 2) {
                        Flight flight = flightRepository.findById(status.getFlightId()).get();
                        flight.setFlight_status(FlightStatus.values()[i]);
                        flight.setDelay_started_at(status.getTime());
                        flightRepository.saveAndFlush(flight);
                    } else {
                        logger.error("Current flight status is not supported for current method");
                    }
                } else throw new IllegalArgumentException("Flight not found");
            }
        }
        if (newStatus == -1) {
            throw new BadRequestException("not correct flight STATUS");
        }
    }

    @Override
    public List<Flight> getCompFlightsWithDiffTime() {
        List<Flight> complFlights = flightRepository.getComplFlightsWithDiffInTime();
        List<Flight> flightList = new ArrayList<>();
        complFlights.forEach(flight -> {
            try {
                Date dateEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(flight.getEnded_at());
                Date dateStart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(flight.getStarted_at());
                Date timeEst = new SimpleDateFormat("HH:mm").parse(flight.getEst_flight_time());
                if ((dateEnd.getTime() - dateStart.getTime()) > timeEst.getTime()) {
                    System.out.println("Час " + ((dateEnd.getTime() - dateStart.getTime()) - timeEst.getTime()));
                    flightList.add(flight);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        return flightList;
    }

    private Flight setCompanyForFlight(int airCompanyId) {
        Flight newFlight = new Flight();
        airCompanyRepository.findAll().forEach(company -> {
            if (company.getId() == airCompanyId) {
                newFlight.setAirCompany(company);
            }
        });
        return newFlight;
    }

    private Flight setAirplane(int airplaneId) {
        Flight newFlight = new Flight();
        airPlaneRepository.findAll().forEach(airPlane -> {
            if (airPlane.getId() == airplaneId) {
                newFlight.setAirplane(airPlane);
            }
        });
        return newFlight;
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

        setCompanyForFlight(flight.getAirCompanyId());
//        airCompanyRepository.findAll().forEach(company -> {
//            if (company.getId() == flight.getAirCompanyId()) {
//                newFlight.setAirCompany(company);
//            }
//        });

        setAirplane(flight.getAirplaneId());
//        airPlaneRepository.findAll().forEach(airPlane -> {
//            if (airPlane.getId() == flight.getAirplaneId()) {
//                newFlight.setAirplane(airPlane);
//            }
//        });

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
