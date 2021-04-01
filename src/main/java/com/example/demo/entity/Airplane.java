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
public class Airplane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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
