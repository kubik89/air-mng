package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Airplane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Field NAME cannot be empty ")
    private String name;
    private String f_s_number;

    @NotNull(message = "Field airCompanyId cannot be empty ")
    private int airCompanyId;
    private int flight_number;
    private int flight_distance;
    private double fuel_capacity;
    private String type;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String created_at;
}
