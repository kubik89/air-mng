package com.example.demo.service;

import com.example.demo.controller.AirCompanyController;
import com.example.demo.dao.AirCompanyRepository;
import com.example.demo.dao.AirPlaneRepository;
import com.example.demo.dao.BadRequestException;
import com.example.demo.dto.AirplaneCreateDto;
import com.example.demo.dto.AirplaneMigrateDto;
import com.example.demo.entity.AirCompany;
import com.example.demo.entity.Airplane;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AirPlaneService implements IAirPlaneService {

    AirPlaneRepository airPlaneRepository;
    AirCompanyRepository airCompanyRepository;


    @Autowired
    public AirPlaneService(AirPlaneRepository airPlaneRepository, AirCompanyRepository airCompanyRepository) {
        this.airPlaneRepository = airPlaneRepository;
        this.airCompanyRepository = airCompanyRepository;
    }

    private static Logger logger = LoggerFactory.getLogger(AirCompanyController.class);

    @Override
    public List<Airplane> getAllAirPlanes() {
        return airPlaneRepository.findAll();
    }

    @Override
    public void deleteAirplaneById(int id) {
        airPlaneRepository.deleteById(id);
    }

    @Override
    public Airplane updateAirPlane(Airplane airPlane) {
        return null;
    }

    // перевірити ще чи такий по імені не існує як повторне значення
    public Airplane createAirplane(AirplaneCreateDto airplane) {
        Airplane newAirplane = new Airplane();

        newAirplane.setName(airplane.getName());
        newAirplane.setF_s_number(airplane.getF_s_number());
        newAirplane.setAirCompanyId(airplane.getAirCompanyId());
        newAirplane.setFlight_number(airplane.getFlight_number());
        newAirplane.setFlight_distance(airplane.getFlight_distance());
        newAirplane.setFuel_capacity(airplane.getFuel_capacity());
        newAirplane.setType(airplane.getType());
        newAirplane.setCreated_at(airplane.getCreated_at());

        return airPlaneRepository.saveAndFlush(newAirplane);
    }

    @Override
    public Airplane moveToAnotherCompany(AirplaneMigrateDto airplaneMigrateDto) {

        Airplane airplane = new Airplane();
        Airplane airplane1 = airPlaneRepository.findAirplaneById(airplaneMigrateDto.getAirplaneId());
        if (airplane1 == null) {
            logger.error("Airplane does not exist in DB");
            return null;
        } else {

            AirCompany airCompanyByName = airCompanyRepository.findAirCompanyByName(airplaneMigrateDto.getAirCompanyName());

            if (airCompanyByName == null) {
                logger.error("Air company does not exist in DB");
                return null;
            } else
                airplane.setId(airplane1.getId());
            airplane.setName(airplane1.getName());
            airplane.setF_s_number(airplane1.getF_s_number());
            airplane.setAirCompanyId(airCompanyByName.getId());
            airplane.setFlight_number(airplane1.getFlight_number());
            airplane.setFlight_distance(airplane1.getFlight_distance());
            airplane.setFuel_capacity(airplane1.getFuel_capacity());
            airplane.setType(airplane1.getType());
            airplane.setCreated_at(airplane1.getCreated_at());
            return airPlaneRepository.saveAndFlush(airplane);
        }
    }
}
