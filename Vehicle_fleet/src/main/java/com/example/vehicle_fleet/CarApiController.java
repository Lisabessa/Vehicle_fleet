package com.example.vehicle_fleet;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/cars")
public class CarApiController {

    @Autowired
    private CarService carService;

    @GetMapping
    public List<Car> getCarsMethod(@RequestParam(required = false) String keyword) {
        return carService.listAll(keyword);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarByIdMethod(@PathVariable Long id) {
        Optional<Car> car = carService.getCar(id);
        return car.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createCarMethod(@Valid @RequestBody Car car) {

        try{
            carService.createCar(car);
            return new ResponseEntity<>("Автомобиль успешно создан.", HttpStatus.CREATED);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при создании автомобиля.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCarMethod(@PathVariable Long id, @Valid @RequestBody Car car) {
        Optional<Car> existingCar = carService.getCar(id);
        if(existingCar.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        car.setId(id);
        try{
            carService.updateCar(car);
            return ResponseEntity.ok("Данные об автомобиле успешно обновлены.");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка при обновлении данных автомобиля.");
        }
    }

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
