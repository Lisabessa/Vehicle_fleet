package com.example.vehicle_fleet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Car> getCar(Long id) {
        return repo.findById(id);
    }

    public void delete(Long id) {repo.deleteById(id);}
}
