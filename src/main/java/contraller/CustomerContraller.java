package contraller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.dto.CustomerDTO;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class CustomerContraller implements Initializable {

    ObservableList<CustomerDTO> customerObservable = FXCollections.observableArrayList(
            new CustomerDTO("C001", "Mr.", "Danapala", "1981-02-06", 40000.0, "No.20 Walana", "Panadura", "Western", "12500"),
            new CustomerDTO("C002", "Mrs.", "Perera", "1975-11-23", 55000.0, "No.12 Lake Road", "Kandy", "Central", "20000"),
            new CustomerDTO("C003", "Miss", "Fernando", "1990-07-15", 32000.0, "No.33 Hill Street", "Galle", "Southern", "80000"),
            new CustomerDTO("C004", "Mr.", "Wijesuriya", "1985-03-30", 47000.0, "No.5 Temple Lane", "Kurunegala", "North Western", "60000"),
            new CustomerDTO("C005", "Mrs.", "Jayasinghe", "1993-09-12", 39000.0, "No.18 Green Avenue", "Colombo", "Western", "10000")
    );

    Stage stage = new Stage();

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCity;

    @FXML
    private TableColumn<?, ?> colDOB;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPostalCode;

    @FXML
    private TableColumn<?, ?> colProvince;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private TableView<CustomerDTO> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCity;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtProvince;

    @FXML
    private TextField txtSalary;

    @FXML
    private ChoiceBox<String> txtTitle;

    String titleArray[] = {"Mr.","Mrs.","Miss"};

    @FXML
    private TextField txtpostalCode;

    @FXML
    void btnAddAction(ActionEvent event) {
        String id = txtId.getText();
        String title = txtTitle.getValue();
        String name = txtName.getText();
        String dob = String.valueOf(txtDate.getValue());
        Double salary = Double.valueOf(txtSalary.getText());
        String city = txtCity.getText();
        String province = txtProvince.getText();
        String postalCode = txtpostalCode.getText();
        String address = txtAddress.getText();

        customerObservable.add(new CustomerDTO(id,title,name,dob,salary,address,city,province,postalCode));
    }

    @FXML
    void btnCustomerAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteAction(ActionEvent event) {
        customerObservable.remove(tblCustomer.getSelectionModel().getSelectedItem());
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

    }

    @FXML
    void btnResetAction(ActionEvent event) {
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtProvince.setText("");
        txtpostalCode.setText("");
        txtSalary.setText("");
        txtCity.setText("");
        txtDate.setValue(null);
        txtTitle.setValue(null);
    }

    @FXML
    void btnSupplierAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateAction(ActionEvent event) {
        CustomerDTO dto = tblCustomer.getSelectionModel().getSelectedItem();

        dto.setId(txtId.getText());
        dto.setTitle(txtTitle.getValue());
        dto.setName(txtName.getText());
        dto.setDob(String.valueOf(txtDate.getValue()));
        dto.setCity(txtCity.getText());
        dto.setAddress(txtAddress.getText());
        dto.setProvince(txtProvince.getText());
        dto.setSalary(Double.valueOf(txtSalary.getText()));
        dto.setPostalCode(txtpostalCode.getText());

        tblCustomer.refresh();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        txtTitle.getItems().addAll(titleArray);

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        tblCustomer.setItems(customerObservable);

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) ->{
            txtId.setText(newValue.getId());
            txtName.setText(newValue.getName());
            txtAddress.setText(newValue.getAddress());
            txtProvince.setText(newValue.getProvince());
            txtpostalCode.setText(newValue.getPostalCode());
            txtSalary.setText(String.valueOf(newValue.getSalary()));
            txtCity.setText(newValue.getCity());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(newValue.getDob(), formatter);
            txtDate.setValue(localDate);
            if (newValue.getTitle().equals("Mr.")){
                txtTitle.setValue(txtTitle.getItems().get(0));
            } else if (newValue.getTitle().equals("Mrs.")) {
                txtTitle.setValue(txtTitle.getItems().get(1));
            }else if (newValue.getTitle().equals("Miss")){
                txtTitle.setValue(txtTitle.getItems().get(2));
            }
        });
    }
}
