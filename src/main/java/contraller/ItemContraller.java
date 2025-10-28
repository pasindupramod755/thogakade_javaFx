package contraller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.dto.ItemDTO;
import model.dto.SupplierDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ItemContraller implements Initializable {

    ObservableList<ItemDTO> itemList = FXCollections.observableArrayList();

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

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "1234");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Item VALUES (?,?,?,?,?)");
            preparedStatement.setString(1, code);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, category);
            preparedStatement.setInt(4, qty);
            preparedStatement.setDouble(5, price);
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                new Alert(Alert.AlertType.INFORMATION, "Supplier Added successfully!").show();
                itemList.add(new ItemDTO(code, description, category, qty, price));
                tblItem.refresh();
            } else {
                new Alert(Alert.AlertType.WARNING, "Supplier not found!").show();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
            throw new RuntimeException(e);
        }
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
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "1234");
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Item WHERE item_code = ?");
            preparedStatement.setString(1, txtCode.getText());
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                new Alert(Alert.AlertType.INFORMATION, "Item Deleted successfully!").show();
                itemList.remove(tblItem.getSelectionModel().getSelectedItem());
            } else {
                new Alert(Alert.AlertType.WARNING, "Item not found!").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "1234");
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Item SET description=?, category=?, qty_on_hand=?, unit_price=? WHERE item_code=?");
            preparedStatement.setString(1, txtDescription.getText());
            preparedStatement.setString(2, txtCategory.getText());
            preparedStatement.setInt(3, Integer.parseInt(txtQty.getText()));
            preparedStatement.setDouble(4, Double.parseDouble(txtPrice.getText()));
            preparedStatement.setString(5, txtCode.getText());
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                new Alert(Alert.AlertType.INFORMATION, "Item Update successfully!").show();
                dto.setCode(txtCode.getText());
                dto.setDescription(txtDescription.getText());
                dto.setCategory(txtCategory.getText());
                dto.setQtyOnHand(Integer.parseInt(txtQty.getText()));
                dto.setUnitPrice(Double.parseDouble(txtPrice.getText()));
                tblItem.refresh();
            } else {
                new Alert(Alert.AlertType.WARNING, "Item not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "1234");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Item");
            while (resultSet.next()) {
                itemList.add(new ItemDTO(
                        resultSet.getString("item_code"),
                        resultSet.getString("description"),
                        resultSet.getString("category"),
                        resultSet.getInt("qty_on_hand"),
                        resultSet.getDouble("unit_price")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblItem.setItems(itemList);

        tblItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            txtCode.setText(newValue.getCode());
            txtDescription.setText(newValue.getDescription());
            txtCategory.setText(newValue.getCategory());
            txtPrice.setText(String.valueOf(newValue.getUnitPrice()));
            txtQty.setText(String.valueOf(newValue.getQtyOnHand()));
        });
    }
}
