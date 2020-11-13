package com.example.car.dealer.controllers;

import com.example.car.dealer.entities.Car;
import com.example.car.dealer.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @Secured({"ROLE_ADMIN", "ROLE_EDITOR", "ROLE_USER"})
    @GetMapping("")
    public ResponseEntity<List<Car>> findAllCars(@RequestParam(required = false) String brand, @RequestParam(required = false) String model, @RequestParam(required = false) boolean sortByYear_Model) {
        return ResponseEntity.ok(carService.getAllCar(brand, model, sortByYear_Model));
    }

    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @GetMapping("/{id}")
    public ResponseEntity<Car> findCarById(@PathVariable String id) {
        return ResponseEntity.ok(carService.getCarByID(id));
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping("")
    public ResponseEntity<Car> addCar(@Validated @RequestBody Car car) {
        return ResponseEntity.ok(carService.saveCar(car));
    }

    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
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

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCarById(@PathVariable String id) {
        carService.deleteCar(id);
    }
}
