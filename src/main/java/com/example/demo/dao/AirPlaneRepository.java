package com.example.demo.dao;

import com.example.demo.entity.AirCompany;
import com.example.demo.entity.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AirPlaneRepository extends JpaRepository<Airplane, Integer> {
    @Query("select ap from Airplane ap where ap.id = :airplaneId")
    Airplane findAirplaneById(int airplaneId);
}
