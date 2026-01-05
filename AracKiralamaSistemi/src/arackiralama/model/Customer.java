package com.arackiralama.model;

public class Customer {

    private String ad;
    private String soyad;

    public Customer(String ad, String soyad) {
        this.ad = ad;
        this.soyad = soyad;
    }

    public String getAd() {
        return ad;
    }

    public String getSoyad() {
        return soyad;
    }
}
