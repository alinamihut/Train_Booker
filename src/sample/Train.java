package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Train {
    private int trainNumber;
    private String departureStation;
    private String arrivalStation;
    private ArrayList<String> stations=new ArrayList<>();

    public Train(int trainNumber, String departureStation, String arrivalStation){
        this.trainNumber=trainNumber;
        this.departureStation=departureStation;
        this.arrivalStation=arrivalStation;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    public void setDepartureStation(String departureStation) {
        this.departureStation = departureStation;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }

    public int getTrainNumber() {
        return trainNumber;
    }
    public String getDepartureStation(){
        return departureStation;
    }
    /*public Train(ArrayList<String> stations) throws FileNotFoundException{
        File file=new File("C:\\Users\\Alina Mihut\\IdeaProjects\\trainbooker_v1\\counties.txt"));
        BufferedReader
        int i=0;
        while(s.hasNextLine()){
            stations.add(s);
        }
    }
    */

}
