package sample;

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
