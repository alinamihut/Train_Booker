package sample;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class FinalScene {
    public static void createFinalScene(Scene sceneStart, GridPane layoutFinal, Stage window){
        layoutFinal.setPadding(new Insets(20, 0, 20, 20));
        Label labelTitle = new Label("Thank you for your purchase! ");
        labelTitle.setStyle("-fx-font-size: 20pt;");
        Button btnGoToHomePage = new Button("Go to home page");
        btnGoToHomePage.setStyle("-fx-font-size: 12pt;");
        btnGoToHomePage.setMaxWidth(Double.MAX_VALUE);
        layoutFinal.setAlignment(Pos.CENTER);
        layoutFinal.add(labelTitle, 0,0);
        layoutFinal.add(btnGoToHomePage, 0,1);
        btnGoToHomePage.setOnAction(e->window.setScene(sceneStart));

    }
}
