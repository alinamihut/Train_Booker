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

    Button buttonLogIn, buttonSignUp;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Train Booker");
        //primaryStage.setScene(new Scene(root, 300, 275));
        //primaryStage.show();

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 0, 20, 20));
        buttonSignUp = new Button("Sign Up");
        buttonLogIn = new Button("Log In");

        buttonSignUp.setMaxWidth(Double.MAX_VALUE);
        buttonLogIn.setMaxWidth(Double.MAX_VALUE);

        VBox vbButtons = new VBox();
        vbButtons.setSpacing(10);
        vbButtons.setPadding(new Insets(0, 20, 10, 20));
        vbButtons.getChildren().addAll(buttonSignUp, buttonLogIn);

        borderPane.setBottom(vbButtons);
        Scene scene = new Scene(borderPane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException {
        launch(args);
        //ArrayList<String> stations = new ArrayList<>();
    }
}
