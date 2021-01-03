package sample;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.io.*;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import java.util.ArrayList;

public class FileManager {
    public static String chooseTxtFile(LocalDate localDatePicked){
        LocalDate targetLocalDate = LocalDate.of( 2021, 1, 6 );
        LocalDate targetLocalDate1 = LocalDate.of( 2021, 1, 7 );
        LocalDate targetLocalDate2 = LocalDate.of( 2021, 1, 8 );
        LocalDate targetLocalDate3 = LocalDate.of( 2021, 1, 9 );
        LocalDate targetLocalDate4 = LocalDate.of( 2021, 1, 10 );
        LocalDate targetLocalDate5 = LocalDate.of( 2021, 1, 11 );
        LocalDate targetLocalDate6 = LocalDate.of( 2021, 1, 12 );

        String errorMsg = "Error";

        if ( localDatePicked.equals( targetLocalDate )){
            return "trainInfo.txt";
        }else if(localDatePicked.equals( targetLocalDate1 )){
            return "trainInfo1.txt";
        }else if(localDatePicked.equals( targetLocalDate2 )){
            return "trainInfo2.txt";
        }else if(localDatePicked.equals( targetLocalDate3 )){
            return "trainInfo3.txt";
        }else if(localDatePicked.equals( targetLocalDate4 )){
            return "trainInfo4.txt";
        }else if(localDatePicked.equals( targetLocalDate5 )){
            return "trainInfo5.txt";
        }else if(localDatePicked.equals( targetLocalDate6 )){
            return "trainInfo6.txt";
        }else{
            return errorMsg;
        }
    }
    public static void writeNewUser(User user) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt", true))) {
            bw.write(user.getEmail());
            bw.newLine();
            bw.write(user.getPassword());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeUserDetails(User user) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("userDetails.txt", true))) {
            bw.write(user.getFirstName());
            bw.newLine();
            bw.write(user.getLastName());
            bw.newLine();
            bw.write(user.getEmail());
            bw.newLine();
            bw.write(user.getPhoneNumber());
            bw.newLine();
            bw.write(user.getResidence());
            bw.newLine();
            bw.write(user.getPassword());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void createUser(User user, TextField tfLastNameSignUp, TextField tfFirstNameSignUp, TextField tfEmailSignUp,
                                  TextField tfPhoneSignUp, TextField tfResidenceSignUp, PasswordField pfPwdSignUp) {
        user = SignUp.getUserInfo(user, tfLastNameSignUp, tfFirstNameSignUp, tfEmailSignUp,
                tfPhoneSignUp, tfResidenceSignUp, pfPwdSignUp);
        writeNewUser(user);
        writeUserDetails(user);
    }
    public static void createTrains(ArrayList<Train> trains, String chosenTxtFile) {
        try {
            FileReader fileTrainNumbers = new FileReader(chosenTxtFile);
            BufferedReader br = new BufferedReader(fileTrainNumbers);

            String data = br.readLine();
            int line = 1;
            int index = -1;
            ArrayList<String> trainNumbers = new ArrayList<>();
            Integer[] timesBetweenStations = new Integer[0];
            ArrayList<Integer> departureTimes = new ArrayList<>();
            ArrayList<Integer> seats1Class = new ArrayList<>();
            ArrayList<Integer> seats2Class = new ArrayList<>();
            ArrayList<Integer> seats1SleepingClass = new ArrayList<>();
            ArrayList<Integer> seats2SleepingClass = new ArrayList<>();
            String[] stations = new String[0];
            String[] times;

            while (data != null) {
                if (line % 8 == 1) {
                    trainNumbers.add(data);
                    index++;
                }
                if (line % 8 == 2) {
                    stations = data.split("\\*");
                }
                if (line % 8 == 3) {
                    times = data.split(" ");
                    timesBetweenStations = new Integer[times.length];
                    for (int i = 0; i < times.length; i++) {
                        timesBetweenStations[i] = Integer.parseInt(times[i]);
                    }
                }
                if (line % 8 == 4) {
                    departureTimes.add(Integer.parseInt(data));
                }
                if (line % 8 == 5) {
                    seats1Class.add(Integer.parseInt(data));
                }
                if (line % 8 == 6) {
                    seats2Class.add(Integer.parseInt(data));
                }
                if (line % 8 == 7) {
                    seats1SleepingClass.add(Integer.parseInt(data));
                }
                if (line % 8 == 0) {
                    seats2SleepingClass.add(Integer.parseInt(data));
                    Train train = new Train(trainNumbers.get(index), stations, timesBetweenStations, departureTimes.get(index),
                            seats1Class.get(index), seats2Class.get(index), seats1SleepingClass.get(index), seats2SleepingClass.get(index));
                    trains.add(train);
                }

                data = br.readLine();
                line++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Integer getIndexForSelectedClass(String selectedClass) {
        return switch (selectedClass) {
            case "first class" -> 4;
            case "second class" -> 5;
            case "first class sleeping wagon" -> 6;
            case "second class sleeping wagon" -> 7;
            default -> -1;
        };
    }
    public static void updateSeatsTrainInfo(String selectedTrainOption, String selectedClass, TextField tfNumberOfTickets, String chosenTxtFile){
        try {
            FileReader fileTrainNumbers = new FileReader(chosenTxtFile);
            BufferedReader br = new BufferedReader(fileTrainNumbers);
            StringBuffer inputBuffer = new StringBuffer();
            Integer nrOfTickets = Integer.parseInt(tfNumberOfTickets.getText());
            String data = br.readLine();

            int line = 1;
            int lineClass = 0;
            while(data != null) {
                if (line % 8 == 1) {
                    if (data.equals(selectedTrainOption)) {
                        lineClass = line + getIndexForSelectedClass(selectedClass);
                    }
                }
                if(line != lineClass){
                    inputBuffer.append(data);
                    inputBuffer.append('\n');
                }else{
                    Integer difference = Integer.parseInt(data) - nrOfTickets;
                    inputBuffer.append(difference.toString());
                    inputBuffer.append('\n');
                }
                data = br.readLine();
                line++;
            }
            String inputStr = inputBuffer.toString();
            fileTrainNumbers.close();

            FileOutputStream fileOut = new FileOutputStream(chosenTxtFile);
            fileOut.write(inputStr.getBytes());
            fileOut.close();

        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    public static void parseStationList(ArrayList<String> stations) throws IOException {
        File file = new File("D:\\Train_Booker\\Train_Booker\\stations.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String string;
        while ((string = br.readLine()) != null) {
            stations.add(string);
        }
    }

}
