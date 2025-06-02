package application;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class KullaniciKayitController implements Initializable  {
	
	VeriTabani vt = new VeriTabani();
	PreparedStatement ps;
	ResultSet rs;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField ad_tf;

    @FXML
    private TextField email_tf;

    @FXML
    private Button kaydolButton;

    @FXML
    private Hyperlink oturumgit;

    @FXML
    private TextField shownPassword;

    @FXML
    private PasswordField sifre_pf;

    @FXML
    private TextField soyad_tf;

    @FXML
    private Button toggleButton;


    @FXML
    void kaydolButton_click(ActionEvent event) {
    	try {
			vt.baglan();
			String ad = ad_tf.getText().trim();
			String soyad = soyad_tf.getText().trim();
			String email = email_tf.getText().trim();
			String sifre = sifre_pf.getText().trim();
			
			if(ad.isEmpty()||soyad.isEmpty()||email.isEmpty()||sifre.isEmpty()) {
				JOptionPane.showMessageDialog(null, "lütfen tüm alanları doldurun", "uyarı", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			 if (!ad.matches(".{3,}")) {
		            JOptionPane.showMessageDialog(null, "En az 3 karakter uzunluğunda olmalıdır", "Uyarı", JOptionPane.WARNING_MESSAGE);
		            return;
		        }
			
			 if (!email.endsWith("@gmail.com")) {
		            JOptionPane.showMessageDialog(null, "E-posta adresi @gmail.com ile bitmelidir", "Uyarı", JOptionPane.WARNING_MESSAGE);
		            return;
		        }

	        if (!sifre.matches("(?=.*[a-z])(?=.*[A-Z]).{6,}")) {
	            JOptionPane.showMessageDialog(null, "Şifre en az bir küçük harf, bir büyük harf içermelidir ve en az 6 karakter uzunluğunda olmalıdır", "Uyarı", JOptionPane.WARNING_MESSAGE);
	            return;
	        }
	        
	       
			String sorgu= "INSERT INTO kullanici (ad,soyad,email,sifre) VALUES (?,?,?,?)";
			ps=vt.baglan().prepareStatement(sorgu);
			ps.setString(1, ad);
			ps.setString(2, soyad);
			ps.setString(3, email);
			ps.setString(4, sifre);
			int sonuc=ps.executeUpdate();
			if(sonuc > 0) {
				JOptionPane.showInternalMessageDialog(null, "Başarılı kayıt");
				ad_tf.setText("");
				soyad_tf.setText("");
				email_tf.setText("");
				sifre_pf.setText("");
				FXMLLoader loader = new FXMLLoader(getClass().getResource("KullaniciGiris.fxml"));
	            Parent root = loader.load();
	            Stage stage = new Stage();
				stage.setTitle("Kullanıcı Giris");
				stage.setScene(new Scene(root));
				stage.show();
				((Stage)oturumgit.getScene().getWindow()).close();  
			}
			
			else {
				JOptionPane.showInternalMessageDialog(null, "Başarısız kayıt");
			}
			ps.close();
			vt.kapt();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Bir hata oluştu"+e);
		}


    }

    @FXML
    void oturumgit_click(ActionEvent event) {
    	try {
  		   
            FXMLLoader loader = new FXMLLoader(getClass().getResource("KullaniciGiris.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
			stage.setTitle("Kullanıcı Giris");
			stage.setScene(new Scene(root));
			stage.show();
			((Stage)oturumgit.getScene().getWindow()).close();        
   
    	} catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void toggleButton_click(ActionEvent event) {
    	boolean isVisible = shownPassword.isVisible();
        shownPassword.setVisible(!isVisible);
        sifre_pf.setVisible(isVisible);

        toggleButton.setText(isVisible ? "Şifreyi Göster" : "Şifreyi Gizle");

    }

    @FXML
    void initialize() {
        assert ad_tf != null : "fx:id=\"ad_tf\" was not injected: check your FXML file 'KullaniciKayit.fxml'.";
        assert email_tf != null : "fx:id=\"email_tf\" was not injected: check your FXML file 'KullaniciKayit.fxml'.";
        assert kaydolButton != null : "fx:id=\"kaydolButton\" was not injected: check your FXML file 'KullaniciKayit.fxml'.";
        assert oturumgit != null : "fx:id=\"oturumgit\" was not injected: check your FXML file 'KullaniciKayit.fxml'.";
        assert shownPassword != null : "fx:id=\"shownPassword\" was not injected: check your FXML file 'KullaniciKayit.fxml'.";
        assert sifre_pf != null : "fx:id=\"sifre_pf\" was not injected: check your FXML file 'KullaniciKayit.fxml'.";
        assert soyad_tf != null : "fx:id=\"soyad_tf\" was not injected: check your FXML file 'KullaniciKayit.fxml'.";
        assert toggleButton != null : "fx:id=\"toggleButton\" was not injected: check your FXML file 'KullaniciKayit.fxml'.";

    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Şifre alanlarının senkronizasyonu
        shownPassword.managedProperty().bind(shownPassword.visibleProperty());
        sifre_pf.managedProperty().bind(sifre_pf.visibleProperty());

        // Şifre yazıldıkça diğer alana da aynısı yazılır
        sifre_pf.textProperty().addListener((obs, oldText, newText) -> {
            shownPassword.setText(newText);
        });

        shownPassword.textProperty().addListener((obs, oldText, newText) -> {
            sifre_pf.setText(newText);
        });

        // Başlangıçta sadece PasswordField görünür
        shownPassword.setVisible(false);
        sifre_pf.setVisible(true);
    }
}
