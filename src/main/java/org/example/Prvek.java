package org.example;

import java.io.Serializable;

public class Prvek implements Serializable {
    int hodnota;
    Prvek predchozi;
    Prvek dalsi;

    public Prvek(int hodnota, Prvek predchozi, Prvek dalsi) {
        this.hodnota = hodnota;
        this.predchozi = predchozi;
        this.dalsi = dalsi;
    }

    @Override
    public String toString() {
        return "hodnota: " + hodnota;
    }
}
