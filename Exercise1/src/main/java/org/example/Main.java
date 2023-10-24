package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello word!");

        String ortaMetin ="İlginizi çekebilir";
        String altMetin ="Vade Süresi";

        System.out.println(ortaMetin);
        System.out.println(altMetin);

        int vade = 23;
        double dolarDun = 27.98;
        double dolarBugun = 28.98;

        boolean dolarDustuMu = false;
        String okYonu = "";

        if (dolarBugun < dolarDun) {
            okYonu ="down.svg";
            System.out.println(okYonu);
        } else if (dolarBugun > dolarDun) {
            okYonu ="up.svg";
            System.out.println(okYonu);
        } else {
            okYonu="equal.svg";
            System.out.println(okYonu);
        }

        String[] krediler = {"Hızlı Kredi","Maaşını Halkbanktan","Mutlu Emekli"};

        for (int i = 0; i < krediler.length; i++) {
            System.out.println(krediler[i]);
        }
    }
}