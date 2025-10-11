package contraller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.CustomerDTO;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerContraller implements Initializable {

    @FXML private TableView<CustomerDTO> tblCustomer;

    @FXML private TableColumn<CustomerDTO, String> colId;
    @FXML private TableColumn<CustomerDTO, String> colTitle;
    @FXML private TableColumn<CustomerDTO, String> colName;
    @FXML private TableColumn<CustomerDTO, String> colDOB;
    @FXML private TableColumn<CustomerDTO, Double> colSalary;
    @FXML private TableColumn<CustomerDTO, String> colAddress;
    @FXML private TableColumn<CustomerDTO, String> colCity;
    @FXML private TableColumn<CustomerDTO, String> colProvince;
    @FXML private TableColumn<CustomerDTO, String> colPostalCode;

    @FXML private TextField txtId;
    @FXML private ChoiceBox<String> txtTitle;
    @FXML private TextField txtName;
    @FXML private DatePicker txtDate;
    @FXML private TextField txtSalary;
    @FXML private TextField txtAddress;
    @FXML private TextField txtCity;
    @FXML private TextField txtProvince;
    @FXML private TextField txtpostalCode;

    private final ObservableList<CustomerDTO> customerObservable = FXCollections.observableArrayList(
            new CustomerDTO("C001", "Mr.", "Danapala", "1981-02-06", 40000.0, "No.20 Walana", "Panadura", "Western", "12500"),
            new CustomerDTO("C002", "Mrs.", "Perera", "1975-11-23", 55000.0, "No.12 Lake Road", "Kandy", "Central", "20000"),
            new CustomerDTO("C003", "Miss", "Fernando", "1990-07-15", 32000.0, "No.33 Hill Street", "Galle", "Southern", "80000"),
            new CustomerDTO("C004", "Mr.", "Wijesuriya", "1985-03-30", 47000.0, "No.5 Temple Lane", "Kurunegala", "North Western", "60000"),
            new CustomerDTO("C005", "Ms.", "Jayasinghe", "1993-09-12", 39000.0, "No.18 Green Avenue", "Colombo", "Western", "10000")
    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set up ChoiceBox options
        txtTitle.setItems(FXCollections.observableArrayList("Mr.", "Mrs.", "Ms.", "Miss", "Dr."));

        // Set up TableView columns
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        // Set the data to the table
        tblCustomer.setItems(customerObservable);
    }

    // Your button actions go here...
    @FXML void btnAddAction(ActionEvent event) {}
    @FXML void btnCustomerAction(ActionEvent event) {}
    @FXML void btnDeleteAction(ActionEvent event) {}
    @FXML void btnEmployeeAction(ActionEvent event) {}
    @FXML void btnHomeAction(ActionEvent event) {}
    @FXML void btnItemAction(ActionEvent event) {}
    @FXML void btnLogOutAction(ActionEvent event) {}
    @FXML void btnResetAction(ActionEvent event) {}
    @FXML void btnSupplierAction(ActionEvent event) {}
    @FXML void btnUpdateAction(ActionEvent event) {}
}
