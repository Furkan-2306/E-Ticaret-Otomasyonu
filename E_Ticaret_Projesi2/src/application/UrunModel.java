package application;

public class UrunModel {

    private int urunId;
    private String urunAd;
    private int kategoriId;
    private String aciklama;
    private double fiyat;
    private int stok;
    private String resimYolu;
    private int saticiId;
    private Integer indirimOrani;
    private String indirimKodu;

    public UrunModel(int urunId, String urunAd, int kategoriId, String aciklama, double fiyat,
                     int stok, String resimYolu, int saticiId, Integer indirimOrani, String indirimKodu) {
        this.urunId = urunId;
        this.urunAd = urunAd;
        this.kategoriId = kategoriId;
        this.aciklama = aciklama;
        this.fiyat = fiyat;
        this.stok = stok;
        this.resimYolu = resimYolu;
        this.saticiId = saticiId;
        this.indirimOrani = indirimOrani;
        this.indirimKodu = indirimKodu;
    }

    // Getter'lar
    public int getUrunId() { return urunId; }

    public String getUrunAd() { return urunAd; }

    public int getKategoriId() { return kategoriId; }

    public String getAciklama() { return aciklama; }

    public double getFiyat() { return fiyat; }

    public int getStok() { return stok; }

    public String getResimYolu() { return resimYolu; }

    public int getSaticiId() { return saticiId; }

    public Integer getIndirimOrani() { return indirimOrani; }

    public String getIndirimKodu() { return indirimKodu; }
}
