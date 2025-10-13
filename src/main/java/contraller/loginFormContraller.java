package contraller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class loginFormContraller {

    Stage stage = new Stage();

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    void btnLoginAction(ActionEvent event) {
        if (txtUserName.getText().equals("p") && txtPassword.getText().equals("1")){
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();

            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/home.fxml"))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.show();

        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText(null);
            alert.setContentText("Username or password is incorrect.");
            alert.showAndWait();
        }
    }

}
