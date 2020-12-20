package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.IOException;

public class Main extends Application {

    Stage window;
    Scene sceneFirst, sceneLogIn, sceneSignUp;
    Button buttonLogIn, buttonSignUp, buttonGoBackSignUp, buttonGoBackLogIn;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        window.setTitle("Train Booker");
        //primaryStage.setScene(new Scene(root, 300, 275));

        window = primaryStage;

        buttonSignUp = new Button("Sign Up");
        buttonSignUp.setMaxWidth(Double.MAX_VALUE);
        //buttonSignUp.setOnAction(e -> window.setScene(sceneSignUp));

        buttonLogIn = new Button("Log In");
        buttonLogIn.setMaxWidth(Double.MAX_VALUE);
        buttonLogIn.setOnAction(e -> window.setScene(sceneLogIn));

        BorderPane borderPaneFirst = new BorderPane();
        borderPaneFirst.setPadding(new Insets(20, 0, 20, 20));

        VBox vbButtons = new VBox();
        vbButtons.setSpacing(10);
        vbButtons.setPadding(new Insets(0, 20, 10, 20));
        vbButtons.getChildren().addAll(buttonSignUp, buttonLogIn);
        borderPaneFirst.setBottom(vbButtons);
        sceneFirst = new Scene(borderPaneFirst, 400, 400);


        //buttonGoBackSignUp = new Button("Go Back");
        //buttonGoBackSignUp.setOnAction(e -> window.setScene(sceneFirst));

        //BorderPane borderPaneSignUp = new BorderPane();
        //borderPaneSignUp.setPadding(new Insets(20, 0, 20, 20));
        //borderPaneSignUp.getChildren().add(buttonGoBackSignUp);
        //sceneSignUp = new Scene (borderPaneSignUp, 400, 400);


        //buttonGoBackLogIn = new Button("Go Back");
        //buttonGoBackLogIn.setOnAction(e -> window.setScene(sceneFirst));

        BorderPane borderPaneLogIn = new BorderPane();
        //borderPaneLogIn.setPadding(new Insets(20, 0, 20, 20));
        //borderPaneLogIn.getChildren().add(buttonGoBackLogIn);
        sceneLogIn = new Scene (borderPaneLogIn, 400, 400);

        window.setScene(sceneFirst);
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException {
        launch(args);
        //ArrayList<String> stations = new ArrayList<>();
    }
}
