package sample;
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
public class TicketInfo {
    public static void createSceneTicketInfoandPrice(Scene sceneStart, Scene sceneTicketDetails, TextField tfNumberOfTickets, ComboBox cbClass,
                                                     ComboBox cbStatus, ArrayList<Trip> tripOptions, ComboBox cbSelectOption, GridPane gridTicketInfoandPrice,
                                                     ArrayList<Train> trains, LocalDate localDatePicked, Scene sceneTicketInfoandPrice, TableView<Trip> table, Stage window){
        Trip selectedTrip =new Trip(tripOptions.get(0).getTrainNumber(),tripOptions.get(0).getDepartureTime(), tripOptions.get(0).getArrivalTime(),
                tripOptions.get(0).getTripLength(), tripOptions.get(0).getDepartureStation(),tripOptions.get(0).getArrivalStation(),
                tripOptions.get(0).getTripLengthInMinutes());
        for (Trip trip:tripOptions){
            if(trip.getTrainNumber().equals(cbSelectOption.getValue())){
                selectedTrip = new Trip(trip.getTrainNumber(),trip.getDepartureTime(), trip.getArrivalTime(), trip.getTripLength(), trip.getDepartureStation(),
                        trip.getArrivalStation(), trip.getTripLengthInMinutes());
            }
        }
        gridTicketInfoandPrice.setAlignment(Pos.CENTER_LEFT);
        gridTicketInfoandPrice.setHgap(10);
        gridTicketInfoandPrice.setVgap(12);
        gridTicketInfoandPrice.setPadding(new Insets(30));
        gridTicketInfoandPrice.setStyle("-fx-background-color:lightblue");

        Label labelTitle = new Label("Your trip details");
        labelTitle.setStyle("-fx-font-size: 18pt;");
        Label labelTrainNumber = new Label("Train number: " + selectedTrip.getTrainNumber());
        labelTrainNumber.setStyle("-fx-font-size: 12pt;");
        Label labelDepartureDate = new Label("Departure date: " + localDatePicked);
        labelDepartureDate.setStyle("-fx-font-size: 12pt;");
        Label labelDepartureStation = new Label("Departure station: " + selectedTrip.getDepartureStation());
        labelDepartureStation.setStyle("-fx-font-size: 12pt;");
        Label labelArrivalStation = new Label("Arrival station: " + selectedTrip.getArrivalStation());
        labelArrivalStation.setStyle("-fx-font-size: 12pt;");
        Label labelDepartureTime = new Label("Departure time: " + selectedTrip.getDepartureTime());
        labelDepartureTime.setStyle("-fx-font-size: 12pt;");
        Label labelArrivalTime = new Label("Arrival time: " + selectedTrip.getArrivalTime());
        labelArrivalTime.setStyle("-fx-font-size: 12pt;");
        Label labelTripLength = new Label("Trip length: " + selectedTrip.getTripLength());
        labelTripLength.setStyle("-fx-font-size: 12pt;");

        Integer nrOfTickets = Integer.parseInt(tfNumberOfTickets.getText());
        String selectedClass = (String) cbClass.getValue();
        String selectedStatus = (String) cbStatus.getValue();

        Label labelNrOfTickets=new Label("Number of tickets: " + nrOfTickets);
        labelNrOfTickets.setStyle("-fx-font-size: 12pt;");
        Label labelClass=new Label("Travel class: " + selectedClass);
        labelClass.setStyle("-fx-font-size: 12pt;");
        Label labelStatus=new Label("Status of the traveler(s): " + selectedStatus);
        labelStatus.setStyle("-fx-font-size: 12pt;");

        Double pricePerTicket = PriceCalculator.computePricePerTicket (selectedTrip,  selectedClass, selectedStatus);
        Double totalPrice = nrOfTickets * pricePerTicket;
        Label labelPricePerTicket = new Label("Price per ticket: " + pricePerTicket.toString());
        labelPricePerTicket.setStyle("-fx-font-size: 12pt;");
        Label labelTotalPrice = new Label("Total price: " + totalPrice.toString());
        labelTotalPrice.setStyle("-fx-font-size: 12pt;");

        gridTicketInfoandPrice.add(labelTitle, 0, 0 );
        gridTicketInfoandPrice.add(labelTrainNumber, 0, 1 );
        gridTicketInfoandPrice.add(labelDepartureDate, 0, 2 );
        gridTicketInfoandPrice.add(labelDepartureStation, 0, 3 );
        gridTicketInfoandPrice.add(labelArrivalStation, 0, 4 );
        gridTicketInfoandPrice.add(labelDepartureTime, 1,3 );
        gridTicketInfoandPrice.add(labelArrivalTime, 1,4 );
        gridTicketInfoandPrice.add(labelTripLength, 0, 5 );
        gridTicketInfoandPrice.add(labelNrOfTickets, 0, 6 );
        gridTicketInfoandPrice.add(labelClass, 0, 7 );
        gridTicketInfoandPrice.add(labelStatus, 0, 8 );
        gridTicketInfoandPrice.add(labelPricePerTicket, 0, 9 );
        gridTicketInfoandPrice.add(labelTotalPrice, 0, 10 );

        Button btnGoBackTripOptions = new Button("Go to previous page");
        btnGoBackTripOptions.setStyle("-fx-font-size: 12pt;");
        btnGoBackTripOptions.setOnAction(e -> window.setScene(sceneTicketDetails));

        GridPane gridPayment = new GridPane();
        Scene scenePayment = new Scene(gridPayment, 800, 500);
        Button btnConfirm = new Button("Continue with the payment");
        btnConfirm.setStyle("-fx-font-size: 12pt;");

        btnConfirm.setOnAction(e ->{
            Payment.createScenePayment(sceneStart,sceneTicketInfoandPrice, gridPayment,tfNumberOfTickets, cbClass, cbSelectOption,  trains, localDatePicked,table, window);
            window.setScene(scenePayment);
        });

        HBox hbButtons = new HBox();
        hbButtons.setSpacing(10.0);
        hbButtons.getChildren().addAll(btnGoBackTripOptions,btnConfirm);
        gridTicketInfoandPrice.add(hbButtons, 0,11);
    }
}
