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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdminGirisController implements Initializable {
	
	VeriTabani vt = new VeriTabani();
	PreparedStatement ps;
	ResultSet rs;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BackButton;

    @FXML
    private Button GirisButton;

    @FXML
    private TextField email_tf;

    @FXML
    private Text geri_button;

    @FXML
    private TextField shownPassword;

    @FXML
    private PasswordField sifre_pf;

    @FXML
    private Button toggleButton;
    
    @FXML
    void BackButton_click(ActionEvent event) {
    	
    	try {
    		   
            FXMLLoader loader = new FXMLLoader(getClass().getResource("KullaniciSecim.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
			stage.setTitle("Admin Giris");
			stage.setScene(new Scene(root));
			stage.show();
			((Stage)GirisButton.getScene().getWindow()).close();        
   
    	} catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void GirisButton_click(ActionEvent event) {
    	
    	try {
			String email = email_tf.getText().trim();
			String sifre = sifre_pf.getText().trim();
			String sorgu = "SELECT * FROM Adminler WHERE email=? AND sifre=?";
			ps=vt.baglan().prepareStatement(sorgu);
			ps.setString(1, email);
			ps.setString(2, sifre);
			rs=ps.executeQuery();
			if(rs.next()) {
				JOptionPane.showMessageDialog(null, "Hosgeldiniz :)");			    
			    String id = rs.getString("id");
			    String ad = rs.getString("ad");
			    String soyad = rs.getString("soyad");
			    String Tel = rs.getString("Tel");
			    String Depart = rs.getString("depart");
			    String Unvan = rs.getString("Unvan");


			    FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminPanel.fxml"));
			    Parent root=loader.load();
			    

			    AdminPanelController controller = loader.getController();
			    controller.setAdminPanel(email, ad, soyad, Tel, id, Depart, Unvan);
			    
			    Stage stage=new Stage();
			    stage.setTitle("Admin Paneli");
			    stage.setScene(new Scene(root));
			    stage.show();
			    ((Stage)GirisButton.getScene().getWindow()).close();
			}
			else {
				JOptionPane.showMessageDialog(null, "Email veya Sifre yanlis :(");
			}
			
			rs.close();
			ps.close();
			vt.kapt();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);

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
        assert BackButton != null : "fx:id=\"BackButton\" was not injected: check your FXML file 'AdminGiris.fxml'.";
        assert GirisButton != null : "fx:id=\"GirisButton\" was not injected: check your FXML file 'AdminGiris.fxml'.";
        assert email_tf != null : "fx:id=\"email_tf\" was not injected: check your FXML file 'AdminGiris.fxml'.";
        assert geri_button != null : "fx:id=\"geri_button\" was not injected: check your FXML file 'AdminGiris.fxml'.";
        assert shownPassword != null : "fx:id=\"shownPassword\" was not injected: check your FXML file 'AdminGiris.fxml'.";
        assert sifre_pf != null : "fx:id=\"sifre_pf\" was not injected: check your FXML file 'AdminGiris.fxml'.";
        assert toggleButton != null : "fx:id=\"toggleButton\" was not injected: check your FXML file 'AdminGiris.fxml'.";

    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        shownPassword.managedProperty().bind(shownPassword.visibleProperty());
        sifre_pf.managedProperty().bind(sifre_pf.visibleProperty());

        
        sifre_pf.textProperty().addListener((obs, oldText, newText) -> {
            shownPassword.setText(newText);
        });

        shownPassword.textProperty().addListener((obs, oldText, newText) -> {
            sifre_pf.setText(newText);
        });

       
        shownPassword.setVisible(false);
        sifre_pf.setVisible(true);
    }

}
