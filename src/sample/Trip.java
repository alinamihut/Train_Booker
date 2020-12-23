package sample;

public class Trip {
    private String trainNumber;
    private Integer departureTime;
    private Integer arrivalTime;
    private Integer tripLength;
    private String departureStation;
    private String arrivalStation;

    public Trip(String trainNumber, Integer departureTime, Integer arrivalTime, Integer tripLength, String departureStation, String arrivalStation) {
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

    public Integer getDepartureTime() {
        return departureTime;
    }

    public Integer getArrivalTime() {
        return arrivalTime;
    }

    public Integer getTripLength() {
        return tripLength;
    }

    public String getDepartureStation() {
        return departureStation;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }
}
