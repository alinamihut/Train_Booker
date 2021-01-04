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
public class Payment {
    public static void createScenePayment(Scene sceneStart, Scene sceneTicketInfoandPrice, GridPane gridPayment, TextField tfNumberOfTickets, ComboBox cbClass,
                                          ComboBox cbSelectOption, ArrayList<Train> trains, LocalDate localDatePicked, TableView<Trip> table, Stage window){
        gridPayment.setAlignment(Pos.CENTER_LEFT);
        gridPayment.setHgap(10);
        gridPayment.setVgap(12);
        gridPayment.setPadding(new Insets(30));
        gridPayment.setStyle("-fx-background-color:lightblue");

        Label labelTitle = new Label("Introduce your card information ");
        labelTitle.setStyle("-fx-font-size: 18pt;");
        Label labelCardType = new Label("Card type:");
        labelCardType.setStyle("-fx-font-size: 14pt;");
        ComboBox cbCardType = new ComboBox();
        cbCardType.getItems().addAll("VISA", "MasterCard");
        Label labelCardNumber = new Label("Card number:");
        labelCardNumber.setStyle("-fx-font-size: 14pt;");
        TextField tfCardNumber=new TextField();
        Label labelExpirationDate = new Label("Valid until:");
        labelExpirationDate.setStyle("-fx-font-size: 14pt;");
        Label labelMonth = new Label("Month:");
        labelMonth.setStyle("-fx-font-size: 11pt;");
        ComboBox cbMonth= new ComboBox();
        cbMonth.getItems().addAll("01","02","03","04","05","06","07","08","09","10","11","12");
        Label labelYear= new Label("Year:");
        labelYear.setStyle("-fx-font-size: 11pt;");
        ComboBox cbYear= new ComboBox();
        cbYear.getItems().addAll("2021","2022","2023","2024","2025","2026","2027","2028","2029","2030","2031");
        Label labelCVV = new Label("CVV:");
        labelCVV.setStyle("-fx-font-size: 14pt;");
        TextField tfCVV=new TextField();

        Button btnFinalizePayment = new Button("Finalize Payment");
        btnFinalizePayment.setStyle("-fx-font-size: 12pt;");
        Button btnGoBackPrevious= new Button("Go to previous page");
        btnGoBackPrevious.setStyle("-fx-font-size: 12pt;");
        btnGoBackPrevious.setOnAction(e -> window.setScene(sceneTicketInfoandPrice));

        HBox hbButtons = new HBox();
        hbButtons.setSpacing(10.0);
        hbButtons.getChildren().addAll(btnGoBackPrevious,btnFinalizePayment);

        gridPayment.add(labelTitle, 0,0);
        gridPayment.add(labelCardType, 0,1);
        gridPayment.add(cbCardType, 1,1);
        gridPayment.add(labelCardNumber, 0,2);
        gridPayment.add(tfCardNumber, 1,2);
        gridPayment.add(labelExpirationDate, 0,3);
        gridPayment.add(labelMonth, 1,3);
        gridPayment.add(cbMonth, 2,3);
        gridPayment.add(labelYear, 3,3);
        gridPayment.add(cbYear, 4,3);
        gridPayment.add(labelCVV, 0,4);
        gridPayment.add(tfCVV, 1,4);
        gridPayment.add(hbButtons, 0,5);

        GridPane layoutFinal = new GridPane();
        Scene finalScene = new Scene(layoutFinal, 600, 400);
        btnFinalizePayment.setOnAction(e->{
            if (cbCardType.getSelectionModel().isEmpty()) {
                AlertBox.showAlert(Alert.AlertType.ERROR, gridPayment.getScene().getWindow(), "Form Error!", "Please select a card type");
                return;
            }
            if (tfCardNumber.getText().isEmpty()) {
                AlertBox.showAlert(Alert.AlertType.ERROR, gridPayment.getScene().getWindow(), "Form Error!", "Please introduce your card number");
                return;
            }
            if (cbMonth.getSelectionModel().isEmpty()||cbYear.getSelectionModel().isEmpty()) {
                AlertBox.showAlert(Alert.AlertType.ERROR, gridPayment.getScene().getWindow(), "Form Error!", "Please select the expiration date for your card");
                return;
            }
            if (tfCardNumber.getText().isEmpty()) {
                AlertBox.showAlert(Alert.AlertType.ERROR, gridPayment.getScene().getWindow(), "Form Error!", "Please introduce your card's CVV");
                return;
            }
            if (!tfCardNumber.getText().matches("\\d{12}")) {
                AlertBox.showAlert(Alert.AlertType.ERROR, gridPayment.getScene().getWindow(), "Form Error!", "Please enter a valid card number");
                return;
            }
            if (!tfCVV.getText().matches("\\d{3}")) {
                AlertBox.showAlert(Alert.AlertType.ERROR, gridPayment.getScene().getWindow(), "Form Error!", "Please enter a valid CVV");
                return;
            }
            String chosenTxtFile = FileManager.chooseTxtFile(localDatePicked);
            SeatManager.manageSeats(cbClass, trains, cbSelectOption, tfNumberOfTickets, chosenTxtFile);
            table.getItems().clear();
            FinalScene.createFinalScene(sceneStart, layoutFinal, window);
            window.setScene(finalScene);
        });
    }
}
