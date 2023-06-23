package emsi.ma.clientschats;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Connexion extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("connection.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("CHAT App");
        stage.setScene(scene);
        stage.show();
}}
