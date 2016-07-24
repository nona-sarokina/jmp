package module3.task1.deserializers;

import module3.task1.beans.Person;
import module3.task1.serializers.ISerializer;

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
            final String name = object.getString("NAME");
            final Date dateOfBirth = object.getDate("DATEOFBIRTH");
            final String address = object.getString("ADDRESS");
            final String city = object.getString("CITY");
            final int zipCode = object.getInt("ZIPCODE");
            return new Person(name, dateOfBirth, address, city, zipCode);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
