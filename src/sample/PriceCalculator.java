package sample;

public class PriceCalculator {
    public static Double computePricePerTicket(Trip selectedTrip, String selectedClass, String selectedStatus){
        Double pricePerMinute = 0.5;

        switch (selectedClass) {
            case "first class":
            case "first class sleeping wagon":
                if (selectedStatus.equals("child(less than 6 years old)")) {
                    pricePerMinute = 0.00;
                } else {
                    pricePerMinute = 0.40;
                }
                break;
            case "second class":
                if (selectedStatus.equals("adult")) {
                    pricePerMinute = 0.25;
                } else if (selectedStatus.equals("pupil")) {
                    pricePerMinute = 0.12;
                } else {
                    pricePerMinute = 0.00;
                }
                break;
            case "second class sleeping wagon":
                if (selectedStatus.equals("child(less than 6 years old)")) {
                    pricePerMinute = 0.00;
                } else {
                    pricePerMinute = 0.37;
                }
                break;
        }
        return selectedTrip.getTripLengthInMinutes() * pricePerMinute;
    }

}
