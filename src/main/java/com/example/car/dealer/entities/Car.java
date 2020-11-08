package com.example.car.dealer.entities;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Car {

    @Id
    private String id;
    private String brand;
    private String model;
    private Integer year_model;
    private Integer weight;
    private Integer num_of_seats;
    private String equipment;

    public Car () {}

    public Car (String id, String brand, String model, Integer year_model, Integer weight, Integer num_of_seats, String equipment) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year_model = year_model;
        this.weight = weight;
        this.num_of_seats = num_of_seats;
        this.equipment = equipment;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear_model() {
        return this.year_model;
    }

    public void setYear_model(Integer year_model) {
        this.year_model = year_model;
    }

    public Integer getWeight() {
        return this.weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getNum_of_seats() {
        return this.num_of_seats;
    }

    public void setNum_of_seats(Integer num_of_seats) {
        this.num_of_seats = num_of_seats;
    }

    public String getEquipment() {
        return this.equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }
}
