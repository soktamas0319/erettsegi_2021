package com.company;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {
        try {
            RandomAccessFile raf = new RandomAccessFile("diszek.txt", "r");
            ArrayList<NapiBevetel> napiTermelesEsEladas = new ArrayList<>();
            try {
                int i = 0;
                for (String sor = raf.readLine(); sor != null; sor = raf.readLine()) {
                    NapiBevetel egyNapiBevetel = new NapiBevetel(sor);
                    napiTermelesEsEladas.add(egyNapiBevetel);
                    System.out.println(napiTermelesEsEladas.get(i).getNap() + " " + egyNapiBevetel.napiBevetel());
                    i++;
                }


                int db = 0;
                for (int j = 0; j < napiTermelesEsEladas.size(); j++) {
                    db += napiTermelesEsEladas.get(j).napiKeszites();
                }
                System.out.println("4. feladat: Összesen " + db + " darab dísz készült." );

                i = 0;
                while ((i < napiTermelesEsEladas.size() ) &&  napiTermelesEsEladas.get(i).napiKeszites() != 0){
                    i++;
                }
                if (i < napiTermelesEsEladas.size()) {
                    System.out.println("5. feladat: Volt olyan nap, amikor egyetlen díszt sem készített: "
                            + napiTermelesEsEladas.get(i).getNap());
                } else {
                    System.out.println("5. feladat: Nem volt olyan nap, amikor egyetlen díszt sem készített.");
                }


            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
