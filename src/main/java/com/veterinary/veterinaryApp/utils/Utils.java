package com.veterinary.veterinaryApp.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Utils {

    public static String fiveDigits() {
        Random random = new Random();
        int number = random.nextInt(100000000); // Genera un número entre 0 y 99999999
        return String.format("%08d", number);  // Completa con ceros a la izquierda si es necesario
    }

    public static long generateRandomLong() {
        Random random = new Random();
        return 1 + (long)(random.nextInt(3)); // Genera un número entre 0 y 2, luego suma 1
    }

//    public static List<String> generateNextSevenDays() {
//        List<String> dates = new ArrayList<>();
//        LocalDate currentDate = LocalDate.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//        int addedDays = 0;
//        while (addedDays < 7) {
//            if (!(currentDate.getDayOfWeek() == DayOfWeek.SATURDAY || currentDate.getDayOfWeek() == DayOfWeek.SUNDAY)) {
//                dates.add(currentDate.format(formatter));
//                addedDays++;
//            }
//            currentDate = currentDate.plusDays(1);
//        }
//
//        return dates;
//    }

    public static final List<String> hoursOfServiceSlots = Arrays.asList(
            "8:00 AM", "09:00 AM", "10:00 AM", "11:00 AM",
            "01:00 PM", "02:00 PM", "03:00 PM", "04:00 PM", "05:00 PM"
    );
}
