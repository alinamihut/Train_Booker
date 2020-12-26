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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;


public class Main extends Application {
    Stage window;
    Button buttonLogIn, buttonSignUp;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Train Booker");

        window = primaryStage;

        ArrayList<Train> trains = new ArrayList<>();
        createTrains(trains);
        ArrayList<String> stations = new ArrayList<>();
        parseStationList(stations);

        // FIRST SCENE

        BorderPane layoutStart = new BorderPane();
        layoutStart.setPadding(new Insets(20, 0, 20, 20));

        buttonSignUp = new Button("Sign Up");
        buttonSignUp.setMaxWidth(Double.MAX_VALUE);
        buttonLogIn = new Button("Log In");
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

        //SCENE LOG IN

        GridPane gridLogIn = new GridPane();
        Scene sceneLogIn = new Scene(gridLogIn, 400, 400);
        createSceneLogIn(sceneStart, stations, gridLogIn, trains);
        buttonLogIn.setOnAction(e -> window.setScene(sceneLogIn));

        //SCENE SIGN UP

        User user = new User();
        GridPane gridSignUp = new GridPane();
        Scene sceneSignUp = new Scene(gridSignUp, 700, 400);
        createSceneSignUp(sceneStart, sceneLogIn, user, gridSignUp);
        buttonSignUp.setOnAction(e -> window.setScene(sceneSignUp));

        //admin
        User admin = new User();
        admin.setFirstName("John");
        admin.setLastName("Doe");
        admin.setEmail("admin@yahoo.com");
        admin.setResidence("Cluj-Napoca");
        admin.setPhoneNumber("0745689612");
        admin.setPassword("admin");
        //writeNewUser(admin);

