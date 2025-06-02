package application;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class SepetController {
	
	VeriTabani vt=new VeriTabani();
    PreparedStatement ps;
    ResultSet rs;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button anasayfa_button;

    @FXML
    private Button kategoriler_button;

    @FXML
    private TextField kuponField;

    @FXML
    private Button kuponUygulaButton;

    @FXML
    private Button profil;

    @FXML
    private Button sepet;

    @FXML
    private Button sepetiOnayla;

    @FXML
    private Label toplamFiyatLabel;

    @FXML
    private FlowPane urungetirme;
    
    @FXML
    private ScrollPane scrol;

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
    void kuponUygula_click(ActionEvent event) {
        String kupon = kuponField.getText().trim();
        String toplamYazi = toplamFiyatLabel.getText().replace("Toplam: ₺", "").replace("İndirimli Toplam: ₺", "").replace(",", ".");

        try {
            double toplam = Double.parseDouble(toplamYazi);

            // Veritabanındaki kuponu kontrol et
            String sql = "SELECT percentage FROM discount_codes WHERE LOWER(code) = LOWER(?)";
            ps = vt.baglan().prepareStatement(sql);
            ps.setString(1, kupon);
            rs = ps.executeQuery();

            if (rs.next()) {
                double indirimOrani = rs.getDouble("percentage") / 100.0; // yüzdeyi oran yap
                double yeniToplam = toplam * (1 - indirimOrani);
                toplamFiyatLabel.setText("İndirimli: ₺" + String.format("%.2f", yeniToplam));
            } else {
                toplamFiyatLabel.setText("Kupon Geçersiz");
            }

        } catch (Exception e) {
            toplamFiyatLabel.setText("Hata: Fiyat okunamadı");
            e.printStackTrace();
        }
    }

    

    @FXML
    void profil_button(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("KullaniciPanel.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Kullanıcı Giris");
            stage.setScene(new Scene(root));
            stage.show();
            ((Stage)profil.getScene().getWindow()).close();        
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
    void sepetiOnayla_click(ActionEvent event) {
        // Sepet boşsa uyarı göster
        if (SepetSingleton.getInstance().getUrunListesi().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Uyarı");
            alert.setHeaderText(null);
            alert.setContentText("Lütfen sepete ürün ekleyin.");
            alert.showAndWait();
            return; // devam etme
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Odeme.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Ödeme Sayfası");
            stage.setScene(new Scene(root));
            stage.show();

            // Sepeti temizle
            SepetSingleton.getInstance().getUrunListesi().clear();

            // Mevcut pencereyi kapat
            ((Stage) sepetiOnayla.getScene().getWindow()).close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    @FXML
    void initialize() {
        assert anasayfa_button != null : "fx:id=\"anasayfa_button\" was not injected: check your FXML file 'Sepet.fxml'.";
        assert kategoriler_button != null : "fx:id=\"kategoriler_button\" was not injected: check your FXML file 'Sepet.fxml'.";
        assert kuponField != null : "fx:id=\"kuponField\" was not injected: check your FXML file 'Sepet.fxml'.";
        assert kuponUygulaButton != null : "fx:id=\"kuponUygulaButton\" was not injected: check your FXML file 'Sepet.fxml'.";
        assert profil != null : "fx:id=\"profil\" was not injected: check your FXML file 'Sepet.fxml'.";
        assert sepet != null : "fx:id=\"sepet\" was not injected: check your FXML file 'Sepet.fxml'.";
        assert sepetiOnayla != null : "fx:id=\"sepetiOnayla\" was not injected: check your FXML file 'Sepet.fxml'.";
        assert toplamFiyatLabel != null : "fx:id=\"toplamFiyatLabel\" was not injected: check your FXML file 'Sepet.fxml'.";
        assert urungetirme != null : "fx:id=\"urungetirme\" was not injected: check your FXML file 'Sepet.fxml'.";
        assert scrol != null : "fx:id=\"scrol\" was not injected: check your FXML file 'Sepet.fxml'.";
        
        scrol.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());



        // ScrollPane içine FlowPane’i yerleştir
        scrol.setContent(urungetirme);
        scrol.setFitToWidth(true); // genişlik taşarsa yatay scrollbar çıkarmaz
        scrol.setPannable(true);   // mouse ile kaydırılabilirlik
        scrol.setPannable(true);
        scrol.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrol.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);


        double toplam = 0;

        for (urunGoster urun : SepetSingleton.getInstance().getUrunListesi()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("UrunSepet.fxml"));
                Parent urunNode = loader.load();

                UrunSepetController controller = loader.getController();
                controller.setUrunBilgileri(urun);

                urungetirme.getChildren().add(urunNode);

                toplam += Double.parseDouble(urun.getFiyat().replace(",", "."));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        toplamFiyatLabel.setText("Toplam: ₺" + String.format("%.2f", toplam));
    }


    
    
}
