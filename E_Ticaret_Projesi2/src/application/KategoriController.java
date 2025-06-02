package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class KategoriController {
	
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
    private TextField aramaKutusu;

    @FXML
    private Label fiyatLabel;

    @FXML
    private Button inceleButton;

    @FXML
    private Label isimLabel;

    @FXML
    private Button kategoriler_button;

    @FXML
    private Button profil;

    @FXML
    private ImageView resimView;

    @FXML
    private Button sepet;

    @FXML
    private GridPane urunListePaneli;


    @FXML
    private ListView<String> sonucListesi;

    private ObservableList<String> urunlerListesi = FXCollections.observableArrayList();
    
    @FXML
    private VBox kategoriKutu;

    @FXML
    private ScrollPane kategoriScroll;
    @FXML
    private ScrollPane urunscroll;
   
    
    private void kategorileriYukle() {
        String sql = "SELECT kategori_id, kategori_adi FROM kategoriler";

        try (Connection conn = vt.baglan();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int kategoriId = rs.getInt("kategori_id");
                String kategoriAdi = rs.getString("kategori_adi");

                Label adLabel = new Label(kategoriAdi);
                adLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
                adLabel.setPadding(new Insets(5, 10, 5, 10));
                adLabel.setCursor(Cursor.HAND); 

                // Hover efekti: altı çizili olsun
                adLabel.setOnMouseEntered(e -> adLabel.setUnderline(true));
                adLabel.setOnMouseExited(e -> adLabel.setUnderline(false));

                // Tıklanınca kategoriye ait ürünleri göster
                adLabel.setOnMouseClicked(e -> urunleriKategoriyeGoreGoster(kategoriId));

                kategoriKutu.getChildren().add(adLabel);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    // Arama işlevselliği
    @FXML
    void initialize() {
    	 assert anasayfa_button != null : "fx:id=\"anasayfa_button\" was not injected: check your FXML file 'AnaSayfa.fxml'.";
         assert aramaKutusu != null : "fx:id=\"aramaKutusu\" was not injected: check your FXML file 'AnaSayfa.fxml'.";
         assert fiyatLabel != null : "fx:id=\"fiyatLabel\" was not injected: check your FXML file 'AnaSayfa.fxml'.";
         assert inceleButton != null : "fx:id=\"inceleButton\" was not injected: check your FXML file 'AnaSayfa.fxml'.";
         assert isimLabel != null : "fx:id=\"isimLabel\" was not injected: check your FXML file 'AnaSayfa.fxml'.";
         assert kategoriler_button != null : "fx:id=\"kategoriler_button\" was not injected: check your FXML file 'AnaSayfa.fxml'.";
         assert profil != null : "fx:id=\"profil\" was not injected: check your FXML file 'AnaSayfa.fxml'.";
         assert resimView != null : "fx:id=\"resimView\" was not injected: check your FXML file 'AnaSayfa.fxml'.";
         assert sepet != null : "fx:id=\"sepet\" was not injected: check your FXML file 'AnaSayfa.fxml'.";
         assert sonucListesi != null : "fx:id=\"sonucListesi\" was not injected: check your FXML file 'AnaSayfa.fxml'.";
         assert urunListePaneli != null : "fx:id=\"urunListePaneli\" was not injected: check your FXML file 'AnaSayfa.fxml'.";
        
         urunleriKategoriyeGoreGoster(0); // 0 burada özel bir anlam taşıyacak: "tüm ürünler"
         urunscroll.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
         kategoriScroll.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
        
        kategoriScroll.setFitToWidth(true);
        kategoriScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        kategoriScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        kategorileriYukle();

        // ListView başlangıçta gizli olacak
        sonucListesi.setVisible(false);

        // Arama kutusuna yazılan metne göre listeyi filtrele
        aramaKutusu.textProperty().addListener((observable, eski, yeni) -> {
            if (yeni == null || yeni.isBlank()) {
                sonucListesi.setVisible(false);
                return;
            }

            try {
                // Veritabanından ürünleri çekme
                urunleriGetir(yeni);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        // Tıklanan öğe arama kutusuna yazılacak
        sonucListesi.setOnMouseClicked((event) -> {
            String secilen = sonucListesi.getSelectionModel().getSelectedItem();
            if (secilen != null) {
                aramaKutusu.setText(secilen);
                sonucListesi.setVisible(false);
            }
        });
        
     // Enter tuşuna basıldığında detay göster
        aramaKutusu.setOnAction(e -> {
            String arananUrun = aramaKutusu.getText().trim();
            if (!arananUrun.isEmpty()) {
                try {
                    urunDetayGoster(arananUrun);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    alertGoster("Hata", "Ürün detayları gösterilemedi.");
                }
            }
        });

    }
    
    private void urunDetayGoster(String urunAdi) throws Exception {
    	
    	
        String sql = """
            SELECT u.urun_id, u.ad, u.fiyat, u.aciklama, u.resim_yolu, u.stok, k.kategori_adi
            FROM urunler u
            JOIN kategoriler k ON u.kategori_id = k.kategori_id
            WHERE u.ad = ?
            """;

        try (Connection conn = vt.baglan();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, urunAdi);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // SQL'den gelen verileri al
                String id = String.valueOf(rs.getInt("urun_id"));
                String ad = rs.getString("ad");
                String fiyat = rs.getString("fiyat");
                String kategori = rs.getString("kategori_adi");
                String aciklama = rs.getString("aciklama");
                String resim = rs.getString("resim_yolu");
                String stok = rs.getString("stok");

                // Model nesnesi oluştur
                urunGoster urun = new urunGoster(id, ad, fiyat, kategori, aciklama, resim, stok);

                // FXML ve controller ile detay ekranını yükle
                FXMLLoader loader = new FXMLLoader(getClass().getResource("UrunDetay.fxml"));
                Parent root = loader.load();

                UrunDetayController controller = loader.getController();
                controller.setUrun(urun); // ürünü detay ekranına aktar

                // Yeni pencereyi göster
                Stage detayStage = new Stage();
                detayStage.setTitle("Ürün Detay");
                detayStage.setScene(new Scene(root));
                detayStage.show();

                // Arama penceresini kapat
                Stage mevcutStage = (Stage) aramaKutusu.getScene().getWindow();
                mevcutStage.close();

            } else {
                alertGoster("Bulunamadı", "Bu isimde bir ürün bulunamadı.");
            }
        }
    }



    
    private void alertGoster(String baslik, String mesaj) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(baslik);
        alert.setContentText(mesaj);
        alert.showAndWait();
    }

    private void urunleriGetir(String aramaKelimesi) throws SQLException {
        urunlerListesi.clear(); // Listeyi temizliyoruz

        // SQL sorgusuyla veritabanından ürünleri çekiyoruz
        String query = "SELECT ad FROM urunler WHERE ad LIKE ?";

        try (Connection connection = vt.baglan();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, "%" + aramaKelimesi + "%"); // Arama kelimesine göre filtreliyoruz
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                urunlerListesi.add(resultSet.getString("ad")); // Ürün adını listeye ekliyoruz
            }

            sonucListesi.setItems(urunlerListesi); // ListView'e ürünleri ekliyoruz
            sonucListesi.setVisible(!urunlerListesi.isEmpty()); // Liste boşsa görünür yapmıyoruz
        }
    }
    
    

    private List<urunGoster> urunleriKategoriIdIleGetir(int kategoriId) {
        List<urunGoster> urunler = new ArrayList<>();

        String sql = kategoriId == 0
                ? "SELECT * FROM urunler"
                : "SELECT * FROM urunler WHERE kategori_id = ?";

        try (Connection conn = vt.baglan();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (kategoriId != 0) {
                ps.setInt(1, kategoriId);
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                urunGoster urun = new urunGoster(
                    String.valueOf(rs.getInt("urun_id")),
                    rs.getString("ad"),
                    rs.getString("fiyat"),
                    String.valueOf(rs.getInt("kategori_id")),
                    rs.getString("aciklama"),
                    rs.getString("resim_yolu"),
                    rs.getString("stok")
                );
                urunler.add(urun);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return urunler;
    }

    
    @FXML
    private FlowPane urunFlowPane;


    
    private void urunleriKategoriyeGoreGoster(int kategoriId) {
        urunFlowPane.getChildren().clear();

        List<urunGoster> urunler = urunleriKategoriIdIleGetir(kategoriId);

        for (urunGoster urun : urunler) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("UrunKart.fxml"));
                Parent kart = loader.load();

                UrunKartController controller = loader.getController();
                controller.setUrun(urun);

                urunFlowPane.getChildren().add(kart);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    
    
    }
    

