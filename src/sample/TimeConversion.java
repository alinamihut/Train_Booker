package sample;

public class TimeConversion {
    public static String convertTime(Integer time){
        Integer hour, minutes;
        hour = time / 60;
        minutes = time % 60;
        String finalTime = hour.toString() + ":" + minutes.toString();
        if(minutes == 0){
            finalTime = finalTime + "0";
        }
        return finalTime;
    }
}
