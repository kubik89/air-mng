package com.example.demo.controller;

import com.example.demo.dto.AirplaneCreateDto;
import com.example.demo.dto.AirplaneMigrateDto;
import com.example.demo.entity.Airplane;
import com.example.demo.service.AirPlaneService;
import com.example.demo.validator.AirCompanyValidator;
import com.example.demo.validator.AirplaneMigrateValidator;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping (value = "/airplane")
@RestController
@Slf4j
public class AirPlaneController {

    @Autowired
    AirPlaneService airPlaneService;

    private static Logger logger = LoggerFactory.getLogger(AirCompanyController.class);

    @GetMapping
    public List<Airplane> getAllAirPlanes () {
        return airPlaneService.getAllAirPlanes();
    }

    @PostMapping
    public Airplane createAirplane (@RequestBody AirplaneCreateDto airplane) {
        return airPlaneService.createAirplane(airplane);
    }

    @DeleteMapping ("/{id}")
    public void deleteAirplaneById (@PathVariable int id) {
        airPlaneService.deleteAirplaneById(id);
    }

    @PostMapping("/migrate")
    public Airplane moveAirplaneToAnotherCompany (@RequestBody @Valid AirplaneMigrateDto airplaneMigrateDto) {
        return airPlaneService.moveToAnotherCompany(airplaneMigrateDto);
    }

}