        window.setScene(sceneStart);
        window.show();
    }

    public void createSceneLogIn(Scene sceneStart, ArrayList<String> stations, GridPane gridLogIn, ArrayList<Train> trains) throws IOException {
        gridLogIn.setAlignment(Pos.CENTER);
        gridLogIn.setHgap(10);
        gridLogIn.setVgap(12);

        Button btnSubmitLogIn = new Button("Submit");
        btnSubmitLogIn.setStyle("-fx-font-size: 12pt;");

        Button btnGoBackLogIn = new Button("Go back");
        btnGoBackLogIn.setStyle("-fx-font-size: 12pt;");
        btnGoBackLogIn.setOnAction(e -> window.setScene(sceneStart));

        Label labelUsername = new Label("Username:");
        TextField tfName = new TextField();
        Label labelPassword = new Label("Password:");
        PasswordField pfPassword = new PasswordField();

        HBox hbButtons = new HBox();
        hbButtons.setSpacing(10.0);
        hbButtons.getChildren().addAll(btnSubmitLogIn, btnGoBackLogIn);

        gridLogIn.add(labelUsername, 0, 0);
        gridLogIn.add(tfName, 1, 0);
        gridLogIn.add(labelPassword, 0, 1);
        gridLogIn.add(pfPassword, 1, 1);
        gridLogIn.add(hbButtons, 0, 2, 2, 1);

        // SCENE BUY TICKET

        GridPane gridBuyTicket = new GridPane();
        Scene sceneBuyTicket = new Scene(gridBuyTicket, 400, 400);
        createSceneBuyTicket(sceneStart, sceneBuyTicket, gridBuyTicket, stations, trains);

        btnSubmitLogIn.setOnAction(e -> {
            if (tfName.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridLogIn.getScene().getWindow(), "Form Error!", "Please enter your email");
                return;
            }
            if (pfPassword.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridLogIn.getScene().getWindow(), "Form Error!", "Please enter your password");
                return;
            }
            if (!validateLogIn(tfName, pfPassword)) {
                showAlert(Alert.AlertType.ERROR, gridLogIn.getScene().getWindow(), "Form Error!", "Username or password not correct");
                return;
            }
            tfName.clear();
            pfPassword.clear();
            window.setScene(sceneBuyTicket);
        });
    }

    public void createSceneBuyTicket(Scene sceneStart, Scene buyTicket, GridPane gridBuyTicket,
                                     ArrayList<String> stations, ArrayList<Train> trains) throws IOException {
        gridBuyTicket.setAlignment(Pos.CENTER);
        gridBuyTicket.setHgap(10);
        gridBuyTicket.setVgap(12);

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
        hbButtonsSignUp.getChildren().addAll(btnSubmit, btnGoBackHome);
        gridBuyTicket.add(hbButtonsSignUp, 0, 7, 7, 1);

        ArrayList<Trip> tripOptions = new ArrayList<>();

        GridPane gridTripOptions = new GridPane();
        Scene sceneTripOptions = new Scene(gridTripOptions, 680, 400);

        btnSubmit.setOnAction(e -> {
            if (cbDepartureStation.getSelectionModel().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridBuyTicket.getScene().getWindow(), "Form Error!", "Please select a departure station");
                return;
            }
            if (cbArrivalStation.getSelectionModel().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridBuyTicket.getScene().getWindow(), "Form Error!", "Please select a destination");
                return;
            }
            if (datePicker.getValue()== null){
                showAlert(Alert.AlertType.ERROR, gridBuyTicket.getScene().getWindow(), "Form Error!", "Please pick a date for your trip");
                return;
            }

            if (datePicker.getValue().isBefore(LocalDate.now())) {
                showAlert(Alert.AlertType.ERROR, gridBuyTicket.getScene().getWindow(), "Form Error!", "Select a valid date");
                return;
            }
            if(!findTripOptions(cbDepartureStation, cbArrivalStation, trains, tripOptions)){
                showAlert(Alert.AlertType.ERROR, gridBuyTicket.getScene().getWindow(), "Form Error!", "There aren't any train options available");
                return;
            }
            createSceneTripOptions(sceneStart, buyTicket, tripOptions, sceneTripOptions, gridTripOptions, trains);
            cbDepartureStation.getSelectionModel().clearSelection();
            cbArrivalStation.getSelectionModel().clearSelection();
            datePicker.setValue(null);
            window.setScene(sceneTripOptions);
        });
    }

    public void createSceneTripOptions(Scene sceneStart, Scene buyTicket, ArrayList<Trip> tripOptions,
                                       Scene sceneTripOptions, GridPane gridTripOptions,  ArrayList<Train> trains){
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
            createSceneTicketDetails(sceneStart, sceneTripOptions, sceneTicketDetails, gridTicketDetails, trains,tripOptions, cbSelectOption);
            window.setScene(sceneTicketDetails);
        });
    }

    public void createSceneTicketDetails(Scene sceneStart, Scene sceneTripOptions, Scene sceneTicketDetails, GridPane gridTicketDetails,
                                         ArrayList<Train> trains,ArrayList<Trip> tripOptions, ComboBox cbSelectOption){
        gridTicketDetails.setAlignment(Pos.CENTER_LEFT);
        gridTicketDetails.setHgap(10);
        gridTicketDetails.setVgap(12);
        gridTicketDetails.setPadding(new Insets(30));

        Label labelNumberOfTickets = new Label("Select number of tickets");
        TextField tfNumberOfTickets = new TextField();

        Label labelClass = new Label("Select the class");
        ComboBox cbClass = new ComboBox();
        cbClass.getItems().addAll("first class", "second class", "first class sleeping wagon", "second class sleeping wagon");

        Label labelStatus = new Label("Select your status");
        ComboBox cbStatus = new ComboBox();
        cbStatus.getItems().addAll("regular adult", "child(less than 6 years old)", "pupil", "student", "retired person");

        Button btnGoBackTripOptions = new Button("Go to previous page");
        btnGoBackTripOptions.setStyle("-fx-font-size: 12pt;");
        btnGoBackTripOptions.setOnAction(e -> window.setScene(sceneTripOptions));

        Button btnGoToHomePage = new Button("Go to home page");
        btnGoToHomePage.setStyle("-fx-font-size: 12pt;");
        btnGoToHomePage.setOnAction(e -> window.setScene(sceneStart));

        HBox hbButtons = new HBox();
        hbButtons.setSpacing(10.0);
        hbButtons.getChildren().addAll(btnGoBackTripOptions, btnGoToHomePage);

        Button btnSubmit = new Button("Submit");
        btnSubmit.setStyle("-fx-font-size: 12pt;");

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
                showAlert(Alert.AlertType.ERROR, gridTicketDetails.getScene().getWindow(), "Form Error!", "Please enter number of tickets");
                return;
            }
            if (cbClass.getSelectionModel().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridTicketDetails.getScene().getWindow(), "Form Error!", "Please select a class");
                return;
            }
            if (cbStatus.getSelectionModel().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridTicketDetails.getScene().getWindow(), "Form Error!", "Please select a status");
                return;
            }
            createSceneTicketInfoandPrice(sceneStart,sceneTicketDetails,tfNumberOfTickets, cbClass, cbStatus, tripOptions, cbSelectOption, gridTicketInfoandPrice, trains);
            window.setScene(sceneTicketInfoandPrice);
            manageSeats(cbClass, trains, cbSelectOption,tfNumberOfTickets, gridTicketDetails);
        });
    }

    public void createSceneTicketInfoandPrice (Scene sceneStart, Scene sceneTicketDetails, TextField tfNumberOfTickets, ComboBox cbClass,
                                               ComboBox cbStatus, ArrayList<Trip> tripOptions, ComboBox cbSelectOption, GridPane gridTicketInfoandPrice, ArrayList<Train> trains){
        Trip selectedTrip =new Trip(tripOptions.get(0).getTrainNumber(),tripOptions.get(0).getDepartureTime(), tripOptions.get(0).getArrivalTime(),
                tripOptions.get(0).getTripLength(), tripOptions.get(0).getDepartureStation(),tripOptions.get(0).getArrivalStation());
        for (Trip trip:tripOptions){
            if(trip.getTrainNumber().equals(cbSelectOption.getValue())){
                selectedTrip= new Trip(trip.getTrainNumber(),trip.getDepartureTime(), trip.getArrivalTime(), trip.getTripLength(), trip.getDepartureStation(),
                                    trip.getArrivalStation());
            }
        }
        gridTicketInfoandPrice.setAlignment(Pos.CENTER_LEFT);
        gridTicketInfoandPrice.setHgap(10);
        gridTicketInfoandPrice.setVgap(12);
        gridTicketInfoandPrice.setPadding(new Insets(30));

        Label labelTitle = new Label("Your trip details");
        labelTitle.setStyle("-fx-font-size: 20pt;");
        Label labelTrainNumber= new Label("Train number: " + selectedTrip.getTrainNumber());
        labelTrainNumber.setStyle("-fx-font-size: 12pt;");
        Label labelDepartureStation= new Label("Departure station: " + selectedTrip.getDepartureStation());
        labelDepartureStation.setStyle("-fx-font-size: 12pt;");
        Label labelArrivalStation= new Label("Arrival station: " + selectedTrip.getArrivalStation());
        labelArrivalStation.setStyle("-fx-font-size: 12pt;");
        Label labelDepartureTime= new Label("Departure time: " + selectedTrip.getDepartureTime());
        labelDepartureTime.setStyle("-fx-font-size: 12pt;");
        Label labelArrivalTime= new Label("Arrival time: " + selectedTrip.getArrivalTime());
        labelArrivalTime.setStyle("-fx-font-size: 12pt;");
        Label labelTripLength= new Label("Trip length: " + selectedTrip.getTripLength());
        labelTripLength.setStyle("-fx-font-size: 12pt;");

        Label labelNrOfTickets=new Label("Number of tickets: " + tfNumberOfTickets.getText());
        labelNrOfTickets.setStyle("-fx-font-size: 12pt;");
        Label labelClass=new Label("Class: " + cbClass.getValue());
        labelClass.setStyle("-fx-font-size: 12pt;");
        Label labelStatus=new Label("Status of the traveler(s): " + cbStatus.getValue());
        labelStatus.setStyle("-fx-font-size: 12pt;");

        gridTicketInfoandPrice.add(labelTitle, 0, 0 );
        gridTicketInfoandPrice.add(labelTrainNumber, 0, 1 );
        gridTicketInfoandPrice.add(labelDepartureStation, 0, 2 );
        gridTicketInfoandPrice.add(labelArrivalStation, 0, 3 );
        gridTicketInfoandPrice.add(labelDepartureTime, 1,2 );
        gridTicketInfoandPrice.add(labelArrivalTime, 1,3 );
        gridTicketInfoandPrice.add(labelTripLength, 0, 4 );
        gridTicketInfoandPrice.add(labelNrOfTickets, 0, 5 );
        gridTicketInfoandPrice.add(labelClass, 0, 6 );
        gridTicketInfoandPrice.add(labelStatus, 0, 7 );

        Label labelConfirmPurchase= new Label("Comfirm purchase?");
        labelConfirmPurchase.setStyle("-fx-font-size: 12pt;");
        gridTicketInfoandPrice.add(labelConfirmPurchase, 0, 8 );

        Button btnGoBackTripOptions = new Button("Go to previous page");
        btnGoBackTripOptions.setStyle("-fx-font-size: 12pt;");
        btnGoBackTripOptions.setOnAction(e -> window.setScene(sceneTicketDetails));

        Button btnConfirm = new Button("Yes");
        btnConfirm.setStyle("-fx-font-size: 12pt;");
        Trip finalSelectedTrip = selectedTrip;
        btnConfirm.setOnAction(e ->{ window.setScene(sceneStart);
        //manageSeats(cbClass,trains,cbSelectOption,tfNumberOfTickets,gridTicket);
            updateSeatsTrainInfo(finalSelectedTrip.getTrainNumber(),cbClass.getValue().toString(),tfNumberOfTickets);
        });

        HBox hbButtons = new HBox();
        hbButtons.setSpacing(10.0);
        hbButtons.getChildren().addAll(btnConfirm,btnGoBackTripOptions);
        gridTicketInfoandPrice.add(hbButtons, 0,9);

    }

    public void manageSeats(ComboBox cbClass, ArrayList<Train> trains, ComboBox cbSelectOption, TextField tfNumberOfTickets,
                            GridPane gridTicketDetails){
        String selectedTrainOption = (String) cbSelectOption.getValue();
        String selectedClass = (String) cbClass.getValue();

        for (Train train : trains) {
            if(train.getTrainNumber().equals(selectedTrainOption)){
                switch (selectedClass) {
                    case "first class" -> {
                        if(Integer.parseInt(tfNumberOfTickets.getText()) <= train.getSeats1Class()){
                            train.setSeats1Class(train.getSeats1Class() - Integer.parseInt(tfNumberOfTickets.getText()));
                            updateSeatsTrainInfo(selectedTrainOption, selectedClass, tfNumberOfTickets);
                        }else{
                            showAlert(Alert.AlertType.ERROR, gridTicketDetails.getScene().getWindow(), "Form Error!",
                                    "There are only " + train.getSeats1Class().toString() + " seats left at the specified class");
                            return;
                        }
                    }
                    case "second class" -> {
                        if(Integer.parseInt(tfNumberOfTickets.getText()) <= train.getSeats2Class()){
                            train.setSeats2Class(train.getSeats2Class() - Integer.parseInt(tfNumberOfTickets.getText()));
                            updateSeatsTrainInfo(selectedTrainOption, selectedClass, tfNumberOfTickets);
                        }else{
                            showAlert(Alert.AlertType.ERROR, gridTicketDetails.getScene().getWindow(), "Form Error!",
                                    "There are only " + train.getSeats2Class().toString() + " seats left at the specified class");
                            return;
                        }

                    }
                    case "first class sleeping wagon" -> {
                        if(Integer.parseInt(tfNumberOfTickets.getText()) <= train.getSeats1SleepingClass()){
                            train.setSeats1SleepingClass(train.getSeats1SleepingClass() - Integer.parseInt(tfNumberOfTickets.getText()));
                            updateSeatsTrainInfo(selectedTrainOption, selectedClass, tfNumberOfTickets);
                        }else{
                            showAlert(Alert.AlertType.ERROR, gridTicketDetails.getScene().getWindow(), "Form Error!",
                                    "There are only " + train.getSeats1SleepingClass().toString() + " seats left at the specified class");
                            return;
                        }
                    }
                    case "second class sleeping wagon" -> {
                        if(Integer.parseInt(tfNumberOfTickets.getText()) <= train.getSeats2SleepingClass()){
                            train.setSeats2SleepingClass(train.getSeats2SleepingClass() - Integer.parseInt(tfNumberOfTickets.getText()));
                            updateSeatsTrainInfo(selectedTrainOption, selectedClass, tfNumberOfTickets);
                        }else{
                            showAlert(Alert.AlertType.ERROR, gridTicketDetails.getScene().getWindow(), "Form Error!",
                                    "There are only " + train.getSeats2SleepingClass().toString() + " seats left at the specified class");
                            return;
                        }
                    }
                }
                System.out.println("train number " + train.getTrainNumber());
                System.out.println("seats first class " + train.getSeats1Class());
                System.out.println("seats second class " + train.getSeats2Class());
                System.out.println("seats first class sleeping class " + train.getSeats1SleepingClass());
                System.out.println("seats second class sleeping class " + train.getSeats2SleepingClass());
            }
        }
    }

    public void updateSeatsTrainInfo(String selectedTrainOption, String selectedClass, TextField tfNumberOfTickets){
        try {
            FileReader fileTrainNumbers = new FileReader("D:\\Train_Booker\\Train_Booker\\trainInfo.txt");
            BufferedReader br = new BufferedReader(fileTrainNumbers);
            StringBuffer inputBuffer = new StringBuffer();
            Integer nrOfTickets = Integer.parseInt(tfNumberOfTickets.getText());
            String data = br.readLine();

            int line = 1;
            int lineClass = 0;
            while(data != null) {
                if (line % 8 == 1) {
                    if (data.equals(selectedTrainOption)) {
                        lineClass = line + getIndexForSelectedClass(selectedClass);
                    }
                }
                if(line != lineClass){
                    inputBuffer.append(data);
                    inputBuffer.append('\n');
                }else{
                    Integer difference = Integer.parseInt(data) - nrOfTickets;
                    inputBuffer.append(difference.toString());
                    inputBuffer.append('\n');
                }
                data = br.readLine();
                line++;
            }
            String inputStr = inputBuffer.toString();
            System.out.println(inputStr);
            fileTrainNumbers.close();

            FileOutputStream fileOut = new FileOutputStream("D:\\Train_Booker\\Train_Booker\\trainInfo.txt");
            fileOut.write(inputStr.getBytes());
            fileOut.close();

        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public Integer getIndexForSelectedClass(String selectedClass) {
        return switch (selectedClass) {
            case "first class" -> 4;
            case "second class" -> 5;
            case "first class sleeping wagon" -> 6;
            case "second class sleeping wagon" -> 7;
            default -> -1;
        };
    }


    public void createSceneSignUp(Scene sceneStart, Scene sceneLogIn, User user, GridPane gridSignUp) {
        gridSignUp.setAlignment(Pos.CENTER_LEFT);
        gridSignUp.setHgap(10);
        gridSignUp.setVgap(12);
        gridSignUp.setPadding(new Insets(30));

        Button btnSubmitSignUp = new Button("Submit");
        btnSubmitSignUp.setStyle("-fx-font-size: 12pt;");
        Button btnGoBackSignUp = new Button("Go back");
        btnGoBackSignUp.setStyle("-fx-font-size: 12pt;");

        //FIRST NAME, LAST NAME, EMAIL, PHONE NUMBER, RESIDENCE PLACE, PASSWORD
        Label labelLastNameSignUp = new Label("Last Name:");
        TextField tfLastNameSignUp = new TextField();
        Label labelFirstNameSignUp = new Label("First Name:");
        TextField tfFirstNameSignUp = new TextField();
        Label labelEmailSignUp = new Label("Email:");
        TextField tfEmailSignUp = new TextField();
        Label labelPhoneSignUp = new Label("Phone number:  (+)40");
        TextField tfPhoneSignUp = new TextField();
        Label labelResidenceSignUp = new Label("Residence place:");
        TextField tfResidenceSignUp = new TextField();
        Label labelPasswordSignUp = new Label("Password:");
        PasswordField pfPwdSignUp = new PasswordField();
        Label labelConfirmPasswordSignUp = new Label("Confirm password:");
        PasswordField pfConfirmPwdSignUp = new PasswordField();

        HBox hbButtonsSignUp = new HBox();
        hbButtonsSignUp.setSpacing(10.0);
        hbButtonsSignUp.getChildren().addAll(btnSubmitSignUp, btnGoBackSignUp);
        btnGoBackSignUp.setOnAction(e -> {
            tfLastNameSignUp.clear();
            tfFirstNameSignUp.clear();
            tfEmailSignUp.clear();
            tfPhoneSignUp.clear();
            tfResidenceSignUp.clear();
            pfPwdSignUp.clear();
            pfConfirmPwdSignUp.clear();
            window.setScene(sceneStart);
        });

        gridSignUp.add(labelFirstNameSignUp, 0, 0);
        gridSignUp.add(tfFirstNameSignUp, 1, 0);
        gridSignUp.add(labelLastNameSignUp, 0, 1);
        gridSignUp.add(tfLastNameSignUp, 1, 1);
        gridSignUp.add(labelEmailSignUp, 0, 2);
        gridSignUp.add(tfEmailSignUp, 1, 2);
        gridSignUp.add(labelPhoneSignUp, 0, 3);
        gridSignUp.add(tfPhoneSignUp, 1, 3);
        gridSignUp.add(labelResidenceSignUp, 0, 4);
        gridSignUp.add(tfResidenceSignUp, 1, 4);
        gridSignUp.add(labelPasswordSignUp, 0, 5);
        gridSignUp.add(pfPwdSignUp, 1, 5);
        gridSignUp.add(labelConfirmPasswordSignUp, 0, 6);
        gridSignUp.add(pfConfirmPwdSignUp, 1, 6);
        gridSignUp.add(hbButtonsSignUp, 0, 7, 7, 1);

        btnSubmitSignUp.setOnAction(e -> {
            if (tfFirstNameSignUp.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridSignUp.getScene().getWindow(), "Form Error!", "Please enter your first name");
                return;
            }
            if (tfLastNameSignUp.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridSignUp.getScene().getWindow(), "Form Error!", "Please enter your last name");
                return;
            }
            if (tfEmailSignUp.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridSignUp.getScene().getWindow(), "Form Error!", "Please enter your email");
                return;
            }
            if (tfPhoneSignUp.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridSignUp.getScene().getWindow(), "Form Error!", "Please enter a phone number");
                return;
            }
            if (tfResidenceSignUp.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridSignUp.getScene().getWindow(), "Form Error!", "Please enter your residence place");
                return;
            }
            if (pfPwdSignUp.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridSignUp.getScene().getWindow(), "Form Error!", "Please enter a password");
                return;
            }
            if (pfConfirmPwdSignUp.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridSignUp.getScene().getWindow(), "Form Error!", "Please confirm password");
                return;
            }
            if (!tfEmailSignUp.getText().matches("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
                showAlert(Alert.AlertType.ERROR, gridSignUp.getScene().getWindow(), "Form Error!", "Please enter a valid email address");
                return;
            }
            if (!tfPhoneSignUp.getText().matches("\\d{9}")) {
                showAlert(Alert.AlertType.ERROR, gridSignUp.getScene().getWindow(), "Form Error!", "Please enter a valid phone number");
                return;
            }
            if (!pfPwdSignUp.getText().equals(pfConfirmPwdSignUp.getText())) {
                showAlert(Alert.AlertType.ERROR, gridSignUp.getScene().getWindow(), "Form Error!", "The two passwords don't match");
                return;
            }
            if(pfPwdSignUp.getLength() < 6){
                showAlert(Alert.AlertType.ERROR, gridSignUp.getScene().getWindow(), "Form Error!", "Your password must have at least 6 characters");
                return;
            }
            createUser(user, tfLastNameSignUp, tfFirstNameSignUp, tfEmailSignUp, tfPhoneSignUp, tfResidenceSignUp, pfPwdSignUp);
            showAlert(Alert.AlertType.CONFIRMATION, gridSignUp.getScene().getWindow(), "Registration Successful!", "Welcome " + tfFirstNameSignUp.getText());
            tfLastNameSignUp.clear();
            tfFirstNameSignUp.clear();
            tfEmailSignUp.clear();
            tfPhoneSignUp.clear();
            tfResidenceSignUp.clear();
            pfPwdSignUp.clear();
            pfConfirmPwdSignUp.clear();
            window.setScene(sceneLogIn);
        });
    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public User getUserInfo(User user, TextField tfLastNameSignUp, TextField tfFirstNameSignUp, TextField tfEmailSignUp,
                            TextField tfPhoneSignUp, TextField tfResidenceSignUp, PasswordField pfPwdSignUp) {
        user.setFirstName(tfFirstNameSignUp.getText());
        user.setLastName(tfLastNameSignUp.getText());
        user.setEmail(tfEmailSignUp.getText());
        user.setResidence(tfResidenceSignUp.getText());
        user.setPhoneNumber(tfPhoneSignUp.getText());
        user.setPassword(pfPwdSignUp.getText());
        return user;
    }

    public void writeNewUser(User user) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt", true))) {
            bw.write(user.getEmail());
            bw.newLine();
            bw.write(user.getPassword());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createUser(User user, TextField tfLastNameSignUp, TextField tfFirstNameSignUp, TextField tfEmailSignUp,
                           TextField tfPhoneSignUp, TextField tfResidenceSignUp, PasswordField pfPwdSignUp) {
        user = getUserInfo(user, tfLastNameSignUp, tfFirstNameSignUp, tfEmailSignUp,
                tfPhoneSignUp, tfResidenceSignUp, pfPwdSignUp);
        writeNewUser(user);
    }

    public boolean validateLogIn(TextField tfName, TextField pfPassword) {
        try {
            FileReader file = new FileReader("D:\\Train_Booker\\Train_Booker\\users.txt");
            BufferedReader br = new BufferedReader(file);

            int line = 1;
            boolean foundUsername = false, correctPassword = false;
            String data = br.readLine();
            while (data != null) {
                if (line % 2 == 1) {
                    if (tfName.getText().equals(data)) {
                        foundUsername = true;
                    }
                } else {
                    if (foundUsername) {
                        if (pfPassword.getText().equals(data)) {
                            correctPassword = true;
                        }
                    }
                }
                if (foundUsername && correctPassword) {
                    return true;
                }
                data = br.readLine();
                line++;
            }
            br.close();
            return false;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void parseStationList(ArrayList<String> stations) throws IOException {
        File file = new File("D:\\Train_Booker\\Train_Booker\\stations.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String string;
        int i = 0;
        while ((string = br.readLine()) != null) {
            stations.add(string);
            String station = stations.get(i++);
            System.out.println(station);
        }
    }

    public static void createTrains(ArrayList<Train> trains) {
        try {
            FileReader fileTrainNumbers = new FileReader("D:\\Train_Booker\\Train_Booker\\trainInfo.txt");
            BufferedReader br = new BufferedReader(fileTrainNumbers);

            String data = br.readLine();
            int line = 1;
            int index = -1;
            ArrayList<String> trainNumbers = new ArrayList<>();
            Integer[] timesBetweenStations = new Integer[0];
            ArrayList<Integer> departureTimes = new ArrayList<>();
            ArrayList<Integer> seats1Class = new ArrayList<>();
            ArrayList<Integer> seats2Class = new ArrayList<>();
            ArrayList<Integer> seats1SleepingClass = new ArrayList<>();
            ArrayList<Integer> seats2SleepingClass = new ArrayList<>();
            String[] stations = new String[0];
            String[] times;

            while (data != null) {
                if (line % 8 == 1) {
                    trainNumbers.add(data);
                    index++;
                }
                if (line % 8 == 2) {
                    stations = data.split("\\*");
                }
                if (line % 8 == 3) {
                    times = data.split(" ");
                    timesBetweenStations = new Integer[times.length];
                    for (int i = 0; i < times.length; i++) {
                        timesBetweenStations[i] = Integer.parseInt(times[i]);
                    }
                }
                if (line % 8 == 4) {
                    departureTimes.add(Integer.parseInt(data));
                }
                if (line % 8 == 5) {
                    seats1Class.add(Integer.parseInt(data));
                }
                if (line % 8 == 6) {
                    seats2Class.add(Integer.parseInt(data));
                }
                if (line % 8 == 7) {
                    seats1SleepingClass.add(Integer.parseInt(data));
                }
                if (line % 8 == 0) {
                    seats2SleepingClass.add(Integer.parseInt(data));
                    Train train = new Train(trainNumbers.get(index), stations, timesBetweenStations, departureTimes.get(index),
                            seats1Class.get(index), seats2Class.get(index), seats1SleepingClass.get(index), seats2SleepingClass.get(index));
                    trains.add(train);
                }

                data = br.readLine();
                line++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printTrains(ArrayList<Train> trains) {
        for (Train train : trains) {
            System.out.println("train number " + train.getTrainNumber());
            for (String station : train.getStations()) {
                System.out.println(" station " + station);
            }
            for (Integer times : train.getTimeBetweenStations()) {
                System.out.println(" time " + times);
            }
            System.out.println("departure time " + train.getDepartureTime());
            System.out.println("seats first class " + train.getSeats1Class());
            System.out.println("seats second class " + train.getSeats2Class());
            System.out.println("seats first class sleeping class " + train.getSeats1SleepingClass());
            System.out.println("seats second class sleeping class " + train.getSeats2SleepingClass());
        }
    }

    public boolean findTripOptions(ComboBox cbDepartureStation, ComboBox cbArrivalStation, ArrayList<Train> trains, ArrayList<Trip> tripOptions) {
        String selectedDS = (String) cbDepartureStation.getValue();
        String selectedAS = (String) cbArrivalStation.getValue();
        boolean dsFound = false, asFound = false;
        int indexDS = -1, indexAS = -1;
        Integer departureTime, arrivalTime, tripLength;
        for (Train train : trains) {
            for (int i = 0; i < train.getStations().length; i++) {
                if (train.getStations()[i].equals(selectedDS)) {
                    dsFound = true;
                    indexDS = i;
                }
                if (dsFound) {
                    if (train.getStations()[i].equals(selectedAS)) {
                        asFound = true;
                        indexAS = i;
                    }
                }
            }
            if (dsFound && asFound) {
                departureTime = train.getDepartureTime();
                for (int i = 0; i < indexDS; i++) {
                    departureTime = departureTime + train.getTimeBetweenStations()[i];
                }
                arrivalTime = departureTime;
                for (int i = indexDS; i < indexAS; i++) {
                    arrivalTime = arrivalTime + train.getTimeBetweenStations()[i];
                }
                tripLength = arrivalTime - departureTime;
                Trip trip = new Trip(train.getTrainNumber(), convertTime(departureTime), convertTime(arrivalTime),
                        convertTime(tripLength), selectedDS, selectedAS);
                tripOptions.add(trip);
            }
            dsFound = false;
            asFound = false;
        }
        /*
        for (Trip tripOption : tripOptions) {
            System.out.println("train number " + tripOption.getTrainNumber());
            System.out.println("departure time " + tripOption.getDepartureTime());
            System.out.println("arrival time" + tripOption.getArrivalTime());
            System.out.println("trip length " + tripOption.getTripLength());
            System.out.println("departure station" + tripOption.getDepartureStation());
            System.out.println("arrival station" + tripOption.getArrivalStation());
        }
         */
        return tripOptions.size() > 0;
    }

    public String convertTime(Integer time){
        Integer hour, minutes;
        hour = time / 60;
        minutes = time % 60;
        String finalTime = hour.toString() + ":" + minutes.toString();
        if(minutes == 0){
            finalTime = finalTime + "0";
        }
        return finalTime;
    }

    public static void main(String[] args) {
        launch(args);
        //ArrayList<Train> trains = new ArrayList<>();
        //ArrayList<String> stations = new ArrayList<>();
        //parseStationList(stations);
        //createTrains(trains);
        //printTrains(trains);

    }
}
