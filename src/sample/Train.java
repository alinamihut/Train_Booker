package sample;

import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class Train {
    private String trainNumber;
   // private String departureStation;
    //private String arrivalStation;
    private Integer departureTime;
    private String[] stations;
    private ArrayList<Integer> timeBetweenStations = new ArrayList<>();

    public Train (String trainNumber, String[] stations, ArrayList<Integer> timeBetweenStations, Integer departureTime){
        this.trainNumber=trainNumber;
        this.stations=stations;
        this.timeBetweenStations=timeBetweenStations;
        this.departureTime=departureTime;
    }
    public static void parseStationList(ArrayList<String> stations) throws IOException {
        File file = new File("D:\\Train_Booker\\Train_Booker\\stations.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String string;
        int i = 0;
        while ((string = br.readLine()) != null) {
            stations.add(string);
            String station = stations.get(i++);
            System.out.println(station);
        }
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

    public ArrayList<Integer> getTimeBetweenStations() {
        return timeBetweenStations;
    }

    public void setTimeBetweenStations(ArrayList<Integer> timeBetweenStations) {
        this.timeBetweenStations = timeBetweenStations;
    }

    public String[] getStations() {
        return stations;
    }

    public static void createTrains(ArrayList<Train> trains) {
        try {
            FileReader fileTrainNumbers = new FileReader("D:\\Train_Booker\\Train_Booker\\trainNumbers.txt");
            BufferedReader br = new BufferedReader(fileTrainNumbers);

            String data = br.readLine();

            int line = 1;
            int index=-1;
            ArrayList<String> trainNumbers = new ArrayList<>();
            ArrayList<Integer> timesBetweenStations;
            ArrayList<Integer> departureTimes = new ArrayList<>();
            while (data != null){

                if (line%4==1) {
                    trainNumbers.add(data);
                    index++;
                }
                String[] stations = new String[0];
            if (line %4 ==2) {
                stations= data.split("\\*");
            }
                 timesBetweenStations = new ArrayList<>();
            if (line%4 ==3) {
                String[] times = data.split(" ");

                for (int i = 0; i < times.length; i++) {
                    timesBetweenStations.add(Integer.parseInt(times[i]));
                }
            }
                if (line % 4 == 0) {
                    departureTimes.add(Integer.parseInt(data));
                    Train train=new Train(trainNumbers.get(index), stations, timesBetweenStations,departureTimes.get(index) );
                    System.out.println("train number " + train.trainNumber);
                    for (String station: train.stations){
                        System.out.println(" station " + station);
                    }
                    for (Integer times: train.timeBetweenStations){
                        System.out.println(" time " + times);
                    }
                    System.out.println("departure time " + train.departureTime);
                    trains.add(train);
                }


            data = br.readLine();
            line++;
        }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/*
    public static void createTrains(ArrayList<Train> trains) {
        try {
            FileReader fileTrainNumbers = new FileReader("D:\\Train_Booker\\Train_Booker\\trainNumbers.txt");
            BufferedReader brNumbers = new BufferedReader(fileTrainNumbers);
            int i = 0;
            String data = brNumbers.readLine();
            while (data != null) {
                trains.get(i).setTrainNumber(data);
                data = brNumbers.readLine();
                i++;
            }
            FileReader fileStations = new FileReader("D:\\Train_Booker\\Train_Booker\\trainStations.txt");
            BufferedReader brStations = new BufferedReader(fileStations);
            int line = 1;
            data = brStations.readLine();
            while (data != null) {
                if (line%2!=0){
                    trains.get((line-1)/2).stations=data.split("\\*");
                }
                else{
                    String[] times=data.split(" ");
                    System.out.println( times.length);
                    for ( i=0; i<times.length;i++){
                        trains.get((line)/2).timeBetweenStations.add(Integer.parseInt(times[i]));
                    }

                }
                data = brStations.readLine();
                line++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/
    public static void printTrains(ArrayList<Train> trains){
        for (Train train:trains){
            System.out.println("train number " + train.trainNumber);
            for (String station: train.stations){
                System.out.println(" station " + station);
            }
            for (Integer times: train.timeBetweenStations){
                System.out.println(" time " + times);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        ArrayList<Train> trains= new ArrayList<>();
        ArrayList<String> stations=new ArrayList<>();
        parseStationList(stations);
        createTrains(trains);
        //printTrains(trains);
    }
}

