package sample;
import javafx.scene.control.*;
import java.util.ArrayList;
public class TripOptions {
    public static boolean findTripOptions(ComboBox cbDepartureStation, ComboBox cbArrivalStation, ArrayList<Train> trains,
                                          ArrayList<Trip> tripOptions) {
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
                Trip trip = new Trip(train.getTrainNumber(), TimeConversion.convertTime(departureTime), TimeConversion.convertTime(arrivalTime),
                        TimeConversion.convertTime(tripLength), selectedDS, selectedAS, tripLength);
                tripOptions.add(trip);
            }
            dsFound = false;
            asFound = false;
        }
        return tripOptions.size() > 0;
    }
}
