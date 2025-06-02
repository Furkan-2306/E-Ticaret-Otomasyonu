package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class AnaSayfaController {
	
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

            
            KullaniciPanelController controller = loader.getController();

            
            controller.setKullaniciId(girisk.getKullaniciId());

            Stage stage = new Stage();
            stage.setTitle("Kullanıcı Panel");
            stage.setScene(new Scene(root));
            stage.show();

            
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
        
        urunleriGoster();

        
        sonucListesi.setVisible(false);

        
        aramaKutusu.textProperty().addListener((observable, eski, yeni) -> {
            if (yeni == null || yeni.isBlank()) {
                sonucListesi.setVisible(false);
                return;
            }

            try {
                
                urunleriGetir(yeni);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        
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
                    urunDetayGoster(arananUrun);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    alertGoster("Hata", "Ürün detayları gösterilemedi.");
                }
            }
        });

    }
    
    private void urunDetayGoster(String urunAdi) throws Exception {
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
                String kategori = rs.getString("kategori_adi");  
                String aciklama = rs.getString("aciklama");
                String resim = rs.getString("resim_yolu");
                String stok = rs.getString("stok");

                urunGoster urun = new urunGoster(id, ad, fiyat, kategori, aciklama, resim, stok);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("UrunDetay.fxml"));
                Parent root = loader.load();

                UrunDetayController controller = loader.getController();
                controller.setUrun(urun);

                Stage detayStage = new Stage();
                detayStage.setTitle("Ürün Detay");
                detayStage.setScene(new Scene(root));
                detayStage.show();

                
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
        urunlerListesi.clear(); 
        
        String query = "SELECT ad FROM urunler WHERE ad LIKE ?";

        try (Connection connection = vt.baglan();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, "%" + aramaKelimesi + "%"); 
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                urunlerListesi.add(resultSet.getString("ad")); 
            }

            sonucListesi.setItems(urunlerListesi); 
            sonucListesi.setVisible(!urunlerListesi.isEmpty()); 
        }
    }
    
    private void urunleriGoster() {
        urunListePaneli.getChildren().clear(); 
        String sql = """
            SELECT u.urun_id, u.ad, u.fiyat, u.resim_yolu, u.aciklama, u.stok, k.kategori_adi
            FROM urunler u
            JOIN kategoriler k ON u.kategori_id = k.kategori_id
            LIMIT 2
            """;

        int column = 0;
        int row = 0;
        int urunSayisi = 0;

        try (Connection conn = vt.baglan();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next() && urunSayisi < 2) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("UrunKart.fxml"));
                Parent urunNode = loader.load();

                // Ürün bilgilerini al
                String id = String.valueOf(rs.getInt("urun_id"));
                String ad = rs.getString("ad");
                String fiyat = rs.getString("fiyat");
                String resimAdi = rs.getString("resim_yolu");
                String aciklama = rs.getString("aciklama");
                String stok = rs.getString("stok");
                String kategori = rs.getString("kategori_adi");

                
                urunGoster urun = new urunGoster(id, ad, fiyat, kategori, aciklama, resimAdi, stok);

                
                UrunKartController controller = loader.getController();
                controller.setUrun(urun);

                
                urunListePaneli.add(urunNode, column, row);

                column++;
                if (column == 2) {
                    column = 0;
                    row++;
                }

                urunSayisi++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    }
    

