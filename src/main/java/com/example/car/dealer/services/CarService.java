package com.example.car.dealer.services;

import com.example.car.dealer.entities.Car;
import com.example.car.dealer.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCar() {
        return carRepository.findAll();
    }

    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    public Car getCarByID(String id) {
        return carRepository.findById(id).get();
    }

    public void deleteCar(String id) {
        carRepository.deleteById(id);
    }
}
