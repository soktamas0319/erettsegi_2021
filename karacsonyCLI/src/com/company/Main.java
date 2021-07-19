package com.company;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        try {
            RandomAccessFile raf = new RandomAccessFile("diszek.txt", "r");
            ArrayList<NapiBevetel> napiBevetelek = new ArrayList<>();
            try {
                int i = 0;
                for (String sor  = raf.readLine(); sor != null ; sor = raf.readLine()) {
                    NapiBevetel egyNapiBevetel = new NapiBevetel(sor);
                    napiBevetelek.add(egyNapiBevetel);
                    System.out.println(napiBevetelek.get(i).getNap());
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
