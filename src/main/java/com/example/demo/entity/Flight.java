package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private FlightStatus flight_status;

    @ManyToOne
    private AirCompany airCompany;

    @ManyToOne
    private Airplane airplane;

    private String dep_country;
    private String dest_country;
    private int distance;

    @DateTimeFormat(pattern = "HH:mm")
    private String est_flight_time;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String started_at;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String ended_at;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String delay_started_at;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String created_at;
}
