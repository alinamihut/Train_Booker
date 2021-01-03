package sample;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import java.util.ArrayList;
public class SeatManager {
    public static void manageSeats(ComboBox cbClass, ArrayList<Train> trains, ComboBox cbSelectOption, TextField tfNumberOfTickets, String chosenTxtFile){
        String selectedTrainOption = (String) cbSelectOption.getValue();
        String selectedClass = (String) cbClass.getValue();

        for (Train train : trains) {
            if(train.getTrainNumber().equals(selectedTrainOption)){
                switch (selectedClass) {
                    case "first class" -> {
                        train.setSeats1Class(train.getSeats1Class() - Integer.parseInt(tfNumberOfTickets.getText()));
                        FileManager.updateSeatsTrainInfo(selectedTrainOption, selectedClass, tfNumberOfTickets, chosenTxtFile);
                    }
                    case "second class" -> {
                        train.setSeats2Class(train.getSeats2Class() - Integer.parseInt(tfNumberOfTickets.getText()));
                        FileManager.updateSeatsTrainInfo(selectedTrainOption, selectedClass, tfNumberOfTickets, chosenTxtFile);
                    }
                    case "first class sleeping wagon" -> {
                        train.setSeats1SleepingClass(train.getSeats1SleepingClass() - Integer.parseInt(tfNumberOfTickets.getText()));
                        FileManager.updateSeatsTrainInfo(selectedTrainOption, selectedClass, tfNumberOfTickets, chosenTxtFile);
                    }
                    case "second class sleeping wagon" -> {
                        train.setSeats2SleepingClass(train.getSeats2SleepingClass() - Integer.parseInt(tfNumberOfTickets.getText()));
                        FileManager.updateSeatsTrainInfo(selectedTrainOption, selectedClass, tfNumberOfTickets, chosenTxtFile);
                    }
                }
            }
        }
    }
    public static Boolean checkNrOfAvailableSeats(ComboBox cbClass, ArrayList<Train> trains, ComboBox cbSelectOption, TextField tfNumberOfTickets,
                                                  GridPane gridTicketDetails){
        String selectedTrainOption = (String) cbSelectOption.getValue();
        String selectedClass = (String) cbClass.getValue();

        for (Train train : trains) {
            if(train.getTrainNumber().equals(selectedTrainOption)){
                switch (selectedClass) {
                    case "first class" -> {
                        if(Integer.parseInt(tfNumberOfTickets.getText()) > train.getSeats1Class()){
                            AlertBox.showAlert(Alert.AlertType.ERROR, gridTicketDetails.getScene().getWindow(), "Form Error!",
                                    "There are only " + train.getSeats1Class().toString() + " seats left at the specified class");
                            return false;
                        }
                    }
                    case "second class" -> {
                        if(Integer.parseInt(tfNumberOfTickets.getText()) > train.getSeats2Class()){
                            AlertBox.showAlert(Alert.AlertType.ERROR, gridTicketDetails.getScene().getWindow(), "Form Error!",
                                    "There are only " + train.getSeats2Class().toString() + " seats left at the specified class");
                            return false;
                        }
                    }
                    case "first class sleeping wagon" -> {
                        if(Integer.parseInt(tfNumberOfTickets.getText()) > train.getSeats1SleepingClass()){
                            AlertBox.showAlert(Alert.AlertType.ERROR, gridTicketDetails.getScene().getWindow(), "Form Error!",
                                    "There are only " + train.getSeats1SleepingClass().toString() + " seats left at the specified class");
                            return false;
                        }
                    }
                    case "second class sleeping wagon" -> {
                        if(Integer.parseInt(tfNumberOfTickets.getText()) > train.getSeats2SleepingClass()){
                            AlertBox.showAlert(Alert.AlertType.ERROR, gridTicketDetails.getScene().getWindow(), "Form Error!",
                                    "There are only " + train.getSeats2SleepingClass().toString() + " seats left at the specified class");
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
