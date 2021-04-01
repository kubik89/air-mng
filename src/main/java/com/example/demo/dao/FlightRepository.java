package com.example.demo.dao;

import com.example.demo.entity.Flight;
import com.example.demo.entity.FlightStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
    @Query("select fl from Flight fl where fl.flight_status = :status and fl.airCompany.name like :companyName")
    List<Flight> findFlightByStatus(FlightStatus status, String companyName);

    @Query("select flight from Flight flight where flight.flight_status = 0 and " +
            "current_time - flight.started_at < 24")
    List<Flight> getActiveFlightsStarted24hAgo();
}
