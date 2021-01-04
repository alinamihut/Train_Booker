package sample;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.ArrayList;
public class TicketPurchase1 {
    public static void createSceneBuyTicket(Scene sceneStart, Scene buyTicket, GridPane gridBuyTicket,
                                            ArrayList<String> stations, Stage window) {
        gridBuyTicket.setAlignment(Pos.CENTER);
        gridBuyTicket.setHgap(10);
        gridBuyTicket.setVgap(12);
        gridBuyTicket.setStyle("-fx-background-color:lightblue");

        Label labelDepartureStation = new Label("Select your departure station");
        gridBuyTicket.add(labelDepartureStation, 0, 0);
        ComboBox cbDepartureStation = new ComboBox();
        cbDepartureStation.getItems().addAll(stations);
        gridBuyTicket.add(cbDepartureStation, 0, 1);

        Label labelArrivalStation = new Label("Select your arrival station");
        gridBuyTicket.add(labelArrivalStation, 0, 2);
        ComboBox cbArrivalStation = new ComboBox();
        cbArrivalStation.getItems().addAll(stations);
        gridBuyTicket.add(cbArrivalStation, 0, 3);

        Label labelPickDate = new Label("Pick a date for your trip");
        gridBuyTicket.add(labelPickDate, 0, 4);
        DatePicker datePicker = new DatePicker();
        gridBuyTicket.add(datePicker, 0, 5);

        Button btnSubmit = new Button("Submit");
        btnSubmit.setStyle("-fx-font-size: 11pt;");

        Button btnGoBackHome = new Button("Go to home page");
        btnGoBackHome.setStyle("-fx-font-size: 11pt;");
        btnGoBackHome.setOnAction(e -> {
            cbDepartureStation.getSelectionModel().clearSelection();
            cbArrivalStation.getSelectionModel().clearSelection();
            datePicker.setValue(null);
            window.setScene(sceneStart);
        });

        HBox hbButtonsSignUp = new HBox();
        hbButtonsSignUp.setSpacing(10.0);
        hbButtonsSignUp.getChildren().addAll(btnGoBackHome,btnSubmit);
        gridBuyTicket.add(hbButtonsSignUp, 0, 7, 7, 1);

        GridPane gridTripOptions = new GridPane();
        Scene sceneTripOptions = new Scene(gridTripOptions, 680, 400);

        btnSubmit.setOnAction(e -> {
            if (cbDepartureStation.getSelectionModel().isEmpty()) {
                AlertBox.showAlert(Alert.AlertType.ERROR, gridBuyTicket.getScene().getWindow(), "Form Error!", "Please select a departure station");
                return;
            }
            if (cbArrivalStation.getSelectionModel().isEmpty()) {
                AlertBox.showAlert(Alert.AlertType.ERROR, gridBuyTicket.getScene().getWindow(), "Form Error!", "Please select a destination");
                return;
            }
            if (datePicker.getValue()== null){
                AlertBox.showAlert(Alert.AlertType.ERROR, gridBuyTicket.getScene().getWindow(), "Form Error!", "Please pick a date for your trip");
                return;
            }
            if (datePicker.getValue().isBefore(LocalDate.now())) {
                AlertBox.showAlert(Alert.AlertType.ERROR, gridBuyTicket.getScene().getWindow(), "Form Error!", "Select a valid date");
                return;
            }
            LocalDate localDatePicked = datePicker.getValue();
            if(FileManager.chooseTxtFile(localDatePicked).equals("Error")){
                AlertBox.showAlert(Alert.AlertType.ERROR, gridBuyTicket.getScene().getWindow(), "Form Error!", "Please select a date in the following 7 days");
                return;
            }
            String chosenTxtFile = FileManager.chooseTxtFile(localDatePicked);
            ArrayList<Train> trains = new ArrayList<>();
            FileManager.createTrains(trains,chosenTxtFile);
            ArrayList<Trip> tripOptions = new ArrayList<>();
            if(!TripOptions.findTripOptions(cbDepartureStation, cbArrivalStation, trains, tripOptions)){
                AlertBox.showAlert(Alert.AlertType.ERROR, gridBuyTicket.getScene().getWindow(), "Form Error!", "There aren't any train options available");
                return;
            }
            TicketPurchase2.createSceneTripOptions(sceneStart, buyTicket, tripOptions, sceneTripOptions, gridTripOptions, trains, localDatePicked, window);
            cbDepartureStation.getSelectionModel().clearSelection();
            cbArrivalStation.getSelectionModel().clearSelection();
            datePicker.setValue(null);
            window.setScene(sceneTripOptions);
        });
    }
}
