package contraller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.dto.EmployeeDTO;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EmployeeContraller implements Initializable {

    ObservableList<EmployeeDTO> employeeList = FXCollections.observableArrayList(
            new EmployeeDTO("E001", "Sunil Perera", "832451230V", "1983-07-12", "Manager", 75000.0, "0712456789", "No.15 Temple Road, Kalutara", "2018-05-10", "Active"),
            new EmployeeDTO("E002", "Nimali Fernando", "925431789V", "1992-03-22", "Accountant", 58000.0, "0771234567", "No.23 Lake View, Kandy", "2019-01-15", "Active"),
            new EmployeeDTO("E003", "Ruwan Silva", "883215478V", "1988-11-03", "Sales Executive", 45000.0, "0719876543", "No.88 Hill Street, Galle", "2020-08-20", "Active"),
            new EmployeeDTO("E004", "Ishara Jayasuriya", "943215987V", "1994-06-30", "HR Officer", 50000.0, "0703456789", "No.45 Garden Road, Colombo", "2021-03-01", "Inactive"),
            new EmployeeDTO("E005", "Kamal Rajapaksha", "801234569V", "1980-12-10", "Driver", 40000.0, "0754321987", "No.12 Main Street, Matara", "2017-09-18", "Active")
    );

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colDOB;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colJoinDarte;

    @FXML
    private TableColumn<?, ?> colNIC;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhone;

    @FXML
    private TableColumn<?, ?> colPosition;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableView<EmployeeDTO> tblEmployee;

    @FXML
    private TextField txtAddress;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNIC;

    @FXML
    private DatePicker txtJoinDate;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtPosition;

    @FXML
    private TextField txtSalary;

    @FXML
    private TextField txtStatus;

    @FXML
    void btnAddAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String nic = txtNIC.getText();
        String dob = txtDate.getValue().toString();
        String position = txtPosition.getText();
        double salary = Double.parseDouble(txtSalary.getText());
        String contactNumber = txtPhone.getText();
        String address = txtAddress.getText();
        String joinedDate = txtJoinDate.getValue().toString();
        String status = txtStatus.getText();

        EmployeeDTO newEmployee = new EmployeeDTO(id, name, nic, dob, position, salary, contactNumber, address, joinedDate, status);
        employeeList.add(newEmployee);

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
        employeeList.remove(tblEmployee.getSelectionModel().getSelectedItem());
    }

    @FXML
    void btnEmployeeAction(ActionEvent event) {
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
        txtId.clear();
        txtName.clear();
        txtNIC.clear();
        txtDate.setValue(null);
        txtPosition.clear();
        txtSalary.clear();
        txtPhone.clear();
        txtAddress.clear();
        txtJoinDate.setValue(null);
        txtStatus.clear();
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
        EmployeeDTO dto = tblEmployee.getSelectionModel().getSelectedItem();

        if (dto != null) {
            dto.setId(txtId.getText());
            dto.setName(txtName.getText());
            dto.setNic(txtNIC.getText());
            dto.setDob(txtDate.getValue().toString());
            dto.setPosition(txtPosition.getText());
            dto.setSalary(Double.parseDouble(txtSalary.getText()));
            dto.setContactNumber(txtPhone.getText());
            dto.setAddress(txtAddress.getText());
            dto.setJoinedDate(txtJoinDate.getValue().toString());
            dto.setStatus(txtStatus.getText());
            tblEmployee.refresh();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colJoinDarte.setCellValueFactory(new PropertyValueFactory<>("joinedDate"));

        tblEmployee.setItems(employeeList);

        tblEmployee.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
            txtId.setText(newValue.getId());
            txtName.setText(newValue.getName());
            txtNIC.setText(newValue.getNic());
            txtDate.setValue(LocalDate.parse(newValue.getDob()));
            txtPosition.setText(newValue.getPosition());
            txtSalary.setText(String.valueOf(newValue.getSalary()));
            txtPhone.setText(newValue.getContactNumber());
            txtAddress.setText(newValue.getAddress());
            txtJoinDate.setValue(LocalDate.parse(newValue.getJoinedDate()));
            txtStatus.setText(newValue.getStatus());
        });
    }
}
