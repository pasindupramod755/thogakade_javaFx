package contraller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.dto.EmployeeDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EmployeeContraller implements Initializable {

    ObservableList<EmployeeDTO> employeeList = FXCollections.observableArrayList();

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


        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel","root","1234");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Employee (id, name, nic, dob, position, salary, contact_number, address, joined_date, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1,id);
            preparedStatement.setString(2,name);
            preparedStatement.setString(3,nic);
            preparedStatement.setString(4,dob);
            preparedStatement.setString(5,position);
            preparedStatement.setDouble(6,salary);
            preparedStatement.setString(7,contactNumber);
            preparedStatement.setString(8,address);
            preparedStatement.setString(9,joinedDate);
            preparedStatement.setString(10,status);
            int i = preparedStatement.executeUpdate();
            if (i>0){
                new Alert(Alert.AlertType.INFORMATION, "employee Added successfully!").show();
                employeeList.add(new EmployeeDTO(id, name, nic, dob, position, salary, contactNumber, address, joinedDate, status));
                tblEmployee.refresh();

            }else {
                new Alert(Alert.AlertType.WARNING, "employee not found!").show();
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
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM employee WHERE id = ?");
            preparedStatement.setString(1,txtId.getText());
            int i = preparedStatement.executeUpdate();
            if (i>0){
                new Alert(Alert.AlertType.INFORMATION, "employee Deleted successfully!").show();
            }else {
                new Alert(Alert.AlertType.WARNING, "employee not found!").show();
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel","root","1234");
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE employee SET name=?, nic=?, dob=?, position=?, salary=?, contact_number=?, address=?, joined_date=?, status=? WHERE id=?");
            preparedStatement.setString(1,txtName.getText());
            preparedStatement.setString(2,txtNIC.getText());
            preparedStatement.setString(3, String.valueOf(txtDate.getValue()));
            preparedStatement.setString(4,txtPosition.getText());
            preparedStatement.setDouble(5, Double.parseDouble(txtSalary.getText()));
            preparedStatement.setString(6,txtPhone.getText());
            preparedStatement.setString(7,txtAddress.getText());
            preparedStatement.setString(8, String.valueOf(txtJoinDate.getValue()));
            preparedStatement.setString(9,txtStatus.getText());
            preparedStatement.setString(10,txtId.getText());
            int i = preparedStatement.executeUpdate();
            if (i>0){
                new Alert(Alert.AlertType.INFORMATION, "Emplpoyee updated successfully!").show();
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
            }else {
                new Alert(Alert.AlertType.WARNING, "Emplpoyee not found!").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel","root","1234");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                employeeList.add(new EmployeeDTO(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("nic"),
                        resultSet.getString("dob"),
                        resultSet.getString("position"),
                        resultSet.getDouble("salary"),
                        resultSet.getString("contact_number"),
                        resultSet.getString("address"),
                        resultSet.getString("joined_date"),
                        resultSet.getString("status")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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
