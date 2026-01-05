	package com.arackiralama.model;

public class Rental {

    private Car car;
    private Customer customer;
    private int gun;

    public Rental(Car car, Customer customer, int gun) {
        this.car = car;
        this.customer = customer;
        this.gun = gun;
    }

    public void baslat() {
        if (car.isMusait()) {
            car.kirala();
            System.out.println(customer.getAd() + " " + customer.getSoyad()
                    + " aracı kiraladı.");
            System.out.println("Toplam ücret: "
                    + car.calculateRentalFee(gun));
        } else {
            System.out.println("Araç müsait değil!");
        }
    }

    public void bitir() {
        car.iadeEt();
        System.out.println("Araç iade edildi.");
    }
}
