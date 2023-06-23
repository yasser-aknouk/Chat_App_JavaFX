package emsi.ma.clientschats;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloController {
    @FXML
    TextField userField;
    @FXML TextField passwordField;



    @FXML
    private Button Loginbtn;

    @FXML
    public void onLoginButtonClick(ActionEvent actionEvent) {
        DBUtils.LogIn(actionEvent,userField.getText(),passwordField.getText());

    }
    @FXML
    public void onsignupClick(ActionEvent actionEvent) {
        DBUtils.ChangeScene(actionEvent,"register.fxml");
    }
}