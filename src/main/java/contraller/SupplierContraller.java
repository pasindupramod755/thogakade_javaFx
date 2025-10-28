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
import model.dto.CustomerDTO;
import model.dto.SupplierDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class SupplierContraller implements Initializable {

    ObservableList<SupplierDTO> supplierList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCity;

    @FXML
    private TableColumn<?, ?> colCompanyName;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhone;

    @FXML
    private TableColumn<?, ?> colPostalCode;

    @FXML
    private TableColumn<?, ?> colProvince;

    @FXML
    private TableView<SupplierDTO> tblSupplier;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtCompanyName;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtProvince;

    @FXML
    private TextField txtpostalCode;

    //Add Function
    @FXML
    void btnAddAction(ActionEvent event) {

        String id = txtId.getText();
        String name = txtName.getText();
        String companyName = txtCompanyName.getText();
        String address = txtAddress.getText();
        String city = txtCity.getText();
        String province = txtProvince.getText();
        String postalCode = txtpostalCode.getText();
        String phone = txtPhone.getText();
        String email = txtEmail.getText();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "1234");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO supplier VALUES (?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, companyName);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, city);
            preparedStatement.setString(6, province);
            preparedStatement.setString(7, postalCode);
            preparedStatement.setString(8, phone);
            preparedStatement.setString(9, email);
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                new Alert(Alert.AlertType.INFORMATION, "Supplier Added successfully!").show();
                supplierList.add(new SupplierDTO(id, name, companyName, address, city, province, postalCode, phone, email));
                tblSupplier.refresh();
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
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel","root","1234");
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM supplier WHERE supplier_id = ?");
            preparedStatement.setString(1,txtId.getText());
            int i = preparedStatement.executeUpdate();
            if (i>0){
                new Alert(Alert.AlertType.INFORMATION, "Supplier Deleted successfully!").show();
                supplierList.remove(tblSupplier.getSelectionModel().getSelectedItem());
            }else {
                new Alert(Alert.AlertType.WARNING, "Supplier not found!").show();
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
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/item.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
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
        txtId.setText("");
        txtName.setText("");
        txtCompanyName.setText("");
        txtAddress.setText("");
        txtCity.setText("");
        txtProvince.setText("");
        txtpostalCode.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
    }

    @FXML
    void btnSupplierAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateAction(ActionEvent event) {
        SupplierDTO dto = tblSupplier.getSelectionModel().getSelectedItem();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel","root","1234");
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE supplier SET name=?, company_name=?, address=?, city=?, province=?, postal_code=?, phone=?, email=? WHERE supplier_id=?");
            preparedStatement.setString(1,txtName.getText());
            preparedStatement.setString(2,txtCompanyName.getText());
            preparedStatement.setString(3,txtAddress.getText());
            preparedStatement.setString(4,txtCity.getText());
            preparedStatement.setString(5,txtProvince.getText());
            preparedStatement.setString(6,txtpostalCode.getText());
            preparedStatement.setString(7,txtPhone.getText());
            preparedStatement.setString(8,txtEmail.getText());
            preparedStatement.setString(9,txtId.getText());
            int i = preparedStatement.executeUpdate();
            if (i>0){
                new Alert(Alert.AlertType.INFORMATION, "Supplier Update successfully!").show();

            }else {
                new Alert(Alert.AlertType.WARNING, "Supplier not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
            throw new RuntimeException(e);
        }

        if (dto != null) {
            dto.setId(txtId.getText());
            dto.setName(txtName.getText());
            dto.setCompanyName(txtCompanyName.getText());
            dto.setAddress(txtAddress.getText());
            dto.setCity(txtCity.getText());
            dto.setProvince(txtProvince.getText());
            dto.setPostalCode(txtpostalCode.getText());
            dto.setPhone(txtPhone.getText());
            dto.setEmail(txtEmail.getText());
            tblSupplier.refresh();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel","root","1234");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM supplier");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                supplierList.add(new SupplierDTO(
                        resultSet.getString("supplier_id"),
                        resultSet.getString("name"),
                        resultSet.getString("company_name"),
                        resultSet.getString("address"),
                        resultSet.getString("city"),
                        resultSet.getString("province"),
                        resultSet.getString("postal_code"),
                        resultSet.getString("phone"),
                        resultSet.getString("email")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCompanyName.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("province"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        tblSupplier.setItems(supplierList);

        tblSupplier.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
            txtId.setText(newValue.getId());
            txtName.setText(newValue.getName());
            txtCompanyName.setText(newValue.getCompanyName());
            txtAddress.setText(newValue.getAddress());
            txtCity.setText(newValue.getCity());
            txtProvince.setText(newValue.getProvince());
            txtpostalCode.setText(newValue.getPostalCode());
            txtPhone.setText(newValue.getPhone());
            txtEmail.setText(newValue.getEmail());
        });
    }
}
