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


public class KullaniciGirisController implements Initializable {
	VeriTabani vt = new VeriTabani();
	PreparedStatement ps;
	ResultSet rs;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField email_tf;

    @FXML
    private Button geributton;

    @FXML
    private Hyperlink kaydolLink;

    @FXML
    private Button oturumAcButton;

    @FXML
    private TextField shownPassword;

    @FXML
    private PasswordField sifre_pf;

    @FXML
    private Button toggleButton;

    @FXML
    void geributton_click(ActionEvent event) {
    	try {
    		   
            FXMLLoader loader = new FXMLLoader(getClass().getResource("KullaniciSecim.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
			stage.setTitle("Kullanıcı Secim");
			stage.setScene(new Scene(root));
			stage.show();
			((Stage)geributton.getScene().getWindow()).close();        
   
    	} catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void kaydolLink_click(ActionEvent event) {
    	try {
 		   
            FXMLLoader loader = new FXMLLoader(getClass().getResource("KullaniciKayit.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
			stage.setTitle("Kullanıcı Kayıt");
			stage.setScene(new Scene(root));
			stage.show();
			((Stage)kaydolLink.getScene().getWindow()).close();        
   
    	} catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void oturumAcButton_click(ActionEvent event) {
        try {
            String email = email_tf.getText().trim();
            String sifre = sifre_pf.getText().trim();
            String sorgu = "SELECT * FROM kullanici WHERE email=? AND sifre=?";
            ps = vt.baglan().prepareStatement(sorgu);
            ps.setString(1, email);
            ps.setString(2, sifre);
            rs = ps.executeQuery();

            if (rs.next()) {
                int kullaniciId = rs.getInt(1);
                System.out.println("Giriş yapan kullanıcı ID: " + kullaniciId);

                girisk.setKullaniciId(kullaniciId); // ← BURASI DOĞRU KULLANIM

                JOptionPane.showMessageDialog(null, "Hoşgeldiniz :)");

                FXMLLoader loader = new FXMLLoader(getClass().getResource("AnaSayfa.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Kullanıcı Giriş");
                stage.setScene(new Scene(root));
                stage.show();
                ((Stage) oturumAcButton.getScene().getWindow()).close();
            }


            rs.close();
            ps.close();
            vt.kapt();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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
        assert email_tf != null : "fx:id=\"email_tf\" was not injected: check your FXML file 'KullaniciGiris.fxml'.";
        assert geributton != null : "fx:id=\"geributton\" was not injected: check your FXML file 'KullaniciGiris.fxml'.";
        assert kaydolLink != null : "fx:id=\"kaydolLink\" was not injected: check your FXML file 'KullaniciGiris.fxml'.";
        assert oturumAcButton != null : "fx:id=\"oturumAcButton\" was not injected: check your FXML file 'KullaniciGiris.fxml'.";
        assert shownPassword != null : "fx:id=\"shownPassword\" was not injected: check your FXML file 'KullaniciGiris.fxml'.";
        assert sifre_pf != null : "fx:id=\"sifre_pf\" was not injected: check your FXML file 'KullaniciGiris.fxml'.";
        assert toggleButton != null : "fx:id=\"toggleButton\" was not injected: check your FXML file 'KullaniciGiris.fxml'.";

        
        
            int id = girisk.getKullaniciId();

            try {
                PreparedStatement ps = vt.baglan().prepareStatement("SELECT * FROM kullanici WHERE id=?");
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    // kullanıcı bilgilerini çek
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
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
