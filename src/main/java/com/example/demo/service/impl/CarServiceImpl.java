package com.example.demo.service.impl;


import com.example.demo.model.Car;
import com.example.demo.model.exceptions.DuplicateDataException;
import com.example.demo.repository.CarRepository;
import com.example.demo.service.CarSerivce;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class CarServiceImpl implements CarSerivce {

    @Autowired
    CarRepository carRepository;

    @Override
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @Override
    public Optional<Car> findById(int id) {
        return carRepository.findById(id);
    }

    @Override
    public Car save(Car car) throws DuplicateDataException {
        DuplicateDataException.check(carRepository.existsByVin(car.getVin()),"duplicate.car.vin");
        return carRepository.save(car);
    }

    @Override
    public Car update(Car car) throws DuplicateDataException {
        DuplicateDataException.check(carRepository.existsByVinAndIdNot(car.getVin(),car.getId()), "duplicate.car.vin");
        return carRepository.save(car);
    }
}

