package application;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;


public class UrunKartController {

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label fiyatLabel;

    @FXML
    private Label isimLabel;

    @FXML
    private ImageView resimView;

    @FXML
    private Button İncele;


    private urunGoster urun; 

    
    public void setUrun(urunGoster urun) {
        this.urun = urun;

        isimLabel.setText(urun.getUrun());
        fiyatLabel.setText("₺" + urun.getFiyat());

        // Burada tam yol verelim
        String basePath = "C:\\Users\\furka\\OneDrive\\Masaüstü\\pc gereçleri\\Yazılım\\java_klass\\E_Ticaret_Projesi2\\urunfotograflari\\";
        File resimDosyasi = new File(basePath + urun.getResim());

        System.out.println("Resim yolu: " + resimDosyasi.getAbsolutePath() + ", Dosya var mı? " + resimDosyasi.exists());

        if (resimDosyasi.exists()) {
            Image img = new Image(resimDosyasi.toURI().toString());
            resimView.setImage(img);
        } else {
            System.out.println("Resim bulunamadı: " + resimDosyasi.getAbsolutePath());
        }
    }



    @FXML
    private void inceleTiklandi(ActionEvent event) {
        if (urun == null) {
            System.out.println("HATA: Ürün nesnesi null!");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UrunDetay.fxml"));
            Parent root = loader.load();
            UrunDetayController detayController = loader.getController();
            detayController.setUrun(urun);
            Stage stage = new Stage();
            stage.setTitle("Ürün Detayı");
            stage.setScene(new Scene(root));
            stage.show();
            ((Stage)İncele.getScene().getWindow()).close();

        } catch (IOException e) {
            e.printStackTrace();
        }
       
    }


    @FXML
    void initialize() {
        assert fiyatLabel != null : "fx:id=\"fiyatLabel\" was not injected.";
        assert isimLabel != null : "fx:id=\"isimLabel\" was not injected.";
        assert resimView != null : "fx:id=\"resimView\" was not injected.";
        assert İncele != null : "fx:id=\"inceleButton\" was not injected.";
    }
}
