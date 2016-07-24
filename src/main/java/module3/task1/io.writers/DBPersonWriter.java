package module3.task1.io.writers;

import module3.task1.beans.Person;
import module3.task1.db.Connections;
import module3.task1.serializers.DBSerializer;
import module3.task1.serializers.ISerializer;

import java.sql.*;

/**
 * Created by user on 23.07.2016.
 */

public class DBPersonWriter implements IPersonWriter {
    public static final String INSERT_INTO_PERSONS_QUERY_PREFIX = "INSERT INTO Persons (name, dateOfBirth, address, city, zipcode) VALUES ";
    ISerializer serializer = new DBSerializer();

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
