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
    private Integer seats1Class;
    private Integer seats2Class;
    private Integer seats1SleepingClass;
    private Integer seats2SleepingClass;

    public Train (String trainNumber, String[] stations, Integer[] timeBetweenStations, Integer departureTime,
                  Integer seats1Class, Integer seats2Class, Integer seats1SleepingClass, Integer seats2SleepingClass){
        this.trainNumber = trainNumber;
        this.stations = stations;
        this.timeBetweenStations = timeBetweenStations;
        this.departureTime = departureTime;
        this.seats1Class = seats1Class;
        this.seats2Class = seats2Class;
        this.seats1SleepingClass = seats1SleepingClass;
        this.seats2SleepingClass = seats2SleepingClass;
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

