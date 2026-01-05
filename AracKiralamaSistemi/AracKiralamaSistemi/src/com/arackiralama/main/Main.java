package com.arackiralama.main;

import java.util.*;
import com.arackiralama.model.*;
import com.arackiralama.service.CarInventory;
import com.arackiralama.payment.*;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static CarInventory inventory = new CarInventory();

    public static void main(String[] args) {

        // Hazır araçlar
        inventory.aracEkle(new ElectricCar("34 AB 123", "Tesla", 300, 2500));
        inventory.aracEkle(new GasCar("06 CD 456", "Toyota", 160, 1500));
        inventory.aracEkle(new GasCar("35 EF 789", "Ford", 180, 1700));

        while (true) {
            int rol = menuSec(
                    "\n--- GİRİŞ ---",
                    "1- Yönetici",
                    "2- Müşteri",
                    "0- Çıkış"
            );

            if (rol == 0) {
                System.out.println("Program sonlandı.");
                break;
            } else if (rol == 1) {
                yoneticiPanel();
            } else if (rol == 2) {
                musteriGiris();
                musteriPanel();
            } else {
                System.out.println("Geçersiz seçim!");
            }
        }
    }

    /* ================= MÜŞTERİ GİRİŞ ================= */

    static void musteriGiris() {
        String ad, soyad;

        while (true) {
            System.out.print("İsim: ");
            ad = sc.nextLine();
            if (ad.matches("[A-Za-zÇĞİÖŞÜçğıöşü]+")) break;
            System.out.println("İsim sadece harf olmalı!");
        }

        while (true) {
            System.out.print("Soyisim: ");
            soyad = sc.nextLine();
            if (soyad.matches("[A-Za-zÇĞİÖŞÜçğıöşü]+")) break;
            System.out.println("Soyisim sadece harf olmalı!");
        }

        System.out.println("\nHoş geldiniz " + ad + " " + soyad);
    }

    /* ================= PANELLER ================= */

    static void yoneticiPanel() {
        while (true) {
            int secim = menuSec(
                    "\n--- YÖNETİCİ PANELİ ---",
                    "1- Araç Ekle",
                    "2- Araçları Listele",
                    "3- Araç Sil",
                    "4- Araç Durum Değiştir",
                    "0- Geri"
            );

            if (secim == 0) return;

            switch (secim) {
                case 1 -> aracEkle();
                case 2 -> listele();
                case 3 -> aracSil();
                case 4 -> durumDegistir();
                default -> System.out.println("Geçersiz seçim!");
            }
        }
    }

    static void musteriPanel() {
        while (true) {
            int secim = menuSec(
                    "\n--- MÜŞTERİ PANELİ ---",
                    "1- Araçları Listele",
                    "2- Araç Kirala",
                    "3- Araç İade Et",
                    "0- Geri"
            );

            if (secim == 0) return;

            switch (secim) {
                case 1 -> listele();
                case 2 -> kirala();
                case 3 -> iade();
                default -> System.out.println("Geçersiz seçim!");
            }
        }
    }

    /* ================= İŞLEMLER ================= */

    static void aracEkle() {
        int tip = menuSec("Araç Tipi", "1- Elektrikli", "2- Benzinli", "0- Geri");
        if (tip == 0) return;
        if (tip != 1 && tip != 2) {
            System.out.println("Geçersiz tip!");
            return;
        }

        String plaka = regexInput("Plaka (34 AB 123)", "\\d{2}\\s[A-Z]{1,3}\\s\\d{2,4}");
        String marka = regexInput("Marka", "[A-Za-z]+");
        int motor = intInput("Motor Gücü");
        double ucret = doubleInput("Günlük Ücret");

        if (tip == 1)
            inventory.aracEkle(new ElectricCar(plaka, marka, motor, ucret));
        else
            inventory.aracEkle(new GasCar(plaka, marka, motor, ucret));

        System.out.println("Araç eklendi.");
    }

    static void listele() {
        if (inventory.getCars().isEmpty()) {
            System.out.println("Araç yok.");
            return;
        }

        int i = 1;
        for (Car c : inventory.getCars()) {
            String yakit = (c instanceof ElectricCar) ? "Elektrik" : "Benzin";
            System.out.println(i++ + ") " + c.getPlaka()
                    + " | " + c.getMarka()
                    + " | " + c.getMotorGucu()
                    + " HP | " + yakit
                    + " | " + c.getDurum());
        }
    }

    static void aracSil() {
        if (inventory.getCars().isEmpty()) {
            System.out.println("Araç yok.");
            return;
        }

        listele();
        int sec = intInput("Silinecek araç no") - 1;

        if (sec >= 0 && sec < inventory.getCars().size()) {
            inventory.getCars().remove(sec);
            System.out.println("Araç silindi.");
        } else {
            System.out.println("Geçersiz seçim!");
        }
    }

    static void durumDegistir() {
        if (inventory.getCars().isEmpty()) {
            System.out.println("Araç yok.");
            return;
        }

        listele();
        int sec = intInput("Araç no") - 1;
        if (sec < 0 || sec >= inventory.getCars().size()) {
            System.out.println("Geçersiz araç!");
            return;
        }

        Car c = inventory.getCars().get(sec);
        int d = menuSec("Durum", "1- Müsait", "2- Kirada", "3- Hasarlı", "0- Geri");

        if (d == 0) return;
        if (d == 1) c.setDurum(Car.Durum.MUSAIT);
        else if (d == 2) c.setDurum(Car.Durum.KIRADA);
        else if (d == 3) c.setDurum(Car.Durum.HASARLI);
        else System.out.println("Geçersiz durum!");
    }

    static void kirala() {
        List<Car> musait = new ArrayList<>();
        for (Car c : inventory.getCars())
            if (c.isMusait()) musait.add(c);

        if (musait.isEmpty()) {
            System.out.println("Uygun araç yok.");
            return;
        }

        for (int i = 0; i < musait.size(); i++)
            System.out.println((i + 1) + ") " + musait.get(i).getPlaka());

        int sec = intInput("Araç no") - 1;
        if (sec < 0 || sec >= musait.size()) {
            System.out.println("Geçersiz seçim!");
            return;
        }

        int gun = intInput("Kaç gün");
        Car c = musait.get(sec);

        double toplam = c.calculateRentalFee(gun);

        int odeme = menuSec("Ödeme", "1- Kart", "2- Havale", "3- Nakit", "0- Geri");
        if (odeme == 0) return;
        if (odeme < 1 || odeme > 3) {
            System.out.println("Geçersiz ödeme!");
            return;
        }

        PaymentMethod pm =
                (odeme == 1) ? PaymentMethod.CREDIT_CARD :
                (odeme == 2) ? PaymentMethod.TRANSFER :
                               PaymentMethod.CASH;

        new Payment(pm, toplam).processPayment();
        c.kirala();

        System.out.println("Toplam Ücret: " + toplam + " TL");
    }

    static void iade() {
        List<Car> kirada = new ArrayList<>();
        for (Car c : inventory.getCars())
            if (c.getDurum() == Car.Durum.KIRADA) kirada.add(c);

        if (kirada.isEmpty()) {
            System.out.println("Kirada araç yok.");
            return;
        }

        for (int i = 0; i < kirada.size(); i++)
            System.out.println((i + 1) + ") " + kirada.get(i).getPlaka());

        int sec = intInput("İade edilecek no") - 1;
        if (sec < 0 || sec >= kirada.size()) {
            System.out.println("Geçersiz seçim!");
            return;
        }

        kirada.get(sec).iadeEt();
        System.out.println("Araç iade edildi.");
    }

    /* ================= YARDIMCI METOTLAR ================= */

    static int menuSec(String baslik, String... secenekler) {
        while (true) {
            System.out.println("\n" + baslik);
            for (String s : secenekler) System.out.println(s);
            System.out.print("Seçim: ");
            String g = sc.nextLine();
            if (g.matches("\\d+")) return Integer.parseInt(g);
            System.out.println("Hatalı giriş!");
        }
    }

    static int intInput(String mesaj) {
        while (true) {
            System.out.print(mesaj + ": ");
            String g = sc.nextLine();
            if (g.matches("\\d+")) return Integer.parseInt(g);
            System.out.println("Sayı giriniz!");
        }
    }

    static double doubleInput(String mesaj) {
        while (true) {
            System.out.print(mesaj + ": ");
            String g = sc.nextLine();
            if (g.matches("\\d+(\\.\\d+)?")) return Double.parseDouble(g);
            System.out.println("Geçerli sayı giriniz!");
        }
    }

    static String regexInput(String mesaj, String regex) {
        while (true) {
            System.out.print(mesaj + ": ");
            String g = sc.nextLine();
            if (g.matches(regex)) return g;
            System.out.println("Hatalı format!");
        }
    }
}
