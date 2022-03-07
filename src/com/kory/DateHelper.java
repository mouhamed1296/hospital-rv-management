package com.kory;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateHelper {
    public static Date createDate(short jour, short mois, int annee) throws IllegalStateException {
        return switch (mois) {
            case 1 -> new GregorianCalendar(annee, Calendar.JANUARY, jour).getTime();
            case 2 -> new GregorianCalendar(annee, Calendar.FEBRUARY, jour).getTime();
            case 3 -> new GregorianCalendar(annee, Calendar.MARCH, jour).getTime();
            case 4 -> new GregorianCalendar(annee, Calendar.APRIL, jour).getTime();
            case 5 -> new GregorianCalendar(annee, Calendar.MAY, jour).getTime();
            case 6 -> new GregorianCalendar(annee, Calendar.JUNE, jour).getTime();
            case 7 -> new GregorianCalendar(annee, Calendar.JULY, jour).getTime();
            case 8 -> new GregorianCalendar(annee, Calendar.AUGUST, jour).getTime();
            case 9 -> new GregorianCalendar(annee, Calendar.SEPTEMBER, jour).getTime();
            case 10 -> new GregorianCalendar(annee, Calendar.OCTOBER, jour).getTime();
            case 11 -> new GregorianCalendar(annee, Calendar.NOVEMBER, jour).getTime();
            case 12 -> new GregorianCalendar(annee, Calendar.DECEMBER, jour).getTime();
            default -> throw new IllegalStateException("Unexpected month: " + mois);
        };
    }
    public static Date currentDate() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String[] dateComponents = date.format(formatter).split("-");
        return createDate((short) Integer.parseInt(dateComponents[0]),
                (short) Integer.parseInt(dateComponents[1]), Integer.parseInt(dateComponents[2]));
    }
    public static java.sql.Date toSqlDate(Date date) {
        return java.sql.Date.valueOf(date.toInstant().atZone(ZoneId.of("Africa/Dakar")).toLocalDate());
    }

    public static LocalDate toLocalZoneDate(Date date) {
        return date.toInstant().atZone(ZoneId.of("Africa/Dakar")).toLocalDate();
    }
}
