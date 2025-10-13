package contraller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.dto.ItemDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemContraller implements Initializable {

    ObservableList<ItemDTO> itemList = FXCollections.observableArrayList(
            new ItemDTO("I001", "Red Rice 5kg", "Groceries", 40, 1200.00),
            new ItemDTO("I002", "Sunlight Soap 100g", "Household", 100, 80.00),
            new ItemDTO("I003", "Anchor Milk Powder 1kg", "Dairy", 30, 1450.00),
            new ItemDTO("I004", "Prima Noodles 400g", "Groceries", 50, 250.00),
            new ItemDTO("I005", "Coca-Cola 1.5L", "Beverages", 60, 180.00)
    );

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TextField txtCategory;

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;

    @FXML
    private TableView<ItemDTO> tblItem;

    @FXML
    void btnAddAction(ActionEvent event) {
        String code = txtCode.getText();
        String description = txtDescription.getText();
        String category = txtCategory.getText();
        int qty = Integer.parseInt(txtQty.getText());
        Double price = Double.valueOf(txtPrice.getText());

        itemList.add(new ItemDTO(code,description,category,qty,price));
        tblItem.refresh();
    }

    @FXML
    void btnCustomerAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/customer.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    @FXML
    void btnDeleteAction(ActionEvent event) {
        itemList.remove(tblItem.getSelectionModel().getSelectedItem());
    }

    @FXML
    void btnEmployeeAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/employee.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    @FXML
    void btnHomeAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/home.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    @FXML
    void btnItemAction(ActionEvent event) {

    }

    @FXML
    void btnLogOutAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    @FXML
    void btnResetAction(ActionEvent event) {
        txtCode.setText("");
        txtDescription.setText("");
        txtCategory.setText("");
        txtQty.setText("");
        txtPrice.setText("");
    }

    @FXML
    void btnSupplierAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/supplier.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    @FXML
    void btnUpdateAction(ActionEvent event) {
        ItemDTO dto = tblItem.getSelectionModel().getSelectedItem();

        dto.setCode(txtCode.getText());
        dto.setDescription(txtDescription.getText());
        dto.setCategory(txtCategory.getText());
        dto.setQtyOnHand(Integer.parseInt(txtQty.getText()));
        dto.setUnitPrice(Double.parseDouble(txtPrice.getText()));

        tblItem.refresh();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblItem.setItems(itemList);

        tblItem.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) -> {
            txtCode.setText(newValue.getCode());
            txtDescription.setText(newValue.getDescription());
            txtCategory.setText(newValue.getCategory());
            txtPrice.setText(String.valueOf(newValue.getUnitPrice()));
            txtQty.setText(String.valueOf(newValue.getQtyOnHand()));
        });
    }
}
