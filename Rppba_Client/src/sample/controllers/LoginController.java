package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Request;

import javax.swing.*;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField Login;

    @FXML
    private Button SignIn;

    @FXML
    private PasswordField Password;
    private boolean isresult=false;
    ResultSet Rs;
    @FXML
    void initialize() {
        SignIn.setOnAction(event -> {
            String loginText = Login.getText().trim();
            String PasswordText = Password.getText().trim();
            if (!loginText.equals("") && !Password.equals("")) {
                try {
                    loginuser(loginText, PasswordText);
                    if(isresult==false){
                        JOptionPane.showMessageDialog(null, "Пользователь с такими данными не найден,попробуйте ещё раз", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Password or Login is empty");
            }
        });
    }

    private void loginuser(String loginText, String password) throws SQLException, ClassNotFoundException {
        Request reqest = new Request();
        String request = "getUser" + "," + loginText + "," + password;
        try{
            String response = reqest.doRequest(request);
            if(!response.isEmpty()){
                isresult=true;
                String[] items=response.split(";");
                for(int i=0;i<items.length;i++){
                    String[] propertys=items[i].split(",");
                    SignIn.getScene().getWindow().hide();
                    FXMLLoader loadader=new FXMLLoader();
                    loadader.setLocation(getClass().getResource("../Views/Whole_Programm.fxml"));
                    try {
                        loadader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    MainPageController mainPageController=loadader.getController();
                    //mainPageController.set(propertys[0],propertys[1],propertys[2]);
                    Parent root =loadader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.showAndWait();
                }
            }
        }
        catch (NullPointerException e){
        }

    }
}

