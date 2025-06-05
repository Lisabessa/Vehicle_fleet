package com.example.vehicle_fleet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository repo;

    public List<Car> listAll(String keyword) {
        if(keyword == null || keyword.isEmpty()){
            return repo.findAll();
        }
        return repo.SearchCar(keyword);
    }
}
