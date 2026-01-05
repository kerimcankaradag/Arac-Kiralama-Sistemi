package com.arackiralama.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.arackiralama.payment.*;

class PaymentTest {

    @Test
    void odemeNesnesiOlusmali() {
        Payment payment = new Payment(PaymentMethod.CREDIT_CARD, 2500);
        assertNotNull(payment);
    }

    @Test
    void negatifOdemeTutariOlmamali() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment(PaymentMethod.CASH, -100);
        });
    }
}
