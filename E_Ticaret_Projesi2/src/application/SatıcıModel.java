package application;

public class SatıcıModel {
	private String id;
	private String satici_kod;
	private String ad;
	private String soyad;
	private String email;
	private String sifre;
	private boolean bloklu;
	
	public boolean getBloklu() {
		return bloklu;
	}
	
	public void setBloklu(boolean bloklu) {	
		this.bloklu = bloklu;
	}
	
	// getter ve setter kapsülleme
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSatici_kod() {
		return satici_kod;
	}
	public void setSatici_kod(String satici_kod) {
		this.satici_kod = satici_kod;
	}
	public String getAd() {
		return ad;
	}
	public void setAd(String ad) {
		this.ad = ad;
	}
	public String getSoyad() {
		return soyad;
	}
	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSifre() {
		return sifre;
	}
	public void setSifre(String sifre) {
		this.sifre = sifre;
	}
	
	
	

}
