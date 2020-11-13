package com.example.car.dealer.services;

import com.example.car.dealer.entities.Car;
import com.example.car.dealer.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCar(String brand, String model,boolean sortByYear_Model) {

        List<Car> cars = carRepository.findAll();

        if (brand != null) {
            cars = cars.stream()
                    .filter(car -> car.getBrand().equalsIgnoreCase(brand))
                    .collect(Collectors.toList());
        }

        if (model != null) {
            cars = cars.stream()
                    .filter(car -> car.getModel().equalsIgnoreCase(model))
                    .collect(Collectors.toList());
        }

        if (sortByYear_Model) {
            cars.sort(Comparator.comparing(Car::getYear_model));
        }

        return cars;
    }

    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    public Car getCarByID(String id) {
        if (!carRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Could not find the car by id %s.", id));
        }
        return carRepository.findById(id).get();
    }

    public void deleteCar(String id) {
        if (!carRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Could not find the car by id %s.", id));
        }
        carRepository.deleteById(id);
    }
}
