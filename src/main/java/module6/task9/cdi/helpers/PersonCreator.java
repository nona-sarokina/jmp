package module6.task9.cdi.helpers;

import module3.task1.beans.Person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PersonCreator {
    public static final String NAME_WITH_LOWERCASE_FIRST_LETTER = "craig Neu";
    public static final String NAME_WITH_UPPERCASE_FIRST_LETTER = "Craig Neu";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String DATE = "1978-11-30";
    private static SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    public static final String ADDRESS = "1329 American Drive";
    public static final String CITY = "Tallahassee";
    private static Person person = new Person(NAME_WITH_LOWERCASE_FIRST_LETTER, null, ADDRESS, CITY, 32308);

    public static Person getPersonSample() throws ParseException {
        if (null == person.getDateOfBirth()) {
            person.setDateOfBirth(dateFormat.parse(DATE));
        }

        return person;
    }
}
