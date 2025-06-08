package com.example.vehicle_fleet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    public Car getCar(String id) {
        return repo.findById(Long.valueOf(id)).get();
    }
    public Optional<Car> getCar(Long id) {
        return repo.findById(id);
    }

    public void createCar(Car car) {
        repo.save(car);
    }

    public void updateCar(Car car) { repo.save(car); }

    public void delete(Long id) {repo.deleteById(id);}

    public List<Car> getAllCarsSortedByManufactureYear(boolean ascending) {
        Sort sort = ascending ? Sort.by("manufactureYear").ascending() : Sort.by("manufactureYear").descending();
        return repo.findAll(sort);
    }
}
