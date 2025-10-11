package contraller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardContraller {

    Stage stage = new Stage();

    @FXML
    void btnCustomerAction(ActionEvent event) {
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/customer.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    @FXML
    void btnEmployeeAction(ActionEvent event) {

    }

    @FXML
    void btnHomeAction(ActionEvent event) {

    }

    @FXML
    void btnItemAction(ActionEvent event) {

    }

    @FXML
    void btnLogOutAction(ActionEvent event) {

    }

    @FXML
    void btnSupplierAction(ActionEvent event) {

    }

    public void btnAddAction(ActionEvent actionEvent) {

    }

    public void btnDeleteAction(ActionEvent actionEvent) {

    }

    public void btnUpdateAction(ActionEvent actionEvent) {

    }

    public void btnResetAction(ActionEvent actionEvent) {

    }
}
