package module3.task1.deserializers;

import module3.task1.beans.Person;
import module3.task1.utils.PersonToStringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by user on 24.07.2016.
 */
public class FileDeserializer implements IDeserializer<String, Person> {
    @Override
    public Person deserialize(String string) {
        String[] fields = string.split(PersonToStringUtils.SEPARATOR);
        Person person = null;
        if (fields.length == 5) {
            DateFormat format = new SimpleDateFormat(PersonToStringUtils.DATE_FORMAT);
            try {
                String name = fields[0];
                Date date = format.parse(fields[1]);
                String address = fields[2].trim();
                String city = fields[3].trim();
                int zipCode = Integer.valueOf(fields[4].trim());
                person = new Person(name, date, address, city, zipCode);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return person;
    }
}
