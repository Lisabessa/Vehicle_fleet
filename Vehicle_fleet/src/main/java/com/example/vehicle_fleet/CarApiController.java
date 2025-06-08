package com.example.vehicle_fleet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@RestController
@RequestMapping("api/cars")
public class CarApiController {

    @Autowired
    private CarService carService;

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCarMethod(@PathVariable Long id){
        Optional<Car> car = carService.getCar(id);
        if(car.isEmpty()){
            return ResponseEntity.ok("Автомобиля с таким id не существует.");
        }

        try {
            carService.delete(id);
            return ResponseEntity.ok("Автомобиль успешно удален.");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка при удалении автомобиля.");
        }
    }


}
