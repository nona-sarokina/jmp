package module3.task1.serializers;

import module3.task1.beans.DBRecord;
import module3.task1.beans.Person;

/**
 * Created by user on 24.07.2016.
 */
public class DBSerializer implements ISerializer<Person, DBRecord> {

    @Override
    public DBRecord serialize(Person person) {
        DBRecord record = new DBRecord(person.getName(), person.getDateOfBirth(), person.getAddress(), person.getCity(), person.getZipCode());
        return record;
    }
}
