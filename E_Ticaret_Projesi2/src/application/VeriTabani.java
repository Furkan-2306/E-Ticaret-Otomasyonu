package application;
import java.sql.*;
import javax.swing.JOptionPane;
public class VeriTabani {
	public Connection baglanti;
	private String dataBaseIsmi = "e_ticaret";
	private String kullaniciAdi = "root";
	private String kullaniciSifre = "";
	
	
	public Connection baglan() {
		String url = "jdbc:mysql://localhost:3306/" + dataBaseIsmi; 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			baglanti = DriverManager.getConnection(url,kullaniciAdi,kullaniciSifre);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,e);
		}
		return baglanti;
	}
	
	public void kapt() {
	
		try {
			if (baglanti != null && !baglanti.isClosed()) 
                baglanti.close();
			}
		 catch (Exception e) {
			JOptionPane.showMessageDialog(null,e);
		}
	}
	
}
	
	

