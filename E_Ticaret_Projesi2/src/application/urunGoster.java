package application;

import javafx.beans.property.*;

public class urunGoster {
    private StringProperty id;
    private StringProperty urun;
    private StringProperty fiyat;
    private StringProperty kategori;
    private StringProperty aciklama;
    private StringProperty resim;
    private StringProperty stok;

    // Constructor (parametreli)
    public urunGoster(String id, String urun, String fiyat, String kategori, String aciklama, String resim, String stok) {
        this.id = new SimpleStringProperty(id);
        this.urun = new SimpleStringProperty(urun);
        this.fiyat = new SimpleStringProperty(fiyat);
        this.kategori = new SimpleStringProperty(kategori);
        this.aciklama = new SimpleStringProperty(aciklama);
        this.resim = new SimpleStringProperty(resim);
        this.stok = new SimpleStringProperty(stok);
    }




    // Property metodları (JavaFX için binding'e uygundur)
    public StringProperty idProperty() { return id; }
    public StringProperty urunProperty() { return urun; }
    public StringProperty fiyatProperty() { return fiyat; }
    public StringProperty kategoriProperty() { return kategori; }
    public StringProperty aciklamaProperty() { return aciklama; }
    public StringProperty resimProperty() { return resim; }
    public StringProperty stokProperty() { return stok; }

    // Normal getter ve setter metodları
    public String getId() { return id.get(); }
    public void setId(String id) { this.id.set(id); }

    public String getUrun() { return urun.get(); }
    public void setUrun(String urun) { this.urun.set(urun); }

    public String getFiyat() { return fiyat.get(); }
    public void setFiyat(String fiyat) { this.fiyat.set(fiyat); }

    public String getKategori() { return kategori.get(); }
    public void setKategori(String kategori) { this.kategori.set(kategori); }

    public String getAciklama() { return aciklama.get(); }
    public void setAciklama(String aciklama) { this.aciklama.set(aciklama); }

    public String getResim() { return resim.get(); }
    public void setResim(String resim) { this.resim.set(resim); }

    public String getStok() { return stok.get(); }
    public void setStok(String stok) { this.stok.set(stok); }
}
