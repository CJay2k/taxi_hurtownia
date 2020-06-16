package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginController {

    public Button loginBT;
    public Button closeBT;
    public TextField loginLabel;
    public PasswordField passwordLabel;
    public Label infoLabel;

    @FXML
    public void handleLoginButtonAction() throws IOException {

        switch (authenticateUser()) {
            case -1:
                infoLabel.setText("Proszę uzupełnić wszystkie pola!");
                break;
            case -2:
                infoLabel.setText("Nieprawidłowe dane logowania!");
                break;
            default:
                loadHurtownia();
                break;

        }

    }

    @FXML
    public void handleCloseButtonAction() {
        Stage stage = (Stage) closeBT.getScene().getWindow();
        stage.close();
    }

    private int authenticateUser() {

        String login = loginLabel.getText().toLowerCase();
        String pass = passwordLabel.getText();

        if (login.isEmpty() || pass.isEmpty()) return -1;

        if (login.equals("a") && pass.equals("a")) return 0;

        return -2;
    }

    private void loadHurtownia() throws IOException {

        Parent pane = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxmls/hurtownia.fxml")));
        Stage primaryStage = (Stage) loginBT.getScene().getWindow();
        primaryStage.setTitle("Hurtownia TAXI");
        primaryStage.setResizable(true);

        primaryStage.setScene(new Scene(pane, 1024, 768));

    }
}
