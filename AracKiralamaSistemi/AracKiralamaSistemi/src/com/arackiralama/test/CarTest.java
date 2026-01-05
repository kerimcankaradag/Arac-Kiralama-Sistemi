package com.arackiralama.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.arackiralama.model.Car;
import com.arackiralama.model.ElectricCar;

public class CarTest {

    @Test
    void aracIlkDurumdaMusaitMi() {
        Car car = new ElectricCar("34ABC01", "Tesla", 300, 1000);
        assertTrue(car.isMusait());
    }

    @Test
    void aracKiralanincaDurumDegisirMi() {
        Car car = new ElectricCar("34ABC02", "BMW", 250, 800);
        car.kirala();
        assertEquals(Car.Durum.KIRADA, car.getDurum());
    }

    @Test
    void ucretDogruHesaplaniyorMu() {
        Car car = new ElectricCar("34ABC03", "Audi", 200, 500);
        double toplam = car.calculateRentalFee(3);
        assertEquals(1500, toplam);
    }
}
