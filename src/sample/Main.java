package sample;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main extends Application {
    Stage window;
    Button buttonLogIn, buttonSignUp, btnGoBackLogIn, btnGoBackSignUp;
    Label labelWelcome;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Train Booker");

        window = primaryStage;

        // FIRST SCENE

        BorderPane layoutStart = new BorderPane();
        layoutStart.setPadding(new Insets(20, 0, 20, 20));

        buttonSignUp = new Button("Sign Up");
        buttonSignUp.setMaxWidth(Double.MAX_VALUE);
        buttonLogIn = new Button("Log In");
        buttonLogIn.setMaxWidth(Double.MAX_VALUE);

        labelWelcome = new Label("Welcome to Train Booker!");
        final double MAX_FONT_SIZE = 27.0;
        labelWelcome.setFont(new Font(MAX_FONT_SIZE));

        VBox layoutStartButtons = new VBox();
        layoutStartButtons.setSpacing(10);
        layoutStartButtons.setPadding(new Insets(0, 20, 10, 20));

        layoutStartButtons.getChildren().addAll(buttonLogIn,buttonSignUp);
        layoutStart.setBottom(layoutStartButtons);
        layoutStart.setCenter(labelWelcome);
        Scene sceneStart = new Scene(layoutStart, 400, 400);

        //SCENE LOG IN

        GridPane gridLogIn = new GridPane();
        gridLogIn.setAlignment(Pos.CENTER);
        gridLogIn.setHgap(10);
        gridLogIn.setVgap(12);

        HBox hbButtons = new HBox();
        hbButtons.setSpacing(10.0);

        Button btnSubmit = new Button("Submit");
        btnGoBackLogIn = new Button ("Go back");
        btnGoBackLogIn.setStyle("-fx-font-size: 12pt;");
        btnSubmit.setStyle("-fx-font-size: 12pt;");

        Label labelUsername = new Label("Username:");
        TextField tfName = new TextField();
        Label labelPassword = new Label("Password:");
        PasswordField pfPwd = new PasswordField();

        hbButtons.getChildren().addAll(btnSubmit, btnGoBackLogIn);
        btnGoBackLogIn.setOnAction(e-> window.setScene(sceneStart));
        gridLogIn.add(labelUsername, 0, 0);
        gridLogIn.add(tfName, 1, 0);
        gridLogIn.add(labelPassword, 0, 1);
        gridLogIn.add(pfPwd, 1, 1);
        gridLogIn.add(hbButtons, 0, 2, 2, 1);

        Scene sceneLogIn = new Scene (gridLogIn, 400, 400);
        buttonLogIn.setOnAction(e->window.setScene(sceneLogIn));


        //SCENE SIGN UP

        GridPane gridSignUp = new GridPane();
        gridSignUp.setAlignment(Pos.CENTER);
        gridSignUp.setHgap(10);
        gridSignUp.setVgap(12);

        HBox hbButtonsSignUp = new HBox();
        hbButtonsSignUp.setSpacing(10.0);

        Button btnSubmitSignUp = new Button("Submit");
        btnGoBackSignUp = new Button ("Go back");
        btnGoBackSignUp.setStyle("-fx-font-size: 12pt;");
        btnSubmitSignUp.setStyle("-fx-font-size: 12pt;");


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

        hbButtonsSignUp.getChildren().addAll(btnSubmitSignUp, btnGoBackSignUp);
        btnGoBackSignUp.setOnAction(e-> window.setScene(sceneStart));

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

        Scene sceneSignUp = new Scene (gridSignUp, 500, 600);
        buttonSignUp.setOnAction(e->window.setScene(sceneSignUp));


        //admin
        User admin = new User();
        admin.setFirstName("John");
        admin.setLastName("Doe");
        admin.setEmail("admin@yahoo.com");
        admin.setResidence("Cluj-Napoca");
        admin.setPhoneNumber("0745689612");
        admin.setPassword("admin");

        //writeNewUser(admin);

        //parse data from user sign up

        Label lblError = new Label("Invalid phone number!");

        User user = new User();
        btnSubmitSignUp.setOnAction(e ->{
            if(validPhoneNumber(tfPhoneSignUp, tfPhoneSignUp.getText(), lblError, gridSignUp)){
                createUser(user, tfLastNameSignUp, tfFirstNameSignUp,  tfEmailSignUp,
                        tfPhoneSignUp, tfResidenceSignUp, pfPwdSignUp);
            }
        });

        window.setScene(sceneStart);
        window.show();
    }


    public User getUserInfo(User user, TextField tfLastNameSignUp, TextField tfFirstNameSignUp, TextField tfEmailSignUp,
                            TextField tfPhoneSignUp, TextField tfResidenceSignUp, PasswordField pfPwdSignUp){
        user.setFirstName(tfFirstNameSignUp.getText());
        user.setLastName(tfLastNameSignUp.getText());
        user.setEmail(tfEmailSignUp.getText());
        user.setResidence(tfResidenceSignUp.getText());
        user.setPhoneNumber(tfPhoneSignUp.getText());
        user.setPassword(pfPwdSignUp.getText());
        return user;
    }

    public void writeNewUser(User user) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt", true))){
            bw.write(user.getEmail());
            bw.newLine();
            bw.write(user.getPassword());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createUser(User user, TextField tfLastNameSignUp, TextField tfFirstNameSignUp,
                           TextField tfEmailSignUp, TextField tfPhoneSignUp, TextField tfResidenceSignUp,
                           PasswordField pfPwdSignUp){
        user = getUserInfo(user, tfLastNameSignUp, tfFirstNameSignUp, tfEmailSignUp,
                tfPhoneSignUp, tfResidenceSignUp, pfPwdSignUp);
        writeNewUser(user);
    }

    private boolean validPhoneNumber(TextField inputPhone, String phoneNumber,
                                     Label lblError, GridPane gridSignUp){
        try{
            Integer.parseInt(inputPhone.getText());
            return true;
        }catch(NumberFormatException e){
            gridSignUp.add(lblError, 2, 3);
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
