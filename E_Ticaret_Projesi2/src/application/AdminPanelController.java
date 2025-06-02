package application;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.TableView;

public class AdminPanelController {
	
	VeriTabani vt=new VeriTabani();
    PreparedStatement ps;
    ResultSet rs;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label adiLabel;
    
    @FXML
    private AnchorPane anchor;

    @FXML
    private Label adminIdLabel;

    @FXML
    private Button cikisbt;

    @FXML
    private Label depAdiLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Button gozatbutton;

    @FXML
    private Button güncellebutton;  

    @FXML
    private TextField kategori_tf;

    @FXML
    private Button kaydetbutton;

    @FXML
    private ImageView resim_yolu;

    @FXML
    private Button silbutton;

    @FXML
    private Label soyadiLabel;
    
    @FXML
    private TableColumn<kategoriGoster, String> ıdColumn;
    
    @FXML
    private TableColumn<kategoriGoster, String> kategoriColumn;
    
    @FXML
    private TableColumn<kategoriGoster, String> resimColumn;

    @FXML
    private TableView<kategoriGoster> tablos;

    @FXML
    private Label telLabel;

    @FXML
    private Label unvanLabel;
    
    @FXML
    private TextField ad_güncelle;
    
    @FXML
    private TextField eposta_güncelle;
    
    @FXML
    private TextField eskieposta;
    
    @FXML
    private TextField sifre_güncelle;
    
    @FXML
    private TextField soyad_güncelle;
    
    @FXML
    private Button bt_guncelle;
    
    @FXML
    private Button arabutton;
    
    @FXML
    private TextField tf_ara;
    
    @FXML
    private Label adiLabel1;
    
    @FXML
    private Label adminIdLabel1;
    
    @FXML
    private Button cikisbt1;
    
    @FXML
    private Label depAdiLabel1;
    
    @FXML
    private Label emailLabel1;
    
    @FXML
    private TableView<SatıcıModel> satici_bilgi;
    
    @FXML
    private TableColumn<SatıcıModel, String> satici_id_show;
    
    @FXML
    private TableColumn<SatıcıModel, String> satici_kod_show;
    
    @FXML
    private TableColumn<SatıcıModel, String> ad_show;
    
    @FXML
    private TableColumn<SatıcıModel, String> soyad_show;
    
    @FXML
    private TableColumn<SatıcıModel, String> email_show;
    
    @FXML
    private TableColumn<SatıcıModel, String> sifre_show;
    
    @FXML
    private TableColumn<SatıcıModel, Boolean> bloklu_show;
    
    @FXML
    private Label soyadiLabel1;
    
    @FXML
    private Label telLabel1;
    
    @FXML
    private Label unvanLabel1;	
    
    @FXML
    private Button bt_guncelle1;
    
    @FXML
    private Button arabutton1;
    
    @FXML
    private Button bloklaButton;
    
    @FXML
    private Button unblockButton;
   

    
    
    private void setText(Object object) {
			// TODO Auto-generated method stub
			
		}
	@FXML
    private TextField tf_ara1;
    
    private ObservableList<SatıcıModel> satıcıModels = FXCollections.observableArrayList();
    
    @FXML
    void arabutton_click1(ActionEvent event) {

        // Arama kutusundan gelen değeri alıyoruz
        String aranacakKelime = tf_ara1.getText().toLowerCase();

        // Tabloyu temizliyoruz (tablodaki mevcut veriyi kaldırıyoruz)
        ObservableList<SatıcıModel> filtrelenmisListe = FXCollections.observableArrayList();

        // Eğer arama kutusu boş değilse, arama işlemi yapıyoruz
        if (!aranacakKelime.isEmpty()) {
            // Tabloyu dolaşıyoruz ve her ürünü kontrol ediyoruz
            for (SatıcıModel satici : satici_bilgi.getItems()) {
                // Sadece ürün adı üzerinde arama yapıyoruz
                if (satici.getAd().toLowerCase().contains(aranacakKelime)) {
                    // Eğer eşleşiyorsa, listeye ekliyoruz
                    filtrelenmisListe.add(satici);
                }
            }

            // Eğer arama sonucunda ürün bulunmazsa, kullanıcıya bilgi veriyoruz
            if (filtrelenmisListe.isEmpty()) {
                showAlert("Arama Sonucu", "Aradığınız ürün bulunamadı.");
            }

            // Tabloyu sadece arama sonuçlarıyla güncelliyoruz
            satici_bilgi.setItems(filtrelenmisListe);
        } else {
            // Arama kutusu boşsa, tüm ürünleri tekrar gösteriyoruz
            tabloVeriCek();
        }
    } 
    
