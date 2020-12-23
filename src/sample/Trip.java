package sample;

public class Trip {
    private String trainNumber;
    private String departureTime;
    private String arrivalTime;
    private String tripLength;
    private String departureStation;
    private String arrivalStation;

    public Trip(String trainNumber, String departureTime, String arrivalTime, String tripLength, String departureStation, String arrivalStation) {
        this.trainNumber = trainNumber;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.tripLength = tripLength;
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getTripLength() {
        return tripLength;
    }

    public String getDepartureStation() {
        return departureStation;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }
}
