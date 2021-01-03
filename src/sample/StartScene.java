package sample;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;
public class StartScene extends Stage {

    public static void create(Stage window) throws IOException {
        Button buttonSignUp = new Button("Sign Up");
        Button buttonLogIn = new Button("Log In");
        BorderPane layoutStart = new BorderPane();
        layoutStart.setPadding(new Insets(20, 0, 20, 20));

        buttonSignUp.setMaxWidth(Double.MAX_VALUE);

        buttonLogIn.setMaxWidth(Double.MAX_VALUE);

        Label labelWelcome = new Label("Welcome to Train Booker!");
        final double MAX_FONT_SIZE = 27.0;
        labelWelcome.setFont(new Font(MAX_FONT_SIZE));

        VBox layoutStartButtons = new VBox();
        layoutStartButtons.setSpacing(10);
        layoutStartButtons.setPadding(new Insets(0, 20, 10, 20));
        layoutStartButtons.getChildren().addAll(buttonLogIn, buttonSignUp);

        layoutStart.setBottom(layoutStartButtons);
        layoutStart.setCenter(labelWelcome);
        Scene sceneStart = new Scene(layoutStart, 400, 400);

        GridPane gridLogIn = new GridPane();
        Scene sceneLogIn = new Scene(gridLogIn, 400, 400);
        Login.createSceneLogIn(sceneStart, gridLogIn, window);
        buttonLogIn.setOnAction(e -> window.setScene(sceneLogIn));

        User user = new User();
        GridPane gridSignUp = new GridPane();
        Scene sceneSignUp = new Scene(gridSignUp, 700, 400);
        SignUp.createSceneSignUp(sceneStart, sceneLogIn, user, gridSignUp,window);
        buttonSignUp.setOnAction(e -> window.setScene(sceneSignUp));

        window.setScene(sceneStart);
        window.show();
    }
}
