package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Train {
    private String trainNumber;
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

    public int getDepartureTime() {
        return departureTime;
    }

    public Integer[] getTimeBetweenStations() {
        return timeBetweenStations;
    }

    public String[] getStations() {
        return stations;
    }

    public Integer getSeats1Class() {
        return seats1Class;
    }

    public Integer getSeats2Class() {
        return seats2Class;
    }

    public Integer getSeats1SleepingClass() {
        return seats1SleepingClass;
    }

    public Integer getSeats2SleepingClass() {
        return seats2SleepingClass;
    }

    public void setSeats1Class(Integer seats1Class) {
        this.seats1Class = seats1Class;
    }

    public void setSeats2Class(Integer seats2Class) {
        this.seats2Class = seats2Class;
    }

    public void setSeats1SleepingClass(Integer seats1SleepingClass) {
        this.seats1SleepingClass = seats1SleepingClass;
    }

    public void setSeats2SleepingClass(Integer seats2SleepingClass) {
        this.seats2SleepingClass = seats2SleepingClass;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Train> trains= new ArrayList<>();
        ArrayList<String> stations=new ArrayList<>();
    }
}

