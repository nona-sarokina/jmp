package module3.task1.beans;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 24.07.2016.
 */
public class FileRecord extends Person {

    public static final String SEPARATOR = ", ";

    public FileRecord(String name, Date dateOfBirth, String address, String city, int zipCode) {
        super(name, dateOfBirth, address, city, zipCode);
    }

    public FileRecord(Person person) {
        super(person.getName(), person.getDateOfBirth(), person.getAddress(), person.getCity(), person.getZipCode());
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormatter.format(this.getDateOfBirth());
        return name + SEPARATOR + formattedDate + SEPARATOR + address + SEPARATOR + city + SEPARATOR + zipCode;
    }
}
