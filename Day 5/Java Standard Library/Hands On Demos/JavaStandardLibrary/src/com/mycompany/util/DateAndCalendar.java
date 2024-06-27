package com.mycompany.util;

import java.util.Calendar;
import java.util.Date;

public class DateAndCalendar {

    public static void main(String[] args) {
        Date now = new Date();
        System.out.println("Current date and time: " + now);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        System.out.println("Current year: " + year);
    }
}
