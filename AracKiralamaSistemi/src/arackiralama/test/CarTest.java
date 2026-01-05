package com.arackiralama.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.arackiralama.model.GasCar;
import com.arackiralama.model.Car;

class CarTest {

    @Test
    void arabaIlkDurumdaMusaitOlmali() {
        Car car = new GasCar("34 AB 123", "Toyota", 120, 1000);
        assertEquals(Car.Durum.MUSAIT, car.getDurum());
    }

    @Test
    void musaitArabaKiralanabilir() {
        Car car = new GasCar("34 AB 123", "Toyota", 120, 1000);
        car.kirala();
        assertEquals(Car.Durum.KIRADA, car.getDurum());
    }

    @Test
    void kiradakiArabaTekrarKiralanamaz() {
        Car car = new GasCar("34 AB 123", "Toyota", 120, 1000);
        car.kirala();

        Exception ex = assertThrows(IllegalStateException.class, () -> {
            car.kirala();
        });

        assertEquals("Araç zaten kirada", ex.getMessage());
    }

    @Test
    void iadeEdilenArabaMusaitOlur() {
        Car car = new GasCar("34 AB 123", "Toyota", 120, 1000);
        car.kirala();
        car.iadeEt();

        assertEquals(Car.Durum.MUSAIT, car.getDurum());
    }
}
