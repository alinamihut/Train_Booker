package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Train {
    private String trainNumber;
   // private String departureStation;
    //private String arrivalStation;
    private Integer departureTime;
    private String[] stations;
    private Integer[] timeBetweenStations;

    public Train (String trainNumber, String[] stations, Integer[] timeBetweenStations, Integer departureTime){
        this.trainNumber = trainNumber;
        this.stations = stations;
        this.timeBetweenStations = timeBetweenStations;
        this.departureTime = departureTime;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public int getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(int departureTime) {
        this.departureTime = departureTime;
    }

    public Integer[] getTimeBetweenStations() {
        return timeBetweenStations;
    }

    public void setTimeBetweenStations(Integer[] timeBetweenStations) {
        this.timeBetweenStations = timeBetweenStations;
    }

    public String[] getStations() {
        return stations;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Train> trains= new ArrayList<>();
        ArrayList<String> stations=new ArrayList<>();
       //parseStationList(stations);
       // createTrains(trains);
       // printTrains(trains);
    }
}

