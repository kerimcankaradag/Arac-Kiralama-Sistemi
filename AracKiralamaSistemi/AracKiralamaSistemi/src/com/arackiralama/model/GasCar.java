package com.arackiralama.model;

public class GasCar extends Car {

    public GasCar(String plaka, String marka, int motorGucu, double gunlukUcret) {
        super(plaka, marka, motorGucu, gunlukUcret);
    }

    @Override
    public double calculateRentalFee(int gun) {
        return gun * gunlukUcret;
    }
}
