package com.example.demo.controller;

import com.example.demo.dto.AirCompanyCreateDto;
import com.example.demo.entity.AirCompany;
import com.example.demo.service.IAirCompanyService;
import com.example.demo.validator.AirCompanyValidator;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/company")
@RestController
@Slf4j
public class AirCompanyController {

    @Autowired
    private IAirCompanyService iAirCompanyService;

    private static Logger logger = LoggerFactory.getLogger(AirCompanyController.class);


    @GetMapping
    public List<AirCompany> getAllCompanies () {
        return iAirCompanyService.getAllAirCompanies();
    }

    @PostMapping
    public AirCompany createAirCompany (@RequestBody @Valid AirCompanyCreateDto airCompanyCreateDto) {
        logger.info("AirCompany with name " + airCompanyCreateDto.getName() + " - created successfully");
        return iAirCompanyService.createAirCompany(airCompanyCreateDto);
    }

    @PutMapping
    public AirCompany updateAirCompany (@RequestBody AirCompany airCompany) {
        return iAirCompanyService.updateAirCompany(airCompany);
    }

    @DeleteMapping ("/{id}")
    public void deleteAirCompany (@PathVariable int id) {
        iAirCompanyService.deleteCompanyById(id);
        logger.info("AirCompany with id " + id + " - deleted successfully");
    }


    @InitBinder
    public void myInitBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new AirCompanyValidator());
    }
}
