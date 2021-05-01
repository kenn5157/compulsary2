package view;

import bll.ConfigMan;
import bll.LogicManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {



    private LogicManager logicManager;
    private ConfigMan configMan;

    @FXML private TextField usernameField;
    public PasswordField passwdField;

    public LoginController(){
        logicManager = new LogicManager();
        configMan = new ConfigMan();
    }

    @FXML
    private void login() throws Exception {
        String username = usernameField.getText();
        String passwd = passwdField.getText();
        int status = logicManager.getUserStatus(username,passwd);
        int id = logicManager.getUserNameID(username,passwd);
        if (status == 1){
            configMan.loggedInUserStatus = 1;
            configMan.loggedInUserID = id;
            showUserView("student");

        } else if (status == 2){
            configMan.loggedInUserStatus = 2;
            showUserView("teacher");
        } else System.out.println("User or password wrong or does not exist");
    }

    private void showUserView(String userViewFile) throws Exception {
        try {
            String username = usernameField.getText();
            String passwd = passwdField.getText();
            String fullName = logicManager.getFullName(username,passwd);
            int status = logicManager.getUserStatus(username,passwd);
            String stat = "Unknown";
            if (status == 1){
                stat = "Student";
            } else if (status == 2){
                stat = "Teacher";
            }


            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(LoginController.class.getResource(userViewFile + ".fxml"));
            AnchorPane userScreen = loader.load();

            Scene scene = new Scene(userScreen);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle(fullName + " (" + stat + ")");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void cancel() {
        exit();
    }

    private void exit() {
        System.exit(0);
    }

}
