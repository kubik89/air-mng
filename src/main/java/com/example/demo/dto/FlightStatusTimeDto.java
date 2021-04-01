package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.NamedEntityGraph;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FlightStatusTimeDto {
    private int flightStatus;
    private int flightId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String time;
}
