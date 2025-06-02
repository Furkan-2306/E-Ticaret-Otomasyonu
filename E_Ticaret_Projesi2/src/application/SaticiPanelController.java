package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.TableView;


public class SaticiPanelController {
	
	VeriTabani vt=new VeriTabani();
    PreparedStatement ps;
    ResultSet rs;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea aciklama_ta;

    @FXML
    private Button arabutton;
    
    

    @FXML
    private Label eposta;
    
    @FXML
    private TableColumn<urunGoster, String> ıdColumn;
    
    @FXML
    private TableColumn<urunGoster, String> urunColumn;
    
    @FXML
    private TableColumn<urunGoster, String> stokCloumn;
    
    @FXML
    private TableColumn<urunGoster, String> kategoriColumn;

    @FXML
    private TableColumn<urunGoster, String> fiyatColumn;
    
    @FXML
    private TableColumn<urunGoster, String> aciklamaColumn;
    
    @FXML
    private TableColumn<urunGoster, String> resimColumn;
    
    @FXML
    private TableView<urunGoster> tablos;

    @FXML
    private TextField fiyat_tf;

    @FXML
    private Button gozatbutton;

    @FXML
    private Button güncellebutton;

    @FXML
    private ComboBox<String> kategoriCombo;

    @FXML
    private Button kaydetbutton;

    @FXML
    private Tab profil;

    @FXML
    private ImageView resim_yolu;

    @FXML
    private Label satici_Adi;

    @FXML
    private Label satici_Soyadi;

    @FXML
    private Label satici_kodu;

    @FXML
    private Button silbutton;

    @FXML
    private TextField stok_tf;

    @FXML
    private TextField tf_ara;

    @FXML
    private Tab urun_ekleme;

    @FXML
    private TextField urun_tf;
    
    @FXML
    private Button cikisbt;
    
    @FXML
    private Label ad_label;
    
    @FXML
    private Label eposta_label;
    
    @FXML
    private Label kod_label;

    @FXML
    private Label soyad_label; 
    
    @FXML
    private Tab profil1;
    
    @FXML
    private TextField ad_güncelle;
    
    @FXML
    private TextField sifre_güncelle;
    
    @FXML
    private TextField soyad_güncelle;
    
    @FXML
    private TextField eposta_güncelle;
    
    public String tiklananIDString;
    
    @FXML
    private Button bt_guncelle;
    
    @FXML
    private TextField eskieposta;
    
