package com.example.car.dealer.repositories;

import com.example.car.dealer.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, String> {
}
