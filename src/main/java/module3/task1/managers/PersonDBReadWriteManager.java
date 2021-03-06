package module3.task1.managers;

import module3.task1.beans.Person;
import module3.task1.db.Connections;
import module3.task1.deserializers.DBDeserializer;
import module3.task1.deserializers.IDeserializer;
import module3.task1.serializers.DBSerializer;
import module3.task1.serializers.ISerializer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nona_Sarokina on 7/26/2016.
 */
public class PersonDBReadWriteManager implements IPersonReadWriteManager {
    public static final String SELECT_FROM_PERSONS = "Select * from Persons";
    public static final String SELECT_FROM_PERSONS_WHERE_NAME = "Select * from Persons where name=?";
    public static final String INSERT_INTO_PERSONS_QUERY_PREFIX = "INSERT INTO Persons (name, dateOfBirth, address, city, zipcode) VALUES ";
    ISerializer<Person, String> serializer = new DBSerializer();
    IDeserializer<ResultSet, Person> deserializer = new DBDeserializer();

    @Override
    public List<Person> readPersons() {
        List<Person> persons = new ArrayList<Person>();
        try (Connection connection = Connections.INSTANCE.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SELECT_FROM_PERSONS)) {

            while (rs.next()) {
                persons.add(deserializer.deserialize(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return persons;
        }
    }

    @Override
    public Person readPerson(String name) {
        try (Connection connection = Connections.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_FROM_PERSONS_WHERE_NAME)) {
            statement.setString(1, name);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return deserializer.deserialize(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void writePerson(Person person) {
        try (Connection connection = Connections.INSTANCE.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate((INSERT_INTO_PERSONS_QUERY_PREFIX + serializer.serialize(person)));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
