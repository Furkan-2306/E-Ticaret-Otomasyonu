package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;


public class KullaniciSecimController {

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Hyperlink admin_hp;

    @FXML
    private Button kullaniciGirisButton;

    @FXML
    private Button saticiGirisButton;

    @FXML
    void admin_hpbt(ActionEvent event) {
    	
    	try {
   		   
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminGiris.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
			stage.setTitle("Kullanıcı Giris");
			stage.setScene(new Scene(root));
			stage.show();
			((Stage)admin_hp.getScene().getWindow()).close();        
   
    	} catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    void kullaniciGirisButton_click(ActionEvent event) {
    	try {
   
            FXMLLoader loader = new FXMLLoader(getClass().getResource("KullaniciGiris.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
			stage.setTitle("Kullanıcı Giris");
			stage.setScene(new Scene(root));
			stage.show();
			((Stage)kullaniciGirisButton.getScene().getWindow()).close();        
   
    	} catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void saticiGirisButton_click(ActionEvent event) {
    	try {
    		   
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SaticiGiris.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
			stage.setTitle("Satici Giris");
			stage.setScene(new Scene(root));
			stage.show();
			((Stage)saticiGirisButton.getScene().getWindow()).close();        
   
    	} catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        assert kullaniciGirisButton != null : "fx:id=\"kullaniciGirisButton\" was not injected: check your FXML file 'KullaniciSecim.fxml'.";
        assert saticiGirisButton != null : "fx:id=\"saticiGirisButton\" was not injected: check your FXML file 'KullaniciSecim.fxml'.";

    }

}
