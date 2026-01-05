package com.arackiralama.payment;

public class Payment {
    private PaymentMethod method;
    private double amount;

    public Payment(PaymentMethod method, double amount) {
        this.method = method;
        this.amount = amount;
    }

    public boolean processPayment() {
        System.out.println("Ödeme Yöntemi: " + method);
        System.out.println("Tutar: " + amount + " TL");

        // Simülasyon: Nakit her zaman başarılı
        if (method == PaymentMethod.CASH) {
            return true;
        }

        // Diğerleri %80 başarılı
        return Math.random() > 0.2;
    }
}
