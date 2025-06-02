package application;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;

public class indirimController {

    VeriTabani vt = new VeriTabani();
    ObservableList<Discount> discountList = FXCollections.observableArrayList();

    @FXML
    private TextField codeField;

    @FXML
    private TextField percentageField;

    @FXML
    private TableView<Discount> discountTable;

    @FXML
    private TableColumn<Discount, String> handleDelete; 

    @FXML
    private TableColumn<Discount, String> codeColumn;

    @FXML
    private TableColumn<Discount, Double> percentageColumn;

    @FXML
    void handleAdd(ActionEvent event) {
        String code = codeField.getText();
        double percentage = Double.parseDouble(percentageField.getText());

        try (Connection conn = vt.baglan()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO discount_codes (code, percentage) VALUES (?, ?)");
            ps.setString(1, code);
            ps.setDouble(2, percentage);
            ps.executeUpdate();
            loadDiscounts();
            clearFields();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleUpdate(ActionEvent event) {
        Discount selected = discountTable.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        String code = codeField.getText();
        double percentage = Double.parseDouble(percentageField.getText());

        try (Connection conn = vt.baglan()) {
            PreparedStatement ps = conn.prepareStatement("UPDATE discount_codes SET code = ?, percentage = ? WHERE id = ?");
            ps.setString(1, code);
            ps.setDouble(2, percentage);
            ps.setInt(3, selected.getId());
            ps.executeUpdate();
            loadDiscounts();
            clearFields();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleDelete(ActionEvent event) {
        Discount selected = discountTable.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        try (Connection conn = vt.baglan()) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM discount_codes WHERE id = ?");
            ps.setInt(1, selected.getId());
            ps.executeUpdate();
            loadDiscounts();
            clearFields();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        percentageColumn.setCellValueFactory(new PropertyValueFactory<>("percentage"));
        discountTable.setItems(discountList);
        loadDiscounts();

        discountTable.setOnMouseClicked(event -> {
            Discount selected = discountTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                codeField.setText(selected.getCode());
                percentageField.setText(String.valueOf(selected.getPercentage()));
            }
        });
    }

    private void loadDiscounts() {
        discountList.clear();
        try (Connection conn = vt.baglan()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM discount_codes");

            while (rs.next()) {
                discountList.add(new Discount(rs.getInt("id"), rs.getString("code"), rs.getDouble("percentage")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        codeField.clear();
        percentageField.clear();
    }
}
