package com.arackiralama.payment;

public class Payment {
    private PaymentMethod method;
    private double amount;

    public Payment(PaymentMethod method, double amount) {
       
    	 if (amount <= 0) {
    	        throw new IllegalArgumentException("Ödeme tutarı 0 veya negatif olamaz");
    	    }
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
