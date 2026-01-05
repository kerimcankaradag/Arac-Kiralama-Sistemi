package com.arackiralama.service;

import java.util.ArrayList;
import java.util.List;
import com.arackiralama.model.Car;

public class CarInventory {

    private List<Car> cars;

    public CarInventory() {
        cars = new ArrayList<>();
    }

    public void aracEkle(Car car) {
        cars.add(car);
    }

    public void musaitAraclariListele() {
        for (Car car : cars) {
            if (car.isMusait()) {
                System.out.println(car.getMarka());
            }
        }
    }

    // 🔴 BU METOT YOKSA MAIN HATA VERİR
    public List<Car> getCars() {
        return cars;
    }
}
