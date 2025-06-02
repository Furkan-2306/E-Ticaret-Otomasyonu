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

public class SaticiGirisController implements Initializable {
	
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
    private Button geriButtona;

    @FXML
    private Hyperlink kaydolLink;

    @FXML
    private Button oturumAcButton;

    @FXML
    private ImageView password_eye;

    @FXML
    private TextField shownPassword;

    @FXML
    private PasswordField sifre_pf;

    @FXML
    private Button toggleButton;

    
    @FXML
    void geriButton_click(ActionEvent event) {
    	try {
 		   
            FXMLLoader loader = new FXMLLoader(getClass().getResource("KullaniciSecim.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
			stage.setTitle("Kullanıcı Giris");
			stage.setScene(new Scene(root));
			stage.show();
			((Stage)geriButtona.getScene().getWindow()).close();        
   
    	} catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void kaydolLink_click(ActionEvent event) {
    	try {
  		   
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SaticiKayit.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
			stage.setTitle("Satici Kayıt");
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

            String sorgu = "SELECT * FROM saticilar WHERE email=?";
            ps = vt.baglan().prepareStatement(sorgu);
            ps.setString(1, email);
            rs = ps.executeQuery();

            if (rs.next()) {
                boolean bloklu = rs.getBoolean("bloklu");
                String dogruSifre = rs.getString("sifre");

                if (bloklu) {
                    JOptionPane.showMessageDialog(null, "Bu hesap geçici olarak bloklanmıştır.");
                } else if (dogruSifre.equals(sifre)) {
                    JOptionPane.showMessageDialog(null, "Hoşgeldiniz :)");

                    String ad = rs.getString("ad");
                    String soyad = rs.getString("soyad");
                    String Email = rs.getString("email");
                    String saticiKod = rs.getString("satici_kod");
                    String saticiId = rs.getString("id");

                    GirisController.setGirisYapanSaticiId(saticiId);

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("SaticiPanel.fxml"));
                    Parent root = loader.load();

                    SaticiPanelController controller = loader.getController();
                    controller.setSaticiPanel(Email, ad, soyad, saticiKod);

                    Stage stage = new Stage();
                    stage.setTitle("Satıcı Paneli");
                    stage.setScene(new Scene(root));
                    stage.show();

                    ((Stage) oturumAcButton.getScene().getWindow()).close();
                } else {
                    JOptionPane.showMessageDialog(null, "Şifre yanlış :(");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Bu e-posta ile kayıtlı satıcı yok.");
            }

            rs.close();
            ps.close();
            vt.kapt();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hata: " + e.getMessage());
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
        assert email_tf != null : "fx:id=\"email_tf\" was not injected: check your FXML file 'SaticiGiris.fxml'.";
        assert geriButtona != null : "fx:id=\"geriButtona\" was not injected: check your FXML file 'SaticiGiris.fxml'.";
        assert kaydolLink != null : "fx:id=\"kaydolLink\" was not injected: check your FXML file 'SaticiGiris.fxml'.";
        assert oturumAcButton != null : "fx:id=\"oturumAcButton\" was not injected: check your FXML file 'SaticiGiris.fxml'.";
        assert password_eye != null : "fx:id=\"password_eye\" was not injected: check your FXML file 'SaticiGiris.fxml'.";
        assert shownPassword != null : "fx:id=\"shownPassword\" was not injected: check your FXML file 'SaticiGiris.fxml'.";
        assert sifre_pf != null : "fx:id=\"sifre_pf\" was not injected: check your FXML file 'SaticiGiris.fxml'.";
        assert toggleButton != null : "fx:id=\"toggleButton\" was not injected: check your FXML file 'SaticiGiris.fxml'.";

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