    @FXML
    void arabutton_click(ActionEvent event) {

        // Arama kutusundan gelen değeri alıyoruz
        String aranacakKelime = tf_ara.getText().toLowerCase();

        // Tabloyu temizliyoruz (tablodaki mevcut veriyi kaldırıyoruz)
        ObservableList<kategoriGoster> filtrelenmisListe = FXCollections.observableArrayList();

        // Eğer arama kutusu boş değilse, arama işlemi yapıyoruz
        if (!aranacakKelime.isEmpty()) {
            // Tabloyu dolaşıyoruz ve her ürünü kontrol ediyoruz
            for (kategoriGoster kategori : tablos.getItems()) {
                // Sadece ürün adı üzerinde arama yapıyoruz
                if (kategori.getKategori().toLowerCase().contains(aranacakKelime)) {
                    // Eğer eşleşiyorsa, listeye ekliyoruz
                    filtrelenmisListe.add(kategori);
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

    String filePath;
    
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
    void kaydetbutton_click(ActionEvent event) {

        try {
            vt.baglan();

            String kategoriAdi = kategori_tf.getText().trim();

            if (kategoriAdi.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Lütfen tüm alanları doldurun!", "Uyarı", JOptionPane.WARNING_MESSAGE);
                return;
            }

           
            Image kaydedilenResim = resim_yolu.getImage();
            if (kaydedilenResim == null) {
                JOptionPane.showMessageDialog(null, "Lütfen bir kategori görseli seçin!", "Uyarı", JOptionPane.WARNING_MESSAGE);
                return;
            }

            
            File destinationFile = new File("C:\\Users\\furka\\OneDrive\\Masaüstü\\pc gereçleri\\Yazılım\\java_klass\\E_Ticaret_Projesi\\kategori_foto\\" + kategoriAdi + ".png");

            try {
                ImageIO.write(SwingFXUtils.fromFXImage(kaydedilenResim, null), "png", destinationFile);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Görsel kaydedilemedi!", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Kategori ekleme
            String kategoriEkleSorgu = "INSERT INTO kategoriler (kategori_adi, resim_yolu) VALUES (?, ?)";
            ps = vt.baglan().prepareStatement(kategoriEkleSorgu);
            ps.setString(1, kategoriAdi);
            ps.setString(2, kategoriAdi + ".png");

            int sonuc = ps.executeUpdate();

            if (sonuc > 0) {
                JOptionPane.showMessageDialog(null, "Kategori başarıyla kaydedildi!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
                tabloVeriYukle();
                kategori_tf.clear();
                resim_yolu.setImage(null);
            } else {
                JOptionPane.showMessageDialog(null, "Kategori kaydı başarısız!", "Hata", JOptionPane.ERROR_MESSAGE);
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
	   
	    	String sorgu="UPDATE kategoriler SET  kategori_adi = ?, resim_yolu = ? WHERE kategori_id = ?";
				ps=vt.baglanti.prepareStatement(sorgu);
				ps.setString(1,kategori_tf.getText());
				Image kaydedilenResim= resim_yolu.getImage();
	    	 File destinationFile = new File("C:\\Users\\furka\\OneDrive\\Masaüstü\\pc gereçleri\\Yazılım\\java_klass\\E_Ticaret_Projesi\\kategori_foto\\"+kategori_tf.getText()+".png");
	         try {
				ImageIO.write(SwingFXUtils.fromFXImage(kaydedilenResim,null),"png",destinationFile);
			} catch (Exception e) {
			e.printStackTrace();
			}
				ps.setString(2,kategori_tf.getText()+".png");
				ps.setString(3,tiklananId);
				int sonuc=ps.executeUpdate();///update insert 
				if(sonuc>0) {
					JOptionPane.showMessageDialog(null, "güncelleme başarılı");
					tabloVeriYukle();
					kategori_tf.clear();
	                resim_yolu.setImage(null);
				}
				else {
					JOptionPane.showMessageDialog(null, "Başarısız güncelleme");
				}
				
				ps.close();
				vt.kapt();
	    	}
	     	catch (Exception e) {
	     		JOptionPane.showMessageDialog(null, "Bir hata oluştu: " + e.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
	 		}

    }

    @FXML
    void silbutton_click(ActionEvent event) {
    	
    	try {
        	vt.baglan();
        	
        	if (tiklananId == null || tiklananId.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Lütfen silmek isteddiğiniz ürünü seçiniz!", "Uyarı", JOptionPane.WARNING_MESSAGE);
                return;
            }
        	
        	
        	String sorgu="DELETE FROM kategoriler WHERE kategori_id=?";
 			ps=vt.baglanti.prepareStatement(sorgu);
 			ps.setString(1,tiklananId);
 			int sonuc=ps.executeUpdate();///update insert 
 			if(sonuc>0) {
 				JOptionPane.showMessageDialog(null, "silme başarılı");
 				tabloVeriYukle();
				kategori_tf.clear();
                resim_yolu.setImage(null);
 			}
 			else {
 				JOptionPane.showMessageDialog(null, "Başarısız silme");
 			}
 			
 			ps.close();
 			vt.kapt();
     	}
     	catch (Exception e) {
 			// TODO: handle exception
 		}

    }

    public String tiklananId;
    
    @FXML
    void tablo_click(MouseEvent event) {
    	
    	kategoriGoster kategoriTıkla = tablos.getSelectionModel().getSelectedItem();

        tiklananId = kategoriTıkla.getId();
        kategori_tf.setText(kategoriTıkla.getKategori());

        String filePath = "file:///" + "C:\\Users\\furka\\OneDrive\\Masaüstü\\pc gereçleri\\Yazılım\\java_klass\\E_Ticaret_Projesi\\kategori_foto\\" + kategoriTıkla.getResim();
    	Image image = new Image(filePath);
    	resim_yolu.setImage(image);

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

    	    String sql = "UPDATE adminler SET ad = ?, soyad = ?, email = ?, sifre = ? WHERE email = ?";

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

    	            // Admin giriş sayfasına yönlendir
    	            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminGiris.fxml"));
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
    void bloklaClicked(ActionEvent event) {
    	SatıcıModel secilen = satici_bilgi.getSelectionModel().getSelectedItem();
        if (secilen != null && !secilen.getBloklu()) {
            try (Connection conn = vt.baglan()) {
                PreparedStatement ps = conn.prepareStatement("UPDATE saticilar SET bloklu = 1 WHERE ad = ?");
                ps.setString(1, secilen.getAd());
                ps.executeUpdate();
                secilen.setBloklu(true);
                satici_bilgi.refresh();
                
                // Başarı mesajı
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("İşlem Başarılı");
                alert.setHeaderText(null);
                alert.setContentText("Satıcı başarıyla bloklanmıştır!");
                alert.showAndWait();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    @FXML
    void unblockedClicked(ActionEvent event) {
    	SatıcıModel secilen = satici_bilgi.getSelectionModel().getSelectedItem();
        if (secilen != null && secilen.getBloklu()) {
            try (Connection conn = vt.baglan()) {
                PreparedStatement ps = conn.prepareStatement("UPDATE saticilar SET bloklu = 0 WHERE ad = ?");
                ps.setString(1, secilen.getAd());
                ps.executeUpdate();
                secilen.setBloklu(false);
                satici_bilgi.refresh();
                
                // Başarı mesajı
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("İşlem Başarılı");
                alert.setHeaderText(null);
                alert.setContentText("Satıcı bloğu kaldırılmıştır!");
                alert.showAndWait();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    

    

	@FXML
    void initialize() {
        assert ad_güncelle != null : "fx:id=\"ad_güncelle\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert ad_show != null : "fx:id=\"ad_show\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert adiLabel != null : "fx:id=\"adiLabel\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert adiLabel1 != null : "fx:id=\"adiLabel1\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert adminIdLabel != null : "fx:id=\"adminIdLabel\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert adminIdLabel1 != null : "fx:id=\"adminIdLabel1\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert arabutton != null : "fx:id=\"arabutton\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert arabutton1 != null : "fx:id=\"arabutton1\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert bloklaButton != null : "fx:id=\"bloklaButton\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert bloklu_show != null : "fx:id=\"bloklu_show\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert bt_guncelle != null : "fx:id=\"bt_guncelle\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert cikisbt != null : "fx:id=\"cikisbt\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert depAdiLabel != null : "fx:id=\"depAdiLabel\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert depAdiLabel1 != null : "fx:id=\"depAdiLabel1\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert emailLabel != null : "fx:id=\"emailLabel\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert emailLabel1 != null : "fx:id=\"emailLabel1\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert email_show != null : "fx:id=\"email_show\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert eposta_güncelle != null : "fx:id=\"eposta_güncelle\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert eskieposta != null : "fx:id=\"eskieposta\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert gozatbutton != null : "fx:id=\"gozatbutton\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert güncellebutton != null : "fx:id=\"güncellebutton\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert kategoriColumn != null : "fx:id=\"kategoriColumn\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert kategori_tf != null : "fx:id=\"kategori_tf\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert kaydetbutton != null : "fx:id=\"kaydetbutton\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert resimColumn != null : "fx:id=\"resimColumn\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert resim_yolu != null : "fx:id=\"resim_yolu\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert satici_bilgi != null : "fx:id=\"satici_bilgi\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert satici_id_show != null : "fx:id=\"satici_id_show\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert satici_kod_show != null : "fx:id=\"satici_kod_show\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert sifre_güncelle != null : "fx:id=\"sifre_güncelle\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert sifre_show != null : "fx:id=\"sifre_show\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert silbutton != null : "fx:id=\"silbutton\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert soyad_güncelle != null : "fx:id=\"soyad_güncelle\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert soyad_show != null : "fx:id=\"soyad_show\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert soyadiLabel != null : "fx:id=\"soyadiLabel\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert soyadiLabel1 != null : "fx:id=\"soyadiLabel1\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert tablos != null : "fx:id=\"tablos\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert telLabel != null : "fx:id=\"telLabel\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert telLabel1 != null : "fx:id=\"telLabel1\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert tf_ara != null : "fx:id=\"tf_ara\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert tf_ara1 != null : "fx:id=\"tf_ara1\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert unblockButton != null : "fx:id=\"unblockButton\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert unvanLabel != null : "fx:id=\"unvanLabel\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert unvanLabel1 != null : "fx:id=\"unvanLabel1\" was not injected: check your FXML file 'AdminPanel.fxml'.";
        assert ıdColumn != null : "fx:id=\"ıdColumn\" was not injected: check your FXML file 'AdminPanel.fxml'.";

        
        
        satici_id_show.setCellValueFactory(cellData->new javafx.beans.property.SimpleStringProperty(cellData.getValue().getId()));
        satici_kod_show.setCellValueFactory(cellData->new javafx.beans.property.SimpleStringProperty(cellData.getValue().getSatici_kod()));
        ad_show.setCellValueFactory(cellData->new javafx.beans.property.SimpleStringProperty(cellData.getValue().getAd()));
        soyad_show.setCellValueFactory(cellData->new javafx.beans.property.SimpleStringProperty(cellData.getValue().getSoyad()));
        email_show.setCellValueFactory(cellData->new javafx.beans.property.SimpleStringProperty(cellData.getValue().getEmail()));
        sifre_show.setCellValueFactory(cellData->new javafx.beans.property.SimpleStringProperty(cellData.getValue().getSifre()));
        bloklu_show.setCellValueFactory(cellData->new javafx.beans.property.SimpleBooleanProperty(cellData.getValue().getBloklu()));
        
        tabloVeriCek();

        ıdColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getId()));
        kategoriColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getKategori()));
        resimColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getResim()));
        
        tabloVeriYukle();
        
        
}    
        

	public void setAdminPanel(String email, String ad, String soyad, String Tel, String id, String Depart, String Unvan) {
        emailLabel.setText(email);
        adiLabel.setText(ad);
        soyadiLabel.setText(soyad);
        telLabel.setText(Tel);
        adminIdLabel.setText(id);
        depAdiLabel.setText(Depart);
        unvanLabel.setText(Unvan);
	}
        
    //Kategori
    public void tabloVeriYukle() {
        ObservableList<kategoriGoster> kategoriListesi = FXCollections.observableArrayList();
        Connection conn = vt.baglan();

        if (conn == null) {
            System.out.println("Veritabanına bağlanılamadı!");
            return;
        }

        String query = "SELECT * FROM kategoriler";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String kategoriId = String.valueOf(rs.getInt("kategori_id"));
                String kategoriAdi = rs.getString("kategori_adi");
                String resim = rs.getString("resim_yolu");

                kategoriGoster Kategori = new kategoriGoster();
                Kategori.setId(kategoriId);;
                Kategori.setKategori(kategoriAdi);
                Kategori.setResim(resim);
                
                kategoriListesi.add(Kategori);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            vt.kapt();
        }

        tablos.setItems(kategoriListesi);
    }
    //Satici
    private void tabloVeriCek() {
		// TODO Auto-generated method stub
    	ObservableList<SatıcıModel> saticilarListesi = FXCollections.observableArrayList();

        // Veritabanı bağlantısını aç
        VeriTabani vt = new VeriTabani();
        Connection conn = vt.baglan();
        
        // Veritabanından satıcı verilerini al
        String query = "SELECT * FROM saticilar"; // Kullanıcılar tablosundan tüm veriler
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                // Veritabanından her bir satıcıyı al
            	String id = String.valueOf(rs.getInt("id"));
            	String satici_kod = rs.getString("satici_kod");
                String ad = rs.getString("ad");
                String soyad = rs.getString("soyad");
                String email = rs.getString("email");
                String sifre = rs.getString("sifre");


                // Satıcıyı model sınıfına ekle
                SatıcıModel saticilar = new SatıcıModel();
                saticilar.setId(id);
                saticilar.setId(satici_kod);
                saticilar.setAd(ad);
                saticilar.setSoyad(soyad);
                saticilar.setEmail(email);
                saticilar.setId(sifre);
                saticilar.setBloklu(rs.getBoolean("Bloklu"));

                // Satıcıyı ObservableList'e ekle
                saticilarListesi.add(saticilar);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            vt.kapt(); // Veritabanı bağlantısını kapat
        }

        // Tabloyu verilerle doldur
        satici_bilgi.setItems(saticilarListesi);
        


	}
    
    
  



    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    
}
