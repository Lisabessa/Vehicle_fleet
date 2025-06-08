package com.example.vehicle_fleet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Map<LocalDate, Long> countCarsByRegistrationDate() {
        List<Car> cars = repo.findAll();

        Map<LocalDate, Long> countsByDate = cars.stream()
                .filter(car -> car.getRegistrationDate() != null)
                .collect(Collectors.groupingBy(Car::getRegistrationDate, Collectors.counting()));

        return countsByDate;
    }
}
