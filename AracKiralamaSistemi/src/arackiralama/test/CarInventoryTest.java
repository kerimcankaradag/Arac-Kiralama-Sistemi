package com.arackiralama.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.arackiralama.service.CarInventory;
import com.arackiralama.model.*;

class CarInventoryTest {

    @Test
    void aracEklemeCalismali() {
        CarInventory inventory = new CarInventory();
        inventory.aracEkle(new GasCar("34 AB 123", "Toyota", 160, 1500));

        assertEquals(1, inventory.getCars().size());
    }

    @Test
    void aracListesiBosBaslamali() {
        CarInventory inventory = new CarInventory();
        assertTrue(inventory.getCars().isEmpty());
    }
}
