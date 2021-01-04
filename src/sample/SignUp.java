package sample;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class SignUp {
    public static void createSceneSignUp(Scene sceneStart, Scene sceneLogIn, User user, GridPane gridSignUp, Stage window) {
        gridSignUp.setAlignment(Pos.CENTER_LEFT);
        gridSignUp.setHgap(10);
        gridSignUp.setVgap(12);
        gridSignUp.setPadding(new Insets(30));
        gridSignUp.setStyle("-fx-background-color:lightblue");

        Button btnSubmit = new Button("Submit");
        btnSubmit.setStyle("-fx-font-size: 12pt;");
        Button btnGoBack = new Button("Go back");
        btnGoBack.setStyle("-fx-font-size: 12pt;");

        //FIRST NAME, LAST NAME, EMAIL, PHONE NUMBER, RESIDENCE PLACE, PASSWORD
        Label labelLastName = new Label("Last Name:");
        TextField tfLastName = new TextField();
        Label labelFirstName = new Label("First Name:");
        TextField tfFirstName = new TextField();
        Label labelEmail = new Label("Email:");
        TextField tfEmail = new TextField();
        Label labelPhone = new Label("Phone number:  (+)40");
        TextField tfPhone = new TextField();
        Label labelResidence = new Label("Residence place:");
        TextField tfResidence = new TextField();
        Label labelPassword = new Label("Password:");
        PasswordField pfPassword = new PasswordField();
        Label labelConfirmPassword = new Label("Confirm password:");
        PasswordField pfConfirmPassword = new PasswordField();

        HBox hbButtons = new HBox();
        hbButtons.setSpacing(10.0);
        hbButtons.getChildren().addAll(btnSubmit, btnGoBack);
        btnGoBack.setOnAction(e -> {
            tfLastName.clear();
            tfFirstName.clear();
            tfEmail.clear();
            tfPhone.clear();
            tfResidence.clear();
            pfPassword.clear();
            pfConfirmPassword.clear();
            window.setScene(sceneStart);
        });

        gridSignUp.add(labelFirstName, 0, 0);
        gridSignUp.add(tfFirstName, 1, 0);
        gridSignUp.add(labelLastName, 0, 1);
        gridSignUp.add(tfLastName, 1, 1);
        gridSignUp.add(labelEmail, 0, 2);
        gridSignUp.add(tfEmail, 1, 2);
        gridSignUp.add(labelPhone, 0, 3);
        gridSignUp.add(tfPhone, 1, 3);
        gridSignUp.add(labelResidence, 0, 4);
        gridSignUp.add(tfResidence, 1, 4);
        gridSignUp.add(labelPassword, 0, 5);
        gridSignUp.add(pfPassword, 1, 5);
        gridSignUp.add(labelConfirmPassword, 0, 6);
        gridSignUp.add(pfConfirmPassword, 1, 6);
        gridSignUp.add(hbButtons, 0, 7, 7, 1);

        btnSubmit.setOnAction(e -> {
            if (tfFirstName.getText().isEmpty()) {
                AlertBox.showAlert(Alert.AlertType.ERROR, gridSignUp.getScene().getWindow(), "Form Error!", "Please enter your first name");
                return;
            }
            if (tfLastName.getText().isEmpty()) {
                AlertBox.showAlert(Alert.AlertType.ERROR, gridSignUp.getScene().getWindow(), "Form Error!", "Please enter your last name");
                return;
            }
            if (tfEmail.getText().isEmpty()) {
                AlertBox.showAlert(Alert.AlertType.ERROR, gridSignUp.getScene().getWindow(), "Form Error!", "Please enter your email");
                return;
            }
            if (tfPhone.getText().isEmpty()) {
                AlertBox.showAlert(Alert.AlertType.ERROR, gridSignUp.getScene().getWindow(), "Form Error!", "Please enter a phone number");
                return;
            }
            if (tfResidence.getText().isEmpty()) {
                AlertBox.showAlert(Alert.AlertType.ERROR, gridSignUp.getScene().getWindow(), "Form Error!", "Please enter your residence place");
                return;
            }
            if (pfPassword.getText().isEmpty()) {
                AlertBox.showAlert(Alert.AlertType.ERROR, gridSignUp.getScene().getWindow(), "Form Error!", "Please enter a password");
                return;
            }
            if (pfConfirmPassword.getText().isEmpty()) {
                AlertBox.showAlert(Alert.AlertType.ERROR, gridSignUp.getScene().getWindow(), "Form Error!", "Please confirm password");
                return;
            }
            if (!tfEmail.getText().matches("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
                AlertBox.showAlert(Alert.AlertType.ERROR, gridSignUp.getScene().getWindow(), "Form Error!", "Please enter a valid email address");
                return;
            }
            if(DataValidation.checkEmailExistence(tfEmail)){
                AlertBox.showAlert(Alert.AlertType.ERROR, gridSignUp.getScene().getWindow(), "Form Error!", "There is already an account associated with this email address");
                return;
            }
            if (!tfPhone.getText().matches("\\d{9}")) {
                AlertBox.showAlert(Alert.AlertType.ERROR, gridSignUp.getScene().getWindow(), "Form Error!", "Please enter a valid phone number");
                return;
            }
            if (!pfPassword.getText().equals(pfConfirmPassword.getText())) {
                AlertBox.showAlert(Alert.AlertType.ERROR, gridSignUp.getScene().getWindow(), "Form Error!", "The two passwords don't match");
                return;
            }
            if(pfPassword.getLength() < 6){
                AlertBox.showAlert(Alert.AlertType.ERROR, gridSignUp.getScene().getWindow(), "Form Error!", "Your password must have at least 6 characters");
                return;
            }

            FileManager.createUser(user, tfLastName, tfFirstName, tfEmail, tfPhone, tfResidence, pfPassword);
            AlertBox.showAlert(Alert.AlertType.CONFIRMATION, gridSignUp.getScene().getWindow(), "Registration Successful!", "Welcome " + tfFirstName.getText());
            tfLastName.clear();
            tfFirstName.clear();
            tfEmail.clear();
            tfPhone.clear();
            tfResidence.clear();
            pfPassword.clear();
            pfConfirmPassword.clear();
            window.setScene(sceneLogIn);
        });
    }
    public static User getUserInfo(User user, TextField tfLastName, TextField tfFirstName, TextField tfEmail,
                                   TextField tfPhone, TextField tfResidence, PasswordField pfPassword) {
        user.setFirstName(tfFirstName.getText());
        user.setLastName(tfLastName.getText());
        user.setEmail(tfEmail.getText());
        user.setResidence(tfResidence.getText());
        user.setPhoneNumber(tfPhone.getText());
        user.setPassword(pfPassword.getText());
        return user;
    }
}
