package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Developer;
import screen.DevScreen;
import model.User;

import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class LoginController {

    @FXML
    public Text loginMessage;
    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField usernameField;

    @FXML
    public void handleLoginButtonAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username == null || username.isEmpty()) {
            loginMessage.setText("Please type in a username!");
            return;
        }

        if (password == null || password.isEmpty()) {
            loginMessage.setText("Password cannot be empty");
            return;
        }

        // see if user is registered by checking the database file
        BufferedReader reader;
        try {
            File userDatabase = new File(getClass().getClassLoader().getResource("database/users.db").getFile());
            reader = new BufferedReader(new FileReader(
                    userDatabase));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                // read next line
                String[] credentials = line.split(" ");
                if(credentials[1].equals(username) && credentials[2].equals(password))
                {
                    if(credentials[3].equals("dev"))
                    {
                        Developer.openDev(username, (Stage) usernameField.getScene().getWindow());

                        return;
                    }
                    if(credentials[3].equals("user")) {
                        User.openUser(username, (Stage) usernameField.getScene().getWindow());

                        return;
                    }

                    return;
                }
                else if(credentials[1].equals(username) && !credentials[2].equals(password))
                {
                    // we found the user but he entered a wrong password
                    loginMessage.setText("Wrong password!");
                    return;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        loginMessage.setText("We should register you!");
        return;

    }

    public void handleRegisterButtonAction() {
        try {
            Stage stage = (Stage) loginMessage.getScene().getWindow();
//            File userDatabase = new File(getClass().getClassLoader().getResource("database/users.db").getFile());
            Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/register.fxml"));
//            Parent viewStudentsRoot = FXMLLoader.load(getClass().getResource("fxml/registers.fxml"));
            Scene scene = new Scene(viewStudentsRoot, 1600, 1000);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
