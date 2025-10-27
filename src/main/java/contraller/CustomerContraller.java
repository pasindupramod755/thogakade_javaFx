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
import model.dto.CustomerDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class CustomerContraller implements Initializable {

    ObservableList<CustomerDTO> customerObservable = FXCollections.observableArrayList();

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
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel","root","1234");
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Customer WHERE customer_id = ?");
            preparedStatement.setString(1,txtId.getText());
            int i = preparedStatement.executeUpdate();

            if (i>0){
                new Alert(Alert.AlertType.INFORMATION, "Customer Deleted successfully!").show();
            }else {
                new Alert(Alert.AlertType.WARNING, "Customer not found!").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        customerObservable.remove(tblCustomer.getSelectionModel().getSelectedItem());
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
        CustomerDTO dto = tblCustomer.getSelectionModel().getSelectedItem();

//        dto.setId(txtId.getText());
        dto.setTitle(txtTitle.getValue());
        dto.setName(txtName.getText());
        dto.setDob(String.valueOf(txtDate.getValue()));
        dto.setCity(txtCity.getText());
        dto.setAddress(txtAddress.getText());
        dto.setProvince(txtProvince.getText());
        dto.setSalary(Double.valueOf(txtSalary.getText()));
        dto.setPostalCode(txtpostalCode.getText());

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel","root","1234");
            String sql = "UPDATE Customer SET title=?, name=?, dob=?, salary=?, address=?, city=?, province=?, postal_code=? WHERE customer_id=?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, txtTitle.getValue());
            pstm.setString(2, txtName.getText());
            pstm.setString(3, String.valueOf(txtDate.getValue()));
            pstm.setString(4, txtSalary.getText());
            pstm.setString(5, txtAddress.getText());
            pstm.setString(6, txtCity.getText());
            pstm.setString(7, txtProvince.getText());
            pstm.setString(8, txtpostalCode.getText());
            pstm.setString(9, txtId.getText());

            int i = pstm.executeUpdate();
            if (i>0){
                new Alert(Alert.AlertType.INFORMATION, "Customer updated successfully!").show();
            }else {
                new Alert(Alert.AlertType.WARNING, "Customer not found!").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        tblCustomer.refresh();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        txtTitle.getItems().addAll(titleArray);

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel","root","1234");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Customer");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                customerObservable.add(new CustomerDTO(
                        resultSet.getString("customer_id"),
                        resultSet.getString("title"),
                        resultSet.getString("name"),
                        resultSet.getString("dob"),
                        resultSet.getDouble("salary"),
                        resultSet.getString("address"),
                        resultSet.getString("city"),
                        resultSet.getString("province"),
                        resultSet.getString("postal_code")
                ));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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
