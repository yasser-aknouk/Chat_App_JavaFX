package emsi.ma.clientschats;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class RegisterController{
        @FXML
    TextField passwordField;
        @FXML
    TextField usernameField;

        @FXML
    RadioButton rb_femme;
        @FXML
        RadioButton rb_homme;


    @FXML
    public void onsignupClick(ActionEvent actionEvent) {
        ToggleGroup toggleGroup= new ToggleGroup();
        rb_femme.setToggleGroup(toggleGroup);
        rb_homme.setToggleGroup(toggleGroup);
        rb_homme.setSelected(true);

        String toggleName= ((RadioButton) toggleGroup.getSelectedToggle()).getText();

        if(!usernameField.getText().trim().isEmpty() && !passwordField.getText().trim().isEmpty())
        {
            DBUtils.signUpUser(actionEvent,usernameField.getText(),passwordField.getText(),toggleName);
        }
        else{
            System.out.println("please fill in the infos");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please Fill in the infos to sign up");
            alert.show();
        }

    }

    @FXML
    public void onsloginClick(ActionEvent actionEvent) {
        DBUtils.ChangeScene(actionEvent,"hello-view.fxml");
    }
}
