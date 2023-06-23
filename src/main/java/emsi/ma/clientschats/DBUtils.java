package emsi.ma.clientschats;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class DBUtils {
    public static void ChangeScene(ActionEvent event,String FxmlFile)
    {
        Parent root = null;
        try{
            root = FXMLLoader.load(DBUtils.class.getResource(FxmlFile));
        }
        catch(IOException e)
        { e.printStackTrace();}
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("title");
        stage.setScene(new Scene(root,600,400));
        stage.show();
    }
    public static void signUpUser(ActionEvent event,String username,String password,String sexe)
    {
        Connection connection= null;
        PreparedStatement psInsert = null;
        PreparedStatement pscheckUserExists=null;
        ResultSet resultSet= null;

        try{
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/chat_app","root","Developpeur2000");
            pscheckUserExists = connection.prepareStatement("SELECT * FROM users WHERE username=?");
            pscheckUserExists.setString(1,username);
            resultSet = pscheckUserExists.executeQuery();

            if(resultSet.isBeforeFirst())
            {
                System.out.println("user already exits!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username");
                alert.show();
            }

            else{
                psInsert = connection.prepareStatement("INSERT INTO users (username,password,sexe) VALUES(?,?,?)");
                psInsert.setString(1,username);
                psInsert.setString(2,password);
                psInsert.setString(3,sexe);
                psInsert.executeUpdate();
                ChangeScene(event,"hello-view.fxml");
            }
        }
        catch(SQLException e)
        {e.printStackTrace();}
        finally{
            if( resultSet !=null)
            {
                try {
                    resultSet.close();
                }
                catch(SQLException e)
                {e.printStackTrace();}
            }
            if( pscheckUserExists !=null)
            {
                try {
                    pscheckUserExists.close();
                }
                catch(SQLException e)
                {e.printStackTrace();}
            }
            if( psInsert !=null)
            {
                try {
                    psInsert.close();
                }
                catch(SQLException e)
                {e.printStackTrace();}
            }
            if( connection !=null)
            {
                try {
                    connection.close();
                }
                catch(SQLException e)
                {e.printStackTrace();}
            }
        }
    }
    public static void LogIn(ActionEvent event,String username,String password)
    {
        Connection connection= null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;
        try{
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/chat_app","root","Developpeur2000");
            preparedStatement = connection.prepareStatement("SELECT password FROM users WHERE username = ?");
            preparedStatement.setString(1,username);
            resultSet =preparedStatement.executeQuery();

            if(!resultSet.isBeforeFirst())
            {
                System.out.println("User Not Found In the database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Username or password ar incorrect ");
                alert.show();
            }
            else{
                while (resultSet.next())
                {
                    String retrievePassword =  resultSet.getString("password");
                    if(retrievePassword.equals(password) )
                    {
                        ChangeScene(event,"connection.fxml");
                    }
                    else{
                        System.out.println("password inccorect");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Password Incorrect ");
                        alert.show();
                    }

                }
            }

        }
        catch(SQLException e)
        {e.printStackTrace();}
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if( connection !=null)
            {
                try {
                    connection.close();
                }
                catch(SQLException e)
                {e.printStackTrace();}
            }
        }
    }


}
