package com.example.car.dealer.controllers;

import com.example.car.dealer.entities.Car;
import com.example.car.dealer.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("")
    public ResponseEntity<List<Car>> findAllCars() {
        return ResponseEntity.ok(carService.getAllCar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> findCarById(@PathVariable String id) {
        return ResponseEntity.ok(carService.getCarByID(id));
    }

    @PostMapping("")
    public ResponseEntity<Car> addCar(@Validated @RequestBody Car car) {
        return ResponseEntity.ok(carService.saveCar(car));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCarById(@Validated @RequestBody Car car, @PathVariable String id) {
        try {
            Car existsCar = carService.getCarByID(id);
            car.setId(id);
            carService.saveCar(car);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCarById(@PathVariable String id) {
        carService.deleteCar(id);
    }
}
