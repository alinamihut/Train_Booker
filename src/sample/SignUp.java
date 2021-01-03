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

        Button btnSubmitSignUp = new Button("Submit");
        btnSubmitSignUp.setStyle("-fx-font-size: 12pt;");
        Button btnGoBackSignUp = new Button("Go back");
        btnGoBackSignUp.setStyle("-fx-font-size: 12pt;");

        //FIRST NAME, LAST NAME, EMAIL, PHONE NUMBER, RESIDENCE PLACE, PASSWORD
        Label labelLastNameSignUp = new Label("Last Name:");
        TextField tfLastNameSignUp = new TextField();
        Label labelFirstNameSignUp = new Label("First Name:");
        TextField tfFirstNameSignUp = new TextField();
        Label labelEmailSignUp = new Label("Email:");
        TextField tfEmailSignUp = new TextField();
        Label labelPhoneSignUp = new Label("Phone number:  (+)40");
        TextField tfPhoneSignUp = new TextField();
        Label labelResidenceSignUp = new Label("Residence place:");
        TextField tfResidenceSignUp = new TextField();
        Label labelPasswordSignUp = new Label("Password:");
        PasswordField pfPwdSignUp = new PasswordField();
        Label labelConfirmPasswordSignUp = new Label("Confirm password:");
        PasswordField pfConfirmPwdSignUp = new PasswordField();

        HBox hbButtonsSignUp = new HBox();
        hbButtonsSignUp.setSpacing(10.0);
        hbButtonsSignUp.getChildren().addAll(btnSubmitSignUp, btnGoBackSignUp);
        btnGoBackSignUp.setOnAction(e -> {
            tfLastNameSignUp.clear();
            tfFirstNameSignUp.clear();
            tfEmailSignUp.clear();
            tfPhoneSignUp.clear();
            tfResidenceSignUp.clear();
            pfPwdSignUp.clear();
            pfConfirmPwdSignUp.clear();
            window.setScene(sceneStart);
        });

        gridSignUp.add(labelFirstNameSignUp, 0, 0);
        gridSignUp.add(tfFirstNameSignUp, 1, 0);
        gridSignUp.add(labelLastNameSignUp, 0, 1);
        gridSignUp.add(tfLastNameSignUp, 1, 1);
        gridSignUp.add(labelEmailSignUp, 0, 2);
        gridSignUp.add(tfEmailSignUp, 1, 2);
        gridSignUp.add(labelPhoneSignUp, 0, 3);
        gridSignUp.add(tfPhoneSignUp, 1, 3);
        gridSignUp.add(labelResidenceSignUp, 0, 4);
        gridSignUp.add(tfResidenceSignUp, 1, 4);
        gridSignUp.add(labelPasswordSignUp, 0, 5);
        gridSignUp.add(pfPwdSignUp, 1, 5);
        gridSignUp.add(labelConfirmPasswordSignUp, 0, 6);
        gridSignUp.add(pfConfirmPwdSignUp, 1, 6);
        gridSignUp.add(hbButtonsSignUp, 0, 7, 7, 1);

        btnSubmitSignUp.setOnAction(e -> {
            if (tfFirstNameSignUp.getText().isEmpty()) {
                AlertBox.showAlert(Alert.AlertType.ERROR, gridSignUp.getScene().getWindow(), "Form Error!", "Please enter your first name");
                return;
            }
            if (tfLastNameSignUp.getText().isEmpty()) {
                AlertBox.showAlert(Alert.AlertType.ERROR, gridSignUp.getScene().getWindow(), "Form Error!", "Please enter your last name");
                return;
            }
            if (tfEmailSignUp.getText().isEmpty()) {
                AlertBox.showAlert(Alert.AlertType.ERROR, gridSignUp.getScene().getWindow(), "Form Error!", "Please enter your email");
                return;
            }
            if (tfPhoneSignUp.getText().isEmpty()) {
                AlertBox.showAlert(Alert.AlertType.ERROR, gridSignUp.getScene().getWindow(), "Form Error!", "Please enter a phone number");
                return;
            }
            if (tfResidenceSignUp.getText().isEmpty()) {
                AlertBox.showAlert(Alert.AlertType.ERROR, gridSignUp.getScene().getWindow(), "Form Error!", "Please enter your residence place");
                return;
            }
            if (pfPwdSignUp.getText().isEmpty()) {
                AlertBox.showAlert(Alert.AlertType.ERROR, gridSignUp.getScene().getWindow(), "Form Error!", "Please enter a password");
                return;
            }
            if (pfConfirmPwdSignUp.getText().isEmpty()) {
                AlertBox.showAlert(Alert.AlertType.ERROR, gridSignUp.getScene().getWindow(), "Form Error!", "Please confirm password");
                return;
            }
            if (!tfEmailSignUp.getText().matches("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
                AlertBox.showAlert(Alert.AlertType.ERROR, gridSignUp.getScene().getWindow(), "Form Error!", "Please enter a valid email address");
                return;
            }
            if(DataValidation.checkEmailExistence(tfEmailSignUp)){
                AlertBox.showAlert(Alert.AlertType.ERROR, gridSignUp.getScene().getWindow(), "Form Error!", "There is already an account associated with this email address");
                return;
            }
            if (!tfPhoneSignUp.getText().matches("\\d{9}")) {
                AlertBox.showAlert(Alert.AlertType.ERROR, gridSignUp.getScene().getWindow(), "Form Error!", "Please enter a valid phone number");
                return;
            }
            if (!pfPwdSignUp.getText().equals(pfConfirmPwdSignUp.getText())) {
                AlertBox.showAlert(Alert.AlertType.ERROR, gridSignUp.getScene().getWindow(), "Form Error!", "The two passwords don't match");
                return;
            }
            if(pfPwdSignUp.getLength() < 6){
                AlertBox.showAlert(Alert.AlertType.ERROR, gridSignUp.getScene().getWindow(), "Form Error!", "Your password must have at least 6 characters");
                return;
            }

            FileManager.createUser(user, tfLastNameSignUp, tfFirstNameSignUp, tfEmailSignUp, tfPhoneSignUp, tfResidenceSignUp, pfPwdSignUp);
            AlertBox.showAlert(Alert.AlertType.CONFIRMATION, gridSignUp.getScene().getWindow(), "Registration Successful!", "Welcome " + tfFirstNameSignUp.getText());
            tfLastNameSignUp.clear();
            tfFirstNameSignUp.clear();
            tfEmailSignUp.clear();
            tfPhoneSignUp.clear();
            tfResidenceSignUp.clear();
            pfPwdSignUp.clear();
            pfConfirmPwdSignUp.clear();
            window.setScene(sceneLogIn);
        });
    }
    public static User getUserInfo(User user, TextField tfLastNameSignUp, TextField tfFirstNameSignUp, TextField tfEmailSignUp,
                                   TextField tfPhoneSignUp, TextField tfResidenceSignUp, PasswordField pfPwdSignUp) {
        user.setFirstName(tfFirstNameSignUp.getText());
        user.setLastName(tfLastNameSignUp.getText());
        user.setEmail(tfEmailSignUp.getText());
        user.setResidence(tfResidenceSignUp.getText());
        user.setPhoneNumber(tfPhoneSignUp.getText());
        user.setPassword(pfPwdSignUp.getText());
        return user;
    }
}
