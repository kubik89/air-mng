package com.example.demo.service;

import com.example.demo.dto.AirCompanyCreateDto;
import com.example.demo.dto.AirplaneCreateDto;
import com.example.demo.dto.AirplaneMigrateDto;
import com.example.demo.entity.AirCompany;
import com.example.demo.entity.Airplane;

import java.util.List;

public interface IAirPlaneService {
    List<Airplane> getAllAirPlanes ();

    Airplane createAirplane (AirplaneCreateDto airPlaneCreateDto);

    void deleteAirplaneById(int id);

    Airplane updateAirPlane(Airplane airPlane);

    Airplane moveToAnotherCompany (AirplaneMigrateDto airplaneMigrateDto);
}
