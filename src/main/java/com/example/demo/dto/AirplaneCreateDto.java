package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AirplaneCreateDto {
    private String name;
    private String f_s_number;
    private int airCompanyId;

    private int flight_number;
    private int flight_distance;
    private double fuel_capacity;
    private String type;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String created_at;
}
