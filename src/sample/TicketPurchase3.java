package sample;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.ArrayList;
public class TicketPurchase3 {
    public static void createSceneTicketDetails(Scene sceneStart, Scene sceneTripOptions, Scene sceneTicketDetails, GridPane gridTicketDetails,
                                                ArrayList<Train> trains, ArrayList<Trip> tripOptions, ComboBox cbSelectOption, LocalDate localDatePicked, TableView<Trip> table, Stage window){
        gridTicketDetails.setAlignment(Pos.CENTER_LEFT);
        gridTicketDetails.setHgap(10);
        gridTicketDetails.setVgap(12);
        gridTicketDetails.setPadding(new Insets(30));
        gridTicketDetails.setStyle("-fx-background-color:lightblue");

        Label labelNumberOfTickets = new Label("Select number of tickets");
        TextField tfNumberOfTickets = new TextField();

        Label labelClass = new Label("Select the travel class");
        ComboBox cbClass = new ComboBox();
        cbClass.getItems().addAll("first class", "second class", "first class sleeping wagon", "second class sleeping wagon");

        Label labelStatus = new Label("Select your status");
        ComboBox cbStatus = new ComboBox();
        cbStatus.getItems().addAll("adult", "child(less than 6 years old)", "pupil", "student", "retired person");

        Button btnGoBackTripOptions = new Button("Go to previous page");
        btnGoBackTripOptions.setStyle("-fx-font-size: 12pt;");
        btnGoBackTripOptions.setOnAction(e -> window.setScene(sceneTripOptions));

        Button btnGoToHomePage = new Button("Go to home page");
        btnGoToHomePage.setStyle("-fx-font-size: 12pt;");
        btnGoToHomePage.setOnAction(e -> window.setScene(sceneStart));

        Button btnSubmit = new Button("Submit");
        btnSubmit.setStyle("-fx-font-size: 12pt;");

        HBox hbButtons = new HBox();
        hbButtons.setSpacing(10.0);
        hbButtons.getChildren().addAll(btnGoBackTripOptions, btnGoToHomePage);

        gridTicketDetails.add(labelNumberOfTickets, 0, 0);
        gridTicketDetails.add(tfNumberOfTickets, 0, 1);
        gridTicketDetails.add(labelClass, 0, 2);
        gridTicketDetails.add(cbClass, 0, 3);
        gridTicketDetails.add(labelStatus, 0, 4);
        gridTicketDetails.add(cbStatus, 0, 5);
        gridTicketDetails.add(hbButtons, 0, 6, 6, 1);
        gridTicketDetails.add(btnSubmit, 1, 0);

        GridPane gridTicketInfoandPrice = new GridPane();
        Scene sceneTicketInfoandPrice = new Scene(gridTicketInfoandPrice, 750, 600);
        btnSubmit.setOnAction(e -> {
            if (tfNumberOfTickets.getText().isEmpty()) {
                AlertBox.showAlert(Alert.AlertType.ERROR, gridTicketDetails.getScene().getWindow(), "Form Error!", "Please enter number of tickets");
                return;
            }
            if (cbClass.getSelectionModel().isEmpty()) {
                AlertBox.showAlert(Alert.AlertType.ERROR, gridTicketDetails.getScene().getWindow(), "Form Error!", "Please select a class");
                return;
            }
            if (cbStatus.getSelectionModel().isEmpty()) {
                AlertBox.showAlert(Alert.AlertType.ERROR, gridTicketDetails.getScene().getWindow(), "Form Error!", "Please select a status");
                return;
            }
            if (!SeatManager.checkNrOfAvailableSeats(cbClass, trains, cbSelectOption, tfNumberOfTickets, gridTicketDetails)){
                return;
            }
            TicketInfo.createSceneTicketInfoandPrice(sceneStart,sceneTicketDetails,tfNumberOfTickets, cbClass, cbStatus, tripOptions,
                    cbSelectOption, gridTicketInfoandPrice, trains, localDatePicked, sceneTicketInfoandPrice,table, window);
            window.setScene(sceneTicketInfoandPrice);
        });
    }
}
