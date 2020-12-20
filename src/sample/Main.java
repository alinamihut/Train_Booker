package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
        Label label1= new Label(" Welcome to Train Booker!");
        VBox vbButtons = new VBox();
        vbButtons.setSpacing(10);
        vbButtons.setPadding(new Insets(0, 20, 10, 20));
        vbButtons.getChildren().addAll(label1, buttonSignUp, buttonLogIn);

        borderPane.setBottom(vbButtons);
        Scene scene = new Scene(borderPane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException {
        launch(args);
        //ArrayList<String> stations = new ArrayList<>();
        //Train train = new Train(stations);
    }
}
/*package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    Button button1;
    Button button2;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("User log in");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        button1 =new Button();
        button1.setText("Log in");
        button2 =new Button();
        button2.setText("Sign up");

        Label label1= new Label(" Welcome to Train Booker!");
        VBox layout=new VBox();
        layout.getChildren().addAll(label1, button1, button2);
        button1.setTranslateX(100);
        button1.setTranslateY(150);
        button2.setTranslateX(150);
        button2.setTranslateY(120);
        Scene scene1=new Scene (layout, 300, 300);
        primaryStage.setScene(scene1);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
*/