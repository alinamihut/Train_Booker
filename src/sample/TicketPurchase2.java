package sample;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
public class TicketPurchase2 {
    public static void createSceneTripOptions(Scene sceneStart, Scene buyTicket, ArrayList<Trip> tripOptions,
                                       Scene sceneTripOptions, GridPane gridTripOptions, ArrayList<Train> trains, LocalDate localDatePicked, Stage window){
        TableView<Trip> table = new TableView<Trip>();

        gridTripOptions.setAlignment(Pos.CENTER_LEFT);
        gridTripOptions.setHgap(10);
        gridTripOptions.setVgap(12);
        gridTripOptions.setPadding(new Insets(30));

        Label label = new Label("Trip options from " + tripOptions.get(0).getDepartureStation() + " to " +
                tripOptions.get(0).getArrivalStation());
        label.setStyle("-fx-font-size: 11pt;");

        TableColumn colTrainNumber = new TableColumn("Train");
        colTrainNumber.setMinWidth(100);
        colTrainNumber.setCellValueFactory(new PropertyValueFactory<Trip, String>("trainNumber"));

        TableColumn colDepartureTime = new TableColumn("Departure time");
        colDepartureTime.setMinWidth(100);
        colDepartureTime.setCellValueFactory(new PropertyValueFactory<Trip, String>("departureTime"));

        TableColumn colArrivalTime = new TableColumn("Arrival time");
        colArrivalTime.setMinWidth(100);
        colArrivalTime.setCellValueFactory(new PropertyValueFactory<Trip, String>("arrivalTime"));

        TableColumn colTripLength = new TableColumn("Trip length");
        colTripLength.setMinWidth(100);
        colTripLength.setCellValueFactory(new PropertyValueFactory<Trip, String>("tripLength"));

        table.getColumns().addAll(colTrainNumber, colDepartureTime, colArrivalTime, colTripLength);
        for(Trip trip: tripOptions){
            table.getItems().add(trip);
        }

        VBox vBoxTable = new VBox();
        vBoxTable.getChildren().clear();
        vBoxTable.setSpacing(10);
        vBoxTable.setPadding(new Insets(10, 20, 10, 10));
        vBoxTable.getChildren().addAll(label, table);

        gridTripOptions.add(vBoxTable, 0, 0);

        Button btnGoBackBuyTicket = new Button("Go to previous page");
        btnGoBackBuyTicket.setStyle("-fx-font-size: 12pt;");
        btnGoBackBuyTicket.setOnAction(e -> window.setScene(buyTicket));

        Button btnGoToHomePage = new Button("Go to home page");
        btnGoToHomePage.setStyle("-fx-font-size: 12pt;");
        btnGoToHomePage.setOnAction(e -> window.setScene(sceneStart));

        HBox hbButtons = new HBox();
        hbButtons.setSpacing(10.0);
        hbButtons.getChildren().addAll(btnGoBackBuyTicket, btnGoToHomePage);
        gridTripOptions.add(hbButtons, 0, 2, 2, 1);

        Label labelSelectOption = new Label("Select your option");
        labelSelectOption.setStyle("-fx-font-size: 11pt;");

        ComboBox cbSelectOption = new ComboBox();
        for(Trip trip: tripOptions){
            cbSelectOption.getItems().add(trip.getTrainNumber());
        }

        Button btnSubmit = new Button("Submit");
        btnSubmit.setStyle("-fx-font-size: 12pt;");

        VBox vBoxSelectOption = new VBox();
        vBoxSelectOption.setSpacing(10);
        vBoxSelectOption.setPadding(new Insets(10, 20, 10, 10));
        vBoxSelectOption.getChildren().addAll(labelSelectOption, cbSelectOption, btnSubmit);
        gridTripOptions.add(vBoxSelectOption, 1, 0);

        GridPane gridTicketDetails = new GridPane();
        Scene sceneTicketDetails = new Scene(gridTicketDetails, 680, 400);
        btnSubmit.setOnAction(e -> {
            TicketPurchase3.createSceneTicketDetails(sceneStart, sceneTripOptions, sceneTicketDetails, gridTicketDetails, trains,tripOptions,
                    cbSelectOption, localDatePicked,table,window);
            window.setScene(sceneTicketDetails);
        });
    }

}
