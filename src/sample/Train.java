package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Train {
    private int trainNumber;
    private String departureStation;
    private String arrivalStation;
    private ArrayList<String> stations = new ArrayList<>();

    public Train(ArrayList<String> stations) throws IOException {
        File file = new File("D:\\Facultate\\AN 2\\SEM1\\OOP\\Train_Booker\\Train_Booker\\Train_Booker\\src\\sample\\Stations");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String string;
        int i = 0;
        while ((string = br.readLine()) != null) {
            stations.add(string);
            String station = stations.get(i++);
            System.out.println(station);
        }
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(String departureStation) {
        this.departureStation = departureStation;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(String arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public ArrayList<String> getStations() {
        return stations;
    }

    public void setStations(ArrayList<String> stations) {
        this.stations = stations;
    }
}
/*


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