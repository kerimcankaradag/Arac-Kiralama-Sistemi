package com.arackiralama.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.arackiralama.model.Car;
import com.arackiralama.model.ElectricCar;

class CarBusinessLogicTest {

    private Car car;

    // Her testten önce temiz bir araba oluşturulur
    @BeforeEach
    void setUp() {
        car = new ElectricCar("34ABC01", "Tesla", 300, 1000);
    }

    @Test
    void yeniOlusturulanAracMusaitOlmalidir() {
        // THEN
        assertTrue(car.isMusait(), "Yeni oluşturulan araç müsait olmalı");
    }

    @Test
    void aracKiralandigindaDurumKiradaOlmalidir() {
        // WHEN
        car.kirala();

        // THEN
        assertEquals(Car.Durum.KIRADA, car.getDurum(),
                "Kiralanan aracın durumu KIRADA olmalı");
    }

    @Test
    void hasarliAracKiralanamamali() {
        // GIVEN
        car.setDurum(Car.Durum.HASARLI);

        // THEN
        assertFalse(car.isMusait(),
                "Hasarlı araç kiralanabilir olmamalı");
    }

    @Test
    void kiralamaUcretiGunSayisinaGoreDogruHesaplanmali() {
        // WHEN
        double toplamUcret = car.calculateRentalFee(3);

        // THEN
        assertEquals(3000, toplamUcret,
                "3 gün için toplam ücret 3000 TL olmalı");
    }

    @Test
    void iadeEdilenAracTekrarMusaitOlmalidir() {
        // GIVEN
        car.kirala();

        // WHEN
        car.iadeEt();

        // THEN
        assertTrue(car.isMusait(),
                "İade edilen araç tekrar müsait olmalı");
    }
}
