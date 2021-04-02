package com.example.demo.service;

import com.example.demo.dto.AirCompanyCreateDto;
import com.example.demo.entity.AirCompany;

import java.util.List;

public interface IAirCompanyService {

    List<AirCompany> getAllAirCompanies ();

    AirCompany createAirCompany (AirCompanyCreateDto airCompanyCreateDto);

    void deleteCompanyById(int id);

    AirCompany updateAirCompany(int id, AirCompany airCompany);
}
