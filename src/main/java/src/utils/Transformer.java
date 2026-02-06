package src.utils;

import java.util.ArrayList;
import java.util.HashSet;

public class Transformer {

    public static String fromIntToReleaseDate(int month, int day,  int year) {
        ArrayList<String> months = new  ArrayList<>() {
            {
                add("Jan");
                add("Feb");
                add("Mar");
                add("Apr");
                add("May");
                add("Jun");
                add("Jul");
                add("Aug");
                add("Sep");
                add("Oct");
                add("Nov");
                add("Dec");
            }
        };
        if (month < 0 || month > 12) {
            return "";
        }
        if (day < 1 || day > 31) {
            return "";
        }

        return months.get(month - 1) + " " + day + ", " + year;
    }
}
