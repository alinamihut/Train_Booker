package sample;
import javafx.scene.control.TextField;
import java.io.*;
public class DataValidation {
    public static Boolean checkEmailExistence(TextField tfEmail){
        try {
            FileReader fileTrainNumbers = new FileReader("users.txt");
            BufferedReader br = new BufferedReader(fileTrainNumbers);
            String data = br.readLine();
            int line = 1;
            while (data != null) {
                if(line % 2 != 0){
                    if(data.equals(tfEmail.getText())){
                        return true;
                    }
                }
                line++;
                data = br.readLine();
            }
        } catch (IOException e) {
            return false;
        }
        return false;
    }
    public static boolean validateLogIn(TextField tfName, TextField pfPassword) {
        try {
            FileReader file = new FileReader("D:\\Train_Booker\\Train_Booker\\users.txt");
            BufferedReader br = new BufferedReader(file);

            int line = 1;
            boolean foundUsername = false, correctPassword = false;
            String data = br.readLine();
            while (data != null) {
                if (line % 2 == 1) {
                    if (tfName.getText().equals(data)) {
                        foundUsername = true;
                    }
                } else {
                    if (foundUsername) {
                        if (pfPassword.getText().equals(data)) {
                            correctPassword = true;
                        }
                    }
                }
                if (foundUsername && correctPassword) {
                    return true;
                }
                data = br.readLine();
                line++;
            }
            br.close();
            return false;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
