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
import model.dto.SupplierDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SupplierContraller implements Initializable {

    ObservableList<SupplierDTO> supplierList = FXCollections.observableArrayList(
            new SupplierDTO("S001", "Fernando", "Agro Foods Pvt Ltd", "No.45 Main Street", "Matara", "Southern", "81000", "0712345678", "agrofoods@gmail.com"),
            new SupplierDTO("S002", "Perera", "Green Leaf Organics", "No.12 Lake Road", "Kandy", "Central", "20000", "0723456789", "greenleaf@gmail.com"),
            new SupplierDTO("S003", "Silva", "Oceanic Exports Ltd", "No.88 Beach Side", "Galle", "Southern", "80000", "0771234567", "oceanicexports@gmail.com"),
            new SupplierDTO("S004", "Kumara", "Sunshine Traders", "No.23 Hill Street", "Nuwara Eliya", "Central", "22200", "0789876543", "sunshine@gmail.com"),
            new SupplierDTO("S005", "Wijesinghe", "Coconut House", "No.67 Palm Avenue", "Kurunegala", "North Western", "60000", "0708765432", "coconuthouse@gmail.com")
    );
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

        supplierList.add(new SupplierDTO(id, name, companyName, address, city, province, postalCode, phone, email));
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
        supplierList.remove(tblSupplier.getSelectionModel().getSelectedItem());
    }

    @FXML
    void btnEmployeeAction(ActionEvent event) {

    }

    @FXML
    void btnHomeAction(ActionEvent event) {

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
