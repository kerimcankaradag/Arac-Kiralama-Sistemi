package com.arackiralama.model;

public abstract class Car {

    // 🔹 DURUM ENUM (ŞART)
    public enum Durum {
        MUSAIT,
        KIRADA,
        HASARLI
    }

    protected String plaka;
    protected String marka;
    protected int motorGucu;
    protected double gunlukUcret;
    protected Durum durum;
    protected String hasarBilgisi;

    public Car(String plaka, String marka, int motorGucu, double gunlukUcret) {
        this.plaka = plaka;
        this.marka = marka;
        this.motorGucu = motorGucu;
        this.gunlukUcret = gunlukUcret;
        this.durum = Durum.MUSAIT;
        this.hasarBilgisi = "Yok";
    }

    // 🔹 METOTLAR (Main.java bunları çağırıyor)
    public boolean isMusait() {
        return durum == Durum.MUSAIT;
    }

    public void kirala() {
        this.durum = Durum.KIRADA;
    }

    public void iadeEt() {
        this.durum = Durum.MUSAIT;
    }

    public double calculateRentalFee(int gun) {
        return gun * gunlukUcret;
    }

    // 🔹 GETTER / SETTER
    public String getPlaka() { return plaka; }
    public String getMarka() { return marka; }
    public int getMotorGucu() { return motorGucu; }
    public double getGunlukUcret() { return gunlukUcret; }

    public Durum getDurum() { return durum; }
    public void setDurum(Durum durum) { this.durum = durum; }

    public String getHasarBilgisi() { return hasarBilgisi; }
    public void setHasarBilgisi(String hasarBilgisi) {
        this.hasarBilgisi = hasarBilgisi;
    }
}
