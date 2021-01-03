package sample;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;


public class Login extends Stage {
    public static void createSceneLogIn(Scene sceneStart, GridPane gridLogIn, Stage window) throws IOException {
        gridLogIn.setAlignment(Pos.CENTER);
        gridLogIn.setHgap(10);
        gridLogIn.setVgap(12);

        Button btnSubmitLogIn = new Button("Submit");
        btnSubmitLogIn.setStyle("-fx-font-size: 12pt;");

        Button btnGoBackLogIn = new Button("Go back");
        btnGoBackLogIn.setStyle("-fx-font-size: 12pt;");
        btnGoBackLogIn.setOnAction(e -> window.setScene(sceneStart));

        Label labelUsername = new Label("Username:");
        TextField tfName = new TextField();
        Label labelPassword = new Label("Password:");
        PasswordField pfPassword = new PasswordField();

        HBox hbButtons = new HBox();
        hbButtons.setSpacing(10.0);
        hbButtons.getChildren().addAll(btnGoBackLogIn, btnSubmitLogIn);

        gridLogIn.add(labelUsername, 0, 0);
        gridLogIn.add(tfName, 1, 0);
        gridLogIn.add(labelPassword, 0, 1);
        gridLogIn.add(pfPassword, 1, 1);
        gridLogIn.add(hbButtons, 0, 2, 2, 1);
        GridPane gridBuyTicket = new GridPane();
        Scene sceneBuyTicket = new Scene(gridBuyTicket, 400, 400);
        ArrayList<String> stations = new ArrayList<>();
        FileManager.parseStationList(stations);
        TicketPurchase1.createSceneBuyTicket(sceneStart, sceneBuyTicket, gridBuyTicket, stations,window);

        btnSubmitLogIn.setOnAction(e -> {
            if (tfName.getText().isEmpty()) {
                AlertBox.showAlert(Alert.AlertType.ERROR, gridLogIn.getScene().getWindow(), "Form Error!", "Please enter your email");
                return;
            }
            if (pfPassword.getText().isEmpty()) {
                AlertBox.showAlert(Alert.AlertType.ERROR, gridLogIn.getScene().getWindow(), "Form Error!", "Please enter your password");
                return;
            }
            if (!DataValidation.validateLogIn(tfName, pfPassword)) {
                AlertBox.showAlert(Alert.AlertType.ERROR, gridLogIn.getScene().getWindow(), "Form Error!", "Username or password not correct");
                return;
            }
            tfName.clear();
            pfPassword.clear();
            window.setScene(sceneBuyTicket);
        });
    }
}