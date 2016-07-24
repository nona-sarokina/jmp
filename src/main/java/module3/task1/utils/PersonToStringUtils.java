package module3.task1.utils;

import module3.task1.beans.Mode;
import module3.task1.beans.Person;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

/**
 * Created by user on 24.07.2016.
 */
public class PersonToStringUtils {
    public static final String SEPARATOR = ", ";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String DB_FORMAT = "('%s','%s','%s','%s', %d)";
    private static final String FILE_FORMAT = "%s, %s, %s, %s, %d";

    public static String personToString(Person person, Mode mode) {
        switch (mode) {
            case DB:
                return getDBPersonString(person);
            case FILE:
            default:
                return getFilePersonString(person);
        }

    }

    private static String getDBPersonString(Person person) {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatter.format(DB_FORMAT, person.getName(), getFormattedDate(person.getDateOfBirth()), person.getAddress(), person.getCity(), person.getZipCode());
        return sb.toString();
    }

    private static String getFilePersonString(Person person) {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatter.format(FILE_FORMAT, person.getName(), getFormattedDate(person.getDateOfBirth()), person.getAddress(), person.getCity(), person.getZipCode());
        return sb.toString();
    }

    public static String getFormattedDate(Date date) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT);
        return dateFormatter.format(date);
    }
}
