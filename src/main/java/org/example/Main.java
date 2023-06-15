package org.example;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static Prvek posledni;
    public static Prvek start;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File f = new File("data.dat");
        Scanner sc = new Scanner(System.in);

        if (f.exists()) {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

            //načítání prvků ze souboru
            start = (Prvek)ois.readObject();
            Prvek nacitany = start;

            while(nacitany.dalsi != null) {
                nacitany.dalsi = (Prvek)ois.readObject();
                nacitany = nacitany.dalsi;
            }

            posledni = nacitany;
        } else {
            Prvek novy = new Prvek(sc.nextInt(), null, null);
            posledni = novy;
            start = novy;
        }

        for(;;) {
            int hodnota = sc.nextInt();

            if(hodnota == 0) {
                break;
            }

            Prvek p = new Prvek(hodnota, null, null);

            //správné zařazení prvku do seznamu
            if(p.hodnota <= start.hodnota) {
                //prvek má nejmenší hodnotu
                start.predchozi = p;
                p.dalsi = start;
                start = p;
            } else if(p.hodnota > posledni.hodnota) {
                //prvek má největší hodnotu
                posledni.dalsi = p;
                p.predchozi = posledni;
                posledni = p;
            } else {
                Prvek porovnavaci = posledni;

                while(porovnavaci != null) {
                    //hledání správného místa pro prvek
                    if(porovnavaci.hodnota <= p.hodnota) {
                        p.dalsi = porovnavaci.dalsi;
                        p.dalsi.predchozi = p;
                        porovnavaci.dalsi = p;
                        p.predchozi = porovnavaci;
                        break;
                    } else {
                        porovnavaci = porovnavaci.predchozi;
                    }
                }
            }
        }

        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        //vypsání seznamu a uložení seznamu do souboru
        while(start != null) {
            System.out.println(start);
            oos.writeObject(start);
            start = start.dalsi;
        }

        oos.close();

    }
}