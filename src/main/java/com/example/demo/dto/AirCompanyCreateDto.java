package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AirCompanyCreateDto {

    private String name;
    private String company_type;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String founded_at;
}
