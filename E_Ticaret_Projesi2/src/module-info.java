module E_Ticaretdenemeleri {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires javafx.swing;
    
    opens application to javafx.graphics, javafx.fxml, javafx.base;
}
