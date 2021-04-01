package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AirCompanyCreateDto {

    private String name;
    private String company_type;
    private String founded_at;
}
