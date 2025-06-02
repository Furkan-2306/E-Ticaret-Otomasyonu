package application;

import java.util.ArrayList;
import java.util.List;

public class SepetSingleton {
    private static SepetSingleton instance;
    private final List<urunGoster> urunListesi = new ArrayList<>();

    private SepetSingleton() {}

    public static SepetSingleton getInstance() {
        if (instance == null) instance = new SepetSingleton();
        return instance;
    }

    public List<urunGoster> getUrunListesi() {
        return urunListesi;
    }

    public void urunEkle(urunGoster urun) {
        urunListesi.add(urun);
    }

    public void sepetiTemizle() {
        urunListesi.clear();
    }
}

