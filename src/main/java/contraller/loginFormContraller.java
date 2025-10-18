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
    String[] usernameArray = {"pasindu","tharindu","lahiru","navindu"};
    String[] passwordArray = {"12345","23456","34567","45678"};


    public int searchIndex(String[] stringArray , String user){
        int count = 0;
        for(String username : stringArray){
            if(username.equals(user)){
                return count;
            }
            count++;
        }
        return -1;
    }

    public boolean log(String user,String pass){

        return true;
    }

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    void btnLoginAction(ActionEvent event) {

        if (searchIndex(usernameArray,txtUserName.getText()) >= 0 && passwordArray[searchIndex(usernameArray,txtUserName.getText())].equals(txtPassword.getText())){
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
