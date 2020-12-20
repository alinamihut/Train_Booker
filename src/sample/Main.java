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
    Button buttonLogIn, buttonSignUp, buttonGoBack,buttonGoBack2;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Train Booker");

        window=primaryStage;
        BorderPane layout1 = new BorderPane();
        layout1.setPadding(new Insets(20, 0, 20, 20));
        buttonSignUp = new Button("Sign Up");
        buttonLogIn = new Button("Log In");
        buttonSignUp.setMaxWidth(Double.MAX_VALUE);
        buttonLogIn.setMaxWidth(Double.MAX_VALUE);

        VBox layout1Buttons = new VBox();
        layout1Buttons.setSpacing(10);
        layout1Buttons.setPadding(new Insets(0, 20, 10, 20));

      //

        layout1Buttons.getChildren().addAll(buttonLogIn,buttonSignUp);
        layout1.setBottom(layout1Buttons);
        Scene first = new Scene(layout1, 400, 400);

        VBox layout2 = new VBox();
        layout2.setSpacing(10);
        layout2.setPadding(new Insets(0, 20, 10, 20));

        Scene sceneLogIn=new Scene (layout2, 400, 400);
        buttonLogIn.setOnAction(e->window.setScene(sceneLogIn));
        buttonGoBack= new Button ("Go back");
        layout2.getChildren().addAll(buttonGoBack);
        buttonGoBack.setOnAction(e-> window.setScene(first));

        VBox layout3 = new VBox();
        layout3.setSpacing(10);
        layout3.setPadding(new Insets(0, 20, 10, 20));

        Scene sceneSignUp=new Scene (layout3, 400, 400);
        buttonSignUp.setOnAction(e->window.setScene(sceneSignUp));
        buttonGoBack2= new Button ("Go back");
        layout3.getChildren().addAll(buttonGoBack2);
        buttonGoBack2.setOnAction(e-> window.setScene(first));

        window.setScene(first);
        window.show();

        //buttonSignUp.setOnAction(e->window.setScene(sceneSignUp));
    }

    public static void main(String[] args) throws IOException {
        launch(args);
        //ArrayList<String> stations = new ArrayList<>();
    }
}
