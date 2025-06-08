package com.example.vehicle_fleet;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    @Query("SELECT p FROM Car p where CONCAT(p.model, ' ', p.manufactureYear, ' ', p.registrationDate, ' ', p.owner) LIKE %?1%")
    List<Car> SearchCar(String keyword);
}
