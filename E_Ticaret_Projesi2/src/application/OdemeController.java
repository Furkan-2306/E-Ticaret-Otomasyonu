package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class OdemeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField Adres;

    @FXML
    private TextField Mahalle;

    @FXML
    private Button anasayfa_button;

    @FXML
    private Button backButton;

    @FXML
    private TextField cvv;

    @FXML
    private TextField il;

    @FXML
    private TextField ilçe;

    @FXML
    private TextField kartNoField;

    @FXML
    private TextField kartisim;

    @FXML
    private TextField karttarihi;

    @FXML
    private Button kategoriler_button;

    @FXML
    private TextField postaKodu;

    @FXML
    private Button profil;

    @FXML
    private Button sepet;

    @FXML
    private Button sipaiş;

    @FXML
    private TextField ulke;

    @FXML
    private Label ülke;
    

   

    

    @FXML
    void siparisiTamamla(ActionEvent event) {
        String adresText = Adres.getText().trim();
        String mahalleText = Mahalle.getText().trim();
        String ilText = il.getText().trim();
        String ilceText = ilçe.getText().trim();
        String postaKoduText = postaKodu.getText().trim();
        String ulkeText = ulke.getText().trim();

        String kartNo = kartNoField.getText().trim();
        String kartIsim = kartisim.getText().trim();
        String kartTarihi = karttarihi.getText().trim();
        String cvvText = cvv.getText().trim();

        if (adresText.isEmpty() || mahalleText.isEmpty() || ilText.isEmpty() || ilceText.isEmpty() ||
            postaKoduText.isEmpty() || ulkeText.isEmpty() || kartNo.isEmpty() || kartIsim.isEmpty() ||
            kartTarihi.isEmpty() || cvvText.isEmpty()) {
            showAlert(AlertType.ERROR, "Hata", "Lütfen tüm alanları doldurunuz.");
            return;
        }

        if (!kartNo.matches("\\d{16}")) {
            showAlert(AlertType.ERROR, "Hata", "Kart numarası 16 haneli olmalıdır.");
            return;
        }

        if (!cvvText.matches("\\d{3}")) {
            showAlert(AlertType.ERROR, "Hata", "CVV 3 haneli olmalıdır.");
            return;
        }

        if (!kartTarihi.matches("(0[1-9]|1[0-2])/\\d{2}")) {
            showAlert(AlertType.ERROR, "Hata", "Kart tarihi MM/YY formatında olmalıdır.");
            return;
        }

        // Eğer tüm kontroller geçtiyse
        showAlert(AlertType.INFORMATION, "Başarılı", "Siparişiniz tamamlandı!");

        // Ana sayfaya yönlendir
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AnaSayfa.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Ana Sayfa");
            stage.setScene(new Scene(root));
            stage.show();
            ((Stage) sipaiş.getScene().getWindow()).close(); // Mevcut pencereyi kapat
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    
    @FXML
    void anasayfa_button_click(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AnaSayfa.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Kullanıcı Giris");
            stage.setScene(new Scene(root));
            stage.show();
            ((Stage)anasayfa_button.getScene().getWindow()).close();        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void kategoriler_button_click(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Kategori.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Kullanıcı Giris");
            stage.setScene(new Scene(root));
            stage.show();
            ((Stage)kategoriler_button.getScene().getWindow()).close();        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void profil_button(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("KullaniciPanel.fxml"));
            Parent root = loader.load();

            // Controller'ı al
            KullaniciPanelController controller = loader.getController();

            // Kullanıcı ID'sini girisk sınıfından alıp set et
            controller.setKullaniciId(girisk.getKullaniciId());

            Stage stage = new Stage();
            stage.setTitle("Kullanıcı Panel");
            stage.setScene(new Scene(root));
            stage.show();

            // Mevcut pencereyi kapat
            ((Stage) profil.getScene().getWindow()).close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    
    

    @FXML
    void sepet_button(ActionEvent event) {
        try {
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Sepet.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Kullanıcı Giris");
            stage.setScene(new Scene(root));
            stage.show();
            ((Stage)sepet.getScene().getWindow()).close();        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        assert Adres != null : "fx:id=\"Adres\" was not injected: check your FXML file 'Odeme.fxml'.";
        assert Mahalle != null : "fx:id=\"Mahalle\" was not injected: check your FXML file 'Odeme.fxml'.";
        assert anasayfa_button != null : "fx:id=\"anasayfa_button\" was not injected: check your FXML file 'Odeme.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'Odeme.fxml'.";
        assert cvv != null : "fx:id=\"cvv\" was not injected: check your FXML file 'Odeme.fxml'.";
        assert il != null : "fx:id=\"il\" was not injected: check your FXML file 'Odeme.fxml'.";
        assert ilçe != null : "fx:id=\"ilçe\" was not injected: check your FXML file 'Odeme.fxml'.";
        assert kartNoField != null : "fx:id=\"kartNoField\" was not injected: check your FXML file 'Odeme.fxml'.";
        assert kartisim != null : "fx:id=\"kartisim\" was not injected: check your FXML file 'Odeme.fxml'.";
        assert karttarihi != null : "fx:id=\"karttarihi\" was not injected: check your FXML file 'Odeme.fxml'.";
        assert kategoriler_button != null : "fx:id=\"kategoriler_button\" was not injected: check your FXML file 'Odeme.fxml'.";
        assert postaKodu != null : "fx:id=\"postaKodu\" was not injected: check your FXML file 'Odeme.fxml'.";
        assert profil != null : "fx:id=\"profil\" was not injected: check your FXML file 'Odeme.fxml'.";
        assert sepet != null : "fx:id=\"sepet\" was not injected: check your FXML file 'Odeme.fxml'.";
        assert sipaiş != null : "fx:id=\"sipaiş\" was not injected: check your FXML file 'Odeme.fxml'.";
        assert ulke != null : "fx:id=\"ulke\" was not injected: check your FXML file 'Odeme.fxml'.";
        assert ülke != null : "fx:id=\"ülke\" was not injected: check your FXML file 'Odeme.fxml'.";

    }

}
