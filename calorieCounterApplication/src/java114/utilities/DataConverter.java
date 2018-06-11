package java114.utilities;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.sql.Date;


public class DataConverter {


    public Date convertToDate(String input) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateTime = LocalDate.parse(input, formatter);
        Date convertedDate = java.sql.Date.valueOf(dateTime);

        return convertedDate;
    }


    public int convertToInt(String input) {

        int convertedNumber = Integer.parseInt(input);

        return convertedNumber;

    }


}