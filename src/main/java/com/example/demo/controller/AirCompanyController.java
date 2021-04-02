package com.example.demo.controller;

import com.example.demo.dto.AirCompanyCreateDto;
import com.example.demo.entity.AirCompany;
import com.example.demo.service.IAirCompanyService;
import com.example.demo.validator.AirCompanyValidator;
import com.example.demo.validator.AirCompanyValidator2;
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
    public AirCompany createAirCompany (@RequestBody AirCompanyCreateDto airCompanyCreateDto) {
        logger.info("AirCompany with name " + airCompanyCreateDto.getName() + " - created successfully");
        return iAirCompanyService.createAirCompany(airCompanyCreateDto);
    }

    @PutMapping ("/{id}")
    public AirCompany updateAirCompany (@PathVariable int id, @RequestBody AirCompany airCompany) {
        return iAirCompanyService.updateAirCompany(id, airCompany);
    }

    @DeleteMapping ("/{id}")
    public void deleteAirCompany (@PathVariable int id) {
        iAirCompanyService.deleteCompanyById(id);
        logger.info("AirCompany with id " + id + " - deleted successfully");
    }
}
