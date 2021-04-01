package com.example.demo.dao;

import com.example.demo.entity.AirCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AirCompanyRepository extends JpaRepository<AirCompany, Integer> {

    @Query("select ac from AirCompany ac where ac.name like :airCompanyName")
    AirCompany findAirCompanyByName(String airCompanyName);

}
