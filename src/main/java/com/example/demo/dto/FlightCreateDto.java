package com.example.demo.dto;

import com.example.demo.entity.FlightStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FlightCreateDto {
    private int flight_status;
    private int airCompanyId;
    private int airplaneId;
    private String dep_country;
    private String dest_country;
    private int distance;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private String est_flight_time;

    @DateTimeFormat(pattern = "HH:mm:ss")
    private String started_at;

    @DateTimeFormat(pattern = "HH:mm:ss")
    private String ended_at;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private String delay_started_at;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String created_at;
}
