package com.company;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        try {
            RandomAccessFile raf = new RandomAccessFile("diszek.txt", "r");
            RandomAccessFile raf2 = new RandomAccessFile("bevetel.txt", "rw");
            ArrayList<NapiBevetel> napiTermelesEsEladas = new ArrayList<>();
            try {
                //arrayList feltöltés, kiírás
                int i = 0;
                for (String sor = raf.readLine(); sor != null; sor = raf.readLine()) {
                    NapiBevetel egyNapiBevetel = new NapiBevetel(sor);
                    napiTermelesEsEladas.add(egyNapiBevetel);
                    System.out.println(napiTermelesEsEladas.get(i).getNap() + " " + egyNapiBevetel.napiBevetel());
                    i++;
                }

                //összes dísz meghatározása
                int db = 0;
                for (int j = 0; j < napiTermelesEsEladas.size(); j++) {
                    db += napiTermelesEsEladas.get(j).napiKeszites();
                }
                System.out.println("4. feladat: Összesen " + db + " darab dísz készült.");

                //volt-e olyan nap, amikor nem készült dísz?
                i = 0;
                while ((i < napiTermelesEsEladas.size()) && napiTermelesEsEladas.get(i).napiKeszites() != 0) {
                    i++;
                }
                if (i < napiTermelesEsEladas.size()) {
                    System.out.println("5. feladat: Volt olyan nap, amikor egyetlen díszt sem készített: "
                            + napiTermelesEsEladas.get(i).getNap());
                } else {
                    System.out.println("5. feladat: Nem volt olyan nap, amikor egyetlen díszt sem készített.");
                }

                //egy adott napi készlet meghatározása
                int napSzama;
                Scanner sc = new Scanner(System.in);
                do {
                    System.out.print("Kérek egy számot 1 és 40 között:");
                    napSzama = sc.nextInt();
                } while ((napSzama < 1) || (napSzama > 40));
                int harangKeszlet = 0;
                int angyalkaKeszlet = 0;
                int fenyofaKeszlet = 0;
                for (i = 0; i <= napSzama - 1; i++) {
                    harangKeszlet += napiTermelesEsEladas.get(i).getHarangKesz() + napiTermelesEsEladas.get(i).getHarangEladott();
                    angyalkaKeszlet += napiTermelesEsEladas.get(i).getAngyalkaKesz() + napiTermelesEsEladas.get(i).getAngyalkaEladott();
                    fenyofaKeszlet += napiTermelesEsEladas.get(i).getFenyofaKesz() + napiTermelesEsEladas.get(i).getFenyofaEladott();
                }
                System.out.println("6. feladat");
                System.out.println("Harang: " + harangKeszlet + System.lineSeparator()
                        + "Angyalka: " + angyalkaKeszlet + System.lineSeparator()
                        + "Fenyőfa:" + fenyofaKeszlet);

                //legtöbbet eladott dísz
                int osszesEladottHarang = 0;
                int osszesEladottAngyalka = 0;
                int osszesEladottFenyofa = 0;
                for (int j = 0; j < napiTermelesEsEladas.size(); j++) {
                    osszesEladottHarang -= napiTermelesEsEladas.get(j).getHarangEladott();
                    osszesEladottAngyalka -= napiTermelesEsEladas.get(j).getAngyalkaEladott();
                    osszesEladottFenyofa -= napiTermelesEsEladas.get(j).getFenyofaEladott();
                }

                int max = Integer.max(Integer.max(osszesEladottAngyalka, osszesEladottFenyofa), osszesEladottHarang);
                System.out.println();
                System.out.println("7. feladat:");
                System.out.println("Legtöbbet eladott dísz:");
                if (max == osszesEladottHarang){
                    System.out.println("Harang: " + max);
                }
                if (max == osszesEladottAngyalka){
                    System.out.println("Angyalka: " + max);
                }
                if (max == osszesEladottFenyofa){
                    System.out.println("Fenyofa: " + max);
                }

                //8. feladat
                //10.000 Ft-os, vagy több napi bevételek fájlba írása
                int szamlalo = 0;
                for (NapiBevetel nb: napiTermelesEsEladas) {
                    if (nb.napiBevetel() >= 10000){
                        raf2.writeBytes( Integer.toString(nb.getNap())
                                + ":" + Integer.toString(nb.napiBevetel())
                                + System.lineSeparator());
                        szamlalo++;
                    }
                }
                raf2.writeBytes(Integer.toString(szamlalo) + " napon volt legalább 10.000 Ft a bevétel.");

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
