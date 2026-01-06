package lms;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lms.database.UserDAO;
import lms.models.User;

public class LoginScreen {

    public void show(Stage stage) {

        Label title = new Label("Login");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setMaxWidth(250);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(250);

        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");

        loginButton.setPrefWidth(200);
        registerButton.setPrefWidth(200);

        loginButton.setOnAction(e -> {

            UserDAO dao = new UserDAO();
            User user = dao.findUser(
                    usernameField.getText(),
                    passwordField.getText()
            );

            if (user != null) {
                new MainMenu().show(stage, user);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Greška");
                alert.setHeaderText(null);
                alert.setContentText("Pogrešno korisničko ime ili lozinka");
                alert.showAndWait();
            }
        });

        registerButton.setOnAction(e ->
                new RegisterScreen().show(stage)
        );

        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(40));
        layout.getChildren().addAll(
                title,
                usernameField,
                passwordField,
                loginButton,
                registerButton
        );

        ThemeUtil.applyPink(layout);
        ThemeUtil.styleTitleLight(title);
        ThemeUtil.styleButtonLight(loginButton);
        ThemeUtil.styleButtonLight(registerButton);

        stage.setScene(new Scene(layout, 500, 400));
        stage.show();
    }
}