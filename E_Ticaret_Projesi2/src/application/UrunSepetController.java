package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UrunSepetController {

    @FXML
    private Label fiyatLabel;

    @FXML
    private Label isimLabel;

    @FXML
    private ImageView resimView;

    public void setUrunBilgileri(urunGoster urun) {
        isimLabel.setText(urun.getUrun());
        fiyatLabel.setText("₺" + urun.getFiyat());

        try {
            // Resimlerin bulunduğu klasör yolu (gerekiyorsa kendi bilgisayar yoluna göre düzenle)
            String resimKlasoru = "C:\\Users\\furka\\OneDrive\\Masaüstü\\pc gereçleri\\Yazılım\\java_klass\\E_Ticaret_Projesi2\\urunfotograflari\\";

            // Veritabanından gelen resim adı ile tam yolu oluştur
            String tamResimYolu = resimKlasoru + urun.getResim();

            // JavaFX Image nesnesi oluştur
            Image image = new Image("file:" + tamResimYolu);

            // Görseli ImageView içine yerleştir
            resimView.setImage(image);
        } catch (Exception e) {
            System.out.println("Resim yüklenemedi: " + urun.getResim());
        }
    }

}

