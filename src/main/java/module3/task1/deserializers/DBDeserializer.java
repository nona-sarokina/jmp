package module3.task1.deserializers;

import module3.task1.beans.Person;
import module3.task1.beans.PersonDBFields;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by user on 24.07.2016.
 */
public class DBDeserializer implements IDeserializer <ResultSet, Person> {

    @Override
    public Person deserialize(ResultSet object){
        try {
            final String name = object.getString(PersonDBFields.NAME.toString());
            final Date dateOfBirth = object.getDate(PersonDBFields.DATEOFBIRTH.toString());
            final String address = object.getString(PersonDBFields.ADDRESS.toString());
            final String city = object.getString(PersonDBFields.CITY.toString());
            final int zipCode = object.getInt(PersonDBFields.ZIPCODE.toString());
            return new Person(name, dateOfBirth, address, city, zipCode);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