    @FXML
    private Button kupon;
   
    
    @FXML
    void kuponGirClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("indirim.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("İndirim Kuponları");
            stage.setScene(new Scene(root));
            stage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    String filePath;
    
    private ObservableList<urunGoster> urunList = FXCollections.observableArrayList();

    @FXML
    private void arabutton_click() {
        // Arama kutusundan gelen değeri alıyoruz
        String aranacakKelime = tf_ara.getText().toLowerCase();

        // Tabloyu temizliyoruz (tablodaki mevcut veriyi kaldırıyoruz)
        ObservableList<urunGoster> filtrelenmisListe = FXCollections.observableArrayList();

        // Eğer arama kutusu boş değilse, arama işlemi yapıyoruz
        if (!aranacakKelime.isEmpty()) {
            // Tabloyu dolaşıyoruz ve her ürünü kontrol ediyoruz
            for (urunGoster urun : tablos.getItems()) {
                // Sadece ürün adı üzerinde arama yapıyoruz
                if (urun.getUrun().toLowerCase().contains(aranacakKelime)) {
                    // Eğer eşleşiyorsa, listeye ekliyoruz
                    filtrelenmisListe.add(urun);
                }
            }

            // Eğer arama sonucunda ürün bulunmazsa, kullanıcıya bilgi veriyoruz
            if (filtrelenmisListe.isEmpty()) {
                showAlert("Arama Sonucu", "Aradığınız ürün bulunamadı.");
            }

            // Tabloyu sadece arama sonuçlarıyla güncelliyoruz
            tablos.setItems(filtrelenmisListe);
        } else {
            // Arama kutusu boşsa, tüm ürünleri tekrar gösteriyoruz
            tabloVeriYukle();
        }
    }


    @FXML
    void cikisbt_click(ActionEvent event) {
    	
    	try {
  		   
            FXMLLoader loader = new FXMLLoader(getClass().getResource("KullaniciSecim.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
			stage.setTitle("Kullanıcı Giris");
			stage.setScene(new Scene(root));
			stage.show();
			((Stage)cikisbt.getScene().getWindow()).close();        
   
    	} catch (IOException e) {
            e.printStackTrace();
        }
    	
    }

    @FXML
    void gozatbutton_click(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
        
        // Yalnızca resim dosyalarına izin ver
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Resim Dosyaları", "*.jpg", "*.png", "*.gif", "*.bmp");
        fileChooser.getExtensionFilters().add(imageFilter);
        
        // Dosya seçme penceresini aç
        Stage stage = (Stage) gozatbutton.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);

        // Eğer dosya seçildiyse
        if (file != null) {
            try {
                // Seçilen dosyadan bir Image nesnesi oluşturuluyor
                Image image = new Image(file.toURI().toString());

                // Seçilen dosyanın yolunu tutalım
                 filePath = file.getAbsolutePath();
                
                // resim ImageView'ine resmi yüklüyoruz
                if (resim_yolu != null) {
                	resim_yolu.setImage(image);  // ImageView içinde görüntüle
                } else {
                    System.out.println("Resim ImageView öğesi null!");
                }

               
            } catch (Exception e) {
                e.printStackTrace();
                // Hata durumunda bir mesaj gösterilebilir
            }
        }
    }

    @FXML
    void kategoriCombo_click(ActionEvent event) {
    	String secilenKategori = kategoriCombo.getSelectionModel().getSelectedItem();
    }
    @FXML
    void kaydetbutton_click(ActionEvent event) {
        try {
            vt.baglan();

            String urunAdi = urun_tf.getText().trim();
            String aciklama = aciklama_ta.getText().trim();
            String fiyat = fiyat_tf.getText().trim();
            String stok = stok_tf.getText().trim();
            Object kategori = kategoriCombo.getSelectionModel().getSelectedItem();

            if (urunAdi.isEmpty() || aciklama.isEmpty() || fiyat.isEmpty() || stok.isEmpty() || kategori == null) {
                JOptionPane.showMessageDialog(null, "Lütfen tüm alanları doldurun!", "Uyarı", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!fiyat.matches("\\d+(\\.\\d{1,2})?") || !stok.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "Fiyat ve stok sayısal değer olmalıdır!", "Uyarı", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Image kaydedilenResim = resim_yolu.getImage();
            if (kaydedilenResim == null) {
                JOptionPane.showMessageDialog(null, "Lütfen bir ürün görseli seçin!", "Uyarı", JOptionPane.WARNING_MESSAGE);
                return;
            }

            
            File destinationFile = new File("C:\\Users\\furka\\OneDrive\\Masaüstü\\pc gereçleri\\Yazılım\\java_klass\\E_Ticaret_Projesi2\\urunfotograflari\\" + aciklama + ".png");
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(kaydedilenResim, null), "png", destinationFile);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Görsel kaydedilemedi!", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // kategori sorgulama
            String kategoris = kategori.toString();
            String kategoriSorgu = "SELECT kategori_id FROM kategoriler WHERE kategori_adi = ?";
            ps = vt.baglan().prepareStatement(kategoriSorgu);
            ps.setString(1, kategoris);
            ResultSet rsKategori = ps.executeQuery();
            int kategoriId = -1;

            if (rsKategori.next()) {
                kategoriId = rsKategori.getInt("kategori_id");
            } else {
                JOptionPane.showMessageDialog(null, "Geçersiz kategori seçildi!", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }
            ps.close();

            // ürün ekleme
            String urunEkleSorgu = "INSERT INTO urunler (kategori_id, ad, aciklama, fiyat, stok, resim_yolu, satici_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            ps = vt.baglan().prepareStatement(urunEkleSorgu);
            ps.setInt(1, kategoriId);
            ps.setString(2, urunAdi);
            ps.setString(3, aciklama);
            ps.setDouble(4, Double.parseDouble(fiyat));
            ps.setInt(5, Integer.parseInt(stok));
            ps.setString(6, aciklama + ".png");
            ps.setInt(7, Integer.parseInt(GirisController.getGirisYapanSaticiId()));

            int sonuc = ps.executeUpdate();

            if (sonuc > 0) {
                JOptionPane.showMessageDialog(null, "Ürün başarıyla kaydedildi!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
                tabloVeriYukle();
                urun_tf.clear();
                aciklama_ta.clear();
                fiyat_tf.clear();
                stok_tf.clear();
                kategoriCombo.getSelectionModel().clearSelection();
                resim_yolu.setImage(null);
            } else {
                JOptionPane.showMessageDialog(null, "Ürün kaydı başarısız!", "Hata", JOptionPane.ERROR_MESSAGE);
            }

            ps.close();
            vt.kapt();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Bir hata oluştu: " + e.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    @FXML
    void güncellebutton_click(ActionEvent event) {
        try {
            vt.baglan();

            if (tiklananId == null || tiklananId.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Lütfen değiştirmek istediğiniz ürünü seçiniz!", "Uyarı", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Seçilen kategori var mı kontrol et
            if (kategoriCombo.getSelectionModel().getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Lütfen bir kategori seçin!", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            

            String kategoriAdi = kategoriCombo.getSelectionModel().getSelectedItem().toString();

            // Veritabanından kategori_id'yi çek
            String kategoriSorgu = "SELECT kategori_id FROM kategoriler WHERE kategori_adi = ?";
            ps = vt.baglanti.prepareStatement(kategoriSorgu);
            ps.setString(1, kategoriAdi);
            ResultSet rsKategori = ps.executeQuery();
            int kategoriId = -1;

            if (rsKategori.next()) {
                kategoriId = rsKategori.getInt("kategori_id");
            } else {
                JOptionPane.showMessageDialog(null, "Kategori bulunamadı: " + kategoriAdi, "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }
            ps.close();

            String sorgu = "UPDATE urunler SET kategori_id = ?, ad = ?, aciklama = ?, fiyat = ?, stok = ?, resim_yolu = ? WHERE urun_id = ?";

            ps = vt.baglanti.prepareStatement(sorgu);
            ps.setInt(1, kategoriId);
            ps.setString(2, urun_tf.getText());
            ps.setString(3, aciklama_ta.getText());
            ps.setString(4, fiyat_tf.getText());
            ps.setString(5, stok_tf.getText());

            Image kaydedilenResim = resim_yolu.getImage();
            File destinationFile = new File("C:\\Users\\furka\\OneDrive\\Masaüstü\\pc gereçleri\\Yazılım\\java_klass\\E_Ticaret_Projesi2\\urunfotograflari\\" + aciklama_ta.getText() + ".png");
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(kaydedilenResim, null), "png", destinationFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
            ps.setString(6, aciklama_ta.getText() + ".png");

            

            ps.setString(7, tiklananId);

            int sonuc = ps.executeUpdate();
            if (sonuc > 0) {
                JOptionPane.showMessageDialog(null, "Güncelleme başarılı");
                tabloVeriYukle();
                urun_tf.clear();
                aciklama_ta.clear();
                fiyat_tf.clear();
                stok_tf.clear();
                kategoriCombo.getSelectionModel().clearSelection();
                resim_yolu.setImage(null);
            } else {
                JOptionPane.showMessageDialog(null, "Başarısız güncelleme");
            }

            ps.close();
            vt.kapt();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Bir hata oluştu: " + e.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }


    @FXML
    void silbutton_click(ActionEvent event) {
        try {
            vt.baglan();

            if (tiklananId == null || tiklananId.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Lütfen silmek istediğiniz ürünü seçiniz!", "Uyarı", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String sorgu = "DELETE FROM urunler WHERE urun_id=?";
            ps = vt.baglanti.prepareStatement(sorgu);
            ps.setString(1, tiklananId);
            int sonuc = ps.executeUpdate();
            if (sonuc > 0) {
                JOptionPane.showMessageDialog(null, "Silme başarılı");
                tabloVeriYukle();

                urun_tf.clear();
                aciklama_ta.clear();
                fiyat_tf.clear();
                stok_tf.clear();
                kategoriCombo.getSelectionModel().clearSelection();
                resim_yolu.setImage(null);

            } else {
                JOptionPane.showMessageDialog(null, "Başarısız silme");
            }

            ps.close();
            vt.kapt();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Bir hata oluştu: " + e.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    public String tiklananId;
    
    @FXML
    void tablo_click(MouseEvent event) {
        
        vt.baglan();
    
        urunGoster urunTıkla = tablos.getSelectionModel().getSelectedItem();
        if (urunTıkla == null) return;

        urun_tf.setText(urunTıkla.getUrun());
        stok_tf.setText(urunTıkla.getStok());
        fiyat_tf.setText(urunTıkla.getFiyat());
        kategoriCombo.setValue(urunTıkla.getKategori());
        aciklama_ta.setText(urunTıkla.getAciklama());

        String filePath = "file:///" + "C:\\Users\\furka\\OneDrive\\Masaüstü\\pc gereçleri\\Yazılım\\java_klass\\E_Ticaret_Projesi2\\urunfotograflari\\" + urunTıkla.getResim();
        try {
            Image image = new Image(filePath);
            resim_yolu.setImage(image);
        } catch (Exception e) {
            System.out.println("Resim dosyası yüklenemedi: " + filePath);
        }

        tiklananId = urunTıkla.getId();
    }

    @FXML
    void bt_guncelle_click(ActionEvent event) {
        String ad = ad_güncelle.getText();
        String soyad = soyad_güncelle.getText();
        String yeniEmail = eposta_güncelle.getText();
        String sifre = sifre_güncelle.getText();
        String eskiEmail = eskieposta.getText();

        if (ad.isEmpty() || soyad.isEmpty() || yeniEmail.isEmpty() || sifre.isEmpty() || eskiEmail.isEmpty()) {
            showAlert("Hata", "Lütfen tüm alanları doldurun.");
            return;
        }

        String sql = "UPDATE saticilar SET ad = ?, soyad = ?, email = ?, sifre = ? WHERE email = ?";

        try {
            vt.baglan();
            ps = vt.baglanti.prepareStatement(sql);

            ps.setString(1, ad);
            ps.setString(2, soyad);
            ps.setString(3, yeniEmail);
            ps.setString(4, sifre);
            ps.setString(5, eskiEmail);

            int updated = ps.executeUpdate();

            if (updated > 0) {
                showAlert("Başarılı", "Bilgiler güncellendi. Lütfen tekrar giriş yapın.");

                // Sayfayı değiştir (SaticiGiris.fxml'e yönlendir)
                FXMLLoader loader = new FXMLLoader(getClass().getResource("SaticiGiris.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();

            } else {
                showAlert("Hata", "Güncelleme yapılamadı. Eski e-posta bulunamadı.");
            }

            ps.close();
            vt.kapt();

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Hata", "Veritabanı hatası: " + e.getMessage());
        }
    }




    @FXML
    void initialize() {
    	 assert aciklamaColumn != null : "fx:id=\"aciklamaColumn\" was not injected: check your FXML file 'SaticiPanel.fxml'.";
         assert aciklama_ta != null : "fx:id=\"aciklama_ta\" was not injected: check your FXML file 'SaticiPanel.fxml'.";
         assert arabutton != null : "fx:id=\"arabutton\" was not injected: check your FXML file 'SaticiPanel.fxml'.";
         assert cikisbt != null : "fx:id=\"cikisbt\" was not injected: check your FXML file 'SaticiPanel.fxml'.";
         assert eposta != null : "fx:id=\"eposta\" was not injected: check your FXML file 'SaticiPanel.fxml'.";
         assert fiyatColumn != null : "fx:id=\"fiyatColumn\" was not injected: check your FXML file 'SaticiPanel.fxml'.";
         assert fiyat_tf != null : "fx:id=\"fiyat_tf\" was not injected: check your FXML file 'SaticiPanel.fxml'.";
         assert gozatbutton != null : "fx:id=\"gozatbutton\" was not injected: check your FXML file 'SaticiPanel.fxml'.";
         assert güncellebutton != null : "fx:id=\"güncellebutton\" was not injected: check your FXML file 'SaticiPanel.fxml'.";
         assert kategoriColumn != null : "fx:id=\"kategoriColumn\" was not injected: check your FXML file 'SaticiPanel.fxml'.";
         assert kategoriCombo != null : "fx:id=\"kategoriCombo\" was not injected: check your FXML file 'SaticiPanel.fxml'.";
         assert kaydetbutton != null : "fx:id=\"kaydetbutton\" was not injected: check your FXML file 'SaticiPanel.fxml'.";
         assert profil != null : "fx:id=\"profil\" was not injected: check your FXML file 'SaticiPanel.fxml'.";
         assert resimColumn != null : "fx:id=\"resimColumn\" was not injected: check your FXML file 'SaticiPanel.fxml'.";
         assert resim_yolu != null : "fx:id=\"resim_yolu\" was not injected: check your FXML file 'SaticiPanel.fxml'.";
         assert satici_Adi != null : "fx:id=\"satici_Adi\" was not injected: check your FXML file 'SaticiPanel.fxml'.";
         assert satici_Soyadi != null : "fx:id=\"satici_Soyadi\" was not injected: check your FXML file 'SaticiPanel.fxml'.";
         assert satici_kodu != null : "fx:id=\"satici_kodu\" was not injected: check your FXML file 'SaticiPanel.fxml'.";
         assert silbutton != null : "fx:id=\"silbutton\" was not injected: check your FXML file 'SaticiPanel.fxml'.";
         assert stokCloumn != null : "fx:id=\"stokCloumn\" was not injected: check your FXML file 'SaticiPanel.fxml'.";
         assert stok_tf != null : "fx:id=\"stok_tf\" was not injected: check your FXML file 'SaticiPanel.fxml'.";
         assert tablos != null : "fx:id=\"tablos\" was not injected: check your FXML file 'SaticiPanel.fxml'.";
         assert tf_ara != null : "fx:id=\"tf_ara\" was not injected: check your FXML file 'SaticiPanel.fxml'.";
         assert urunColumn != null : "fx:id=\"urunColumn\" was not injected: check your FXML file 'SaticiPanel.fxml'.";
         assert urun_ekleme != null : "fx:id=\"urun_ekleme\" was not injected: check your FXML file 'SaticiPanel.fxml'.";
         assert urun_tf != null : "fx:id=\"urun_tf\" was not injected: check your FXML file 'SaticiPanel.fxml'.";
         assert ıdColumn != null : "fx:id=\"ıdColumn\" was not injected: check your FXML file 'SaticiPanel.fxml'.";


        
         
        
        kategoriCombo_click(null);
    	kategoriComboDoldur();
        kategoriCombo.setOnAction(this::kategoriCombo_click);
       
        ıdColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getId()));
        urunColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getUrun()));
        kategoriColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getKategori()));
        fiyatColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getFiyat()));
        aciklamaColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getAciklama()));
        stokCloumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getStok()));
        resimColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getResim()));

        
        tabloVeriYukle();
        
        
        
    }
    
    
    private void kategoriComboDoldur() {
        try {

            String sql = "SELECT kategori_adi FROM kategoriler";
            ps = vt.baglan().prepareStatement(sql); 
            rs = ps.executeQuery();

            kategoriCombo.getItems().clear(); 

            while (rs.next()) {
                kategoriCombo.getItems().add(rs.getString("kategori_adi"));
            }

            rs.close();
            ps.close();
            vt.kapt(); 

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setSaticiPanel(String email, String ad, String soyad, String saticiKodu) {
    	satici_Adi.setText(email);
    	satici_Soyadi.setText(ad);
    	eposta.setText(soyad);
    	satici_kodu.setText(saticiKodu);
    }
    
   


    public void tabloVeriYukle() {
        ObservableList<urunGoster> urunListesi = FXCollections.observableArrayList();
        Connection conn = vt.baglan();

        if (conn == null) {
            System.out.println("Veritabanına bağlanılamadı!");
            return;
        }

        // Giriş yapan satıcının ID'sini GirisController'dan alıyoruz
        String girisYapanSaticiId = GirisController.getGirisYapanSaticiId(); 

        String query = "SELECT * FROM urunler WHERE satici_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, girisYapanSaticiId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String urun_id = String.valueOf(rs.getInt("urun_id"));
                String urunAdi = rs.getString("ad");
                String stok = String.valueOf(rs.getInt("stok"));
                String fiyat = String.valueOf(rs.getDouble("fiyat"));
                String kategori = rs.getString("kategori_id");
                String aciklama = rs.getString("aciklama");
                String resim = rs.getString("resim_yolu");

                urunGoster urun = new urunGoster(resim, resim, resim, resim, resim, resim, resim);
                urun.setId(urun_id);
                urun.setUrun(urunAdi);
                urun.setStok(stok);
                urun.setFiyat(fiyat);
                urun.setKategori(kategori);
                urun.setAciklama(aciklama);
                urun.setResim(resim);

                urunListesi.add(urun);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            vt.kapt();
        }

        tablos.setItems(urunListesi);
    }



   
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    
  

}
