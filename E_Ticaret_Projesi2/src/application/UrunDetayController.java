package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;



import javafx.scene.control.TextField;





public class UrunDetayController {
	
	VeriTabani vt=new VeriTabani();
    PreparedStatement ps;
    ResultSet rs;
	

	 @FXML
	  private TextField aramaKutusu;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button anasayfa_button;

    @FXML
    private Button kategoriler_button;


    @FXML
    private Button profil;

    @FXML
    private Button sepet;
    @FXML
    private ListView<String> sonucListesi;

    private ObservableList<String> urunlerListesi = FXCollections.observableArrayList();

    @FXML private Label lblUrunAdi, lblFiyat, lblStok, lblKategori, lblIndirim;
    @FXML
    private Label txtAciklama;
    @FXML private ImageView imgUrun;
    @FXML private Button sepeteEkleButton;
   

    private urunGoster secilenUrun;

    public void setUrun(urunGoster urun) {
        this.secilenUrun = urun;

        lblUrunAdi.setText(urun.getUrun());
        lblFiyat.setText("Fiyat: ₺" + urun.getFiyat());
        lblStok.setText("Stok: " + urun.getStok());
        lblKategori.setText("Kategori: " + urun.getKategori());
        lblIndirim.setText("İndirim: "); // Varsayılan
        txtAciklama.setText(urun.getAciklama());

        if (urun.getResim() != null && !urun.getResim().isEmpty()) {
            try {
                String basePath = "C:\\Users\\furka\\OneDrive\\Masaüstü\\pc gereçleri\\Yazılım\\java_klass\\E_Ticaret_Projesi2\\urunfotograflari\\";
                File resimDosyasi = new File(basePath + urun.getResim());

                System.out.println("Detayda resim yolu: " + resimDosyasi.getAbsolutePath() + ", Dosya var mı? " + resimDosyasi.exists());

                if (resimDosyasi.exists()) {
                    Image image = new Image(resimDosyasi.toURI().toString(), false);
                    if (!image.isError()) {
                        Platform.runLater(() -> {
                            imgUrun.setFitWidth(200);
                            imgUrun.setFitHeight(200);
                            imgUrun.setPreserveRatio(true);
                            imgUrun.setImage(image);
                        });
                    } else {
                        System.out.println("Resim yüklenirken hata oluştu.");
                    }
                } else {
                    System.out.println("Resim dosyası bulunamadı.");
                }
            } catch (Exception e) {
                System.out.println("Resim yüklenemedi: " + e.getMessage());
            }
        }
    }

    @FXML
    private void sepeteEkle(ActionEvent event) {
        if (secilenUrun == null) {
            System.out.println("Hata: sepete eklenecek ürün seçilmedi.");
            return;
        }

        SepetSingleton.getInstance().getUrunListesi().add(secilenUrun);
        System.out.println("Sepete eklendi: " + secilenUrun.getUrun());
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

    
    @FXML
    void initialize() {
        assert anasayfa_button != null : "fx:id=\"anasayfa_button\" was not injected: check your FXML file 'UrunDetay.fxml'.";
        assert aramaKutusu != null : "fx:id=\"aramaKutusu\" was not injected: check your FXML file 'UrunDetay.fxml'.";
        assert imgUrun != null : "fx:id=\"imgUrun\" was not injected: check your FXML file 'UrunDetay.fxml'.";
        assert kategoriler_button != null : "fx:id=\"kategoriler_button\" was not injected: check your FXML file 'UrunDetay.fxml'.";
        assert lblFiyat != null : "fx:id=\"lblFiyat\" was not injected: check your FXML file 'UrunDetay.fxml'.";
        assert lblIndirim != null : "fx:id=\"lblIndirim\" was not injected: check your FXML file 'UrunDetay.fxml'.";
        assert lblKategori != null : "fx:id=\"lblKategori\" was not injected: check your FXML file 'UrunDetay.fxml'.";
        assert lblStok != null : "fx:id=\"lblStok\" was not injected: check your FXML file 'UrunDetay.fxml'.";
        assert lblUrunAdi != null : "fx:id=\"lblUrunAdi\" was not injected: check your FXML file 'UrunDetay.fxml'.";
        assert profil != null : "fx:id=\"profil\" was not injected: check your FXML file 'UrunDetay.fxml'.";
        assert sepet != null : "fx:id=\"sepet\" was not injected: check your FXML file 'UrunDetay.fxml'.";
        assert sepeteEkleButton != null : "fx:id=\"sepeteEkleButton\" was not injected: check your FXML file 'UrunDetay.fxml'.";
        assert sonucListesi != null : "fx:id=\"sonucListesi\" was not injected: check your FXML file 'UrunDetay.fxml'.";
        assert txtAciklama != null : "fx:id=\"txtAciklama\" was not injected: check your FXML file 'UrunDetay.fxml'.";
        
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
        
        aramaKutusu.setOnAction(e -> {
            String arananUrun = aramaKutusu.getText().trim();
            if (!arananUrun.isEmpty()) {
                try {
                    urunDetayGosterMevcutPencereIcine(arananUrun); // ✅ burada yeni pencere açmak yerine mevcutu güncelliyoruz
                } catch (Exception ex) {
                    ex.printStackTrace();
                    alertGoster("Hata", "Ürün detayları getirilemedi.");
                }
            }
        });


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
    
    private void urunDetayGosterMevcutPencereIcine(String urunAdi) throws Exception {
        // kategori_adi bilgisini almak için JOIN kullanıyoruz
        String sql = "SELECT u.urun_id, u.ad, u.fiyat, u.aciklama, u.resim_yolu, u.stok, k.kategori_adi " +
                     "FROM urunler u " +
                     "JOIN kategoriler k ON u.kategori_id = k.kategori_id " +
                     "WHERE u.ad = ?";
        
        try (Connection conn = vt.baglan();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, urunAdi);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String id = String.valueOf(rs.getInt("urun_id"));
                String ad = rs.getString("ad");
                String fiyat = rs.getString("fiyat");
                String kategori = rs.getString("kategori_adi"); // DÜZELTİLEN KISIM
                String aciklama = rs.getString("aciklama");
                String resim = rs.getString("resim_yolu");
                String stok = rs.getString("stok");

                urunGoster yeniUrun = new urunGoster(id, ad, fiyat, kategori, aciklama, resim, stok);
                setUrun(yeniUrun);
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

    



 
}
