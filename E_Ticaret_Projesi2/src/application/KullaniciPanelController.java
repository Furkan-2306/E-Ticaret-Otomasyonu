package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class KullaniciPanelController {
	VeriTabani vt=new VeriTabani();
    PreparedStatement ps;
    ResultSet rs;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchor;

    @FXML
    private Label kullanici_adi;
    
    @FXML
    private Label kullanici_eposta;

    @FXML
    private Label kullanici_soyadi;
    
    @FXML
    private TextField adGuncelle;
    
    @FXML
    private TextField epostaGuncelle;
    
    @FXML
    private TextField eskiEposta;
    
    @FXML
    private TextField sifreGuncelle;
    
    @FXML
    private TextField soyadGuncelle;
    
    @FXML
    private Button profilGuncelle;
    
    @FXML
    private TextArea adresTextArea;
    
    @FXML
    private TextArea adresGoster;
    
    @FXML
    private Button geri_button;
    @FXML
    private Button  cikisbt;
    
    private int kullaniciId;

    public void setKullaniciId(int kullaniciId) {
        this.kullaniciId = kullaniciId;
    }
    
    @FXML
    void cikisbt_click(ActionEvent event) {
    	
    	try {
  		   
            FXMLLoader loader = new FXMLLoader(getClass().getResource("KullaniciSecim.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
			stage.setTitle("Kullanıcı Giris");
			stage.setScene(new Scene(root));
			stage.show();
			((Stage)cikisbt.getScene().getWindow()).close();        
   
    	} catch (IOException e) {
            e.printStackTrace();
        }
    	
    }
    
    @FXML
    void geri_button_click(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AnaSayfa.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Kullanıcı Giris");
            stage.setScene(new Scene(root));
            stage.show();
            ((Stage)geri_button.getScene().getWindow()).close();        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    
  
    
 


    @FXML
    void profilGuncelle_click(ActionEvent event) {
	    String ad = adGuncelle.getText();
	    String soyad = soyadGuncelle.getText();
	    String eskiEmail = epostaGuncelle.getText();
	    String email = sifreGuncelle.getText();
	    String sifre = eskiEposta.getText();

	    if (ad.isEmpty() || soyad.isEmpty() || email.isEmpty() || sifre.isEmpty() || eskiEmail.isEmpty()) {
	        showAlert("Hata", "Lütfen tüm alanları doldurun.");
	        return;
	    }

	    String sql = "UPDATE kullanici SET ad = ?, soyad = ?, email = ?, sifre = ? WHERE email = ?";

	    try {
	        vt.baglan();
	        ps = vt.baglanti.prepareStatement(sql);

	        ps.setString(1, ad);
	        ps.setString(2, soyad);
	        ps.setString(3, eskiEmail);
	        ps.setString(4, email);
	        ps.setString(5, sifre);

	        int updated = ps.executeUpdate();

	        if (updated > 0) {
	            showAlert("Başarılı", "Bilgiler güncellendi. Lütfen tekrar giriş yapın.");

	            // Admin giriş sayfasına yönlendir
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("KullaniciGiris.fxml"));
	            Parent root = loader.load();
	            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	            stage.setScene(new Scene(root));
	            stage.show();

	        } else {
	            showAlert("Hata", "Güncelleme yapılamadı. Eski e-posta bulunamadı.");
	        }

	        ps.close();
	        vt.kapt();

	    } catch (Exception e) {
	        e.printStackTrace();
	        showAlert("Hata", "Veritabanı hatası: " + e.getMessage());
	    }
    }

    @FXML
    void initialize() {
        int id = girisk.getKullaniciId();
        if (id == 0) return; // ID yoksa çık

        VeriTabani vt = new VeriTabani();
        try {
            String sql = "SELECT * FROM kullanici WHERE id=?";
            PreparedStatement ps = vt.baglan().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                kullanici_adi.setText(rs.getString("ad"));
                kullanici_soyadi.setText(rs.getString("soyad"));
                kullanici_eposta.setText(rs.getString("email"));
            }
            rs.close();
            ps.close();
            vt.kapt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert adGuncelle != null : "fx:id=\"adGuncelle\" was not injected: check your FXML file 'KullaniciPanel.fxml'.";
       
        assert adresTextArea != null : "fx:id=\"adresTextArea\" was not injected: check your FXML file 'KullaniciPanel.fxml'.";
        assert anchor != null : "fx:id=\"anchor\" was not injected: check your FXML file 'KullaniciPanel.fxml'.";
        assert epostaGuncelle != null : "fx:id=\"epostaGuncelle\" was not injected: check your FXML file 'KullaniciPanel.fxml'.";
        assert eskiEposta != null : "fx:id=\"eskiEposta\" was not injected: check your FXML file 'KullaniciPanel.fxml'.";
        assert geri_button != null : "fx:id=\"geri_button\" was not injected: check your FXML file 'KullaniciPanel.fxml'.";
        assert kullanici_adi != null : "fx:id=\"kullanici_adi\" was not injected: check your FXML file 'KullaniciPanel.fxml'.";
        assert kullanici_eposta != null : "fx:id=\"kullanici_eposta\" was not injected: check your FXML file 'KullaniciPanel.fxml'.";
        assert kullanici_soyadi != null : "fx:id=\"kullanici_soyadi\" was not injected: check your FXML file 'KullaniciPanel.fxml'.";
        assert profilGuncelle != null : "fx:id=\"profilGuncelle\" was not injected: check your FXML file 'KullaniciPanel.fxml'.";
        assert sifreGuncelle != null : "fx:id=\"sifreGuncelle\" was not injected: check your FXML file 'KullaniciPanel.fxml'.";
        assert soyadGuncelle != null : "fx:id=\"soyadGuncelle\" was not injected: check your FXML file 'KullaniciPanel.fxml'.";

    }
    
    


   
	public void setKullaniciPanel(String ad, String soyad, String email) {
        kullanici_adi.setText(ad);
        kullanici_soyadi.setText(soyad);
        kullanici_eposta.setText(email);

	}
	
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
       
}
