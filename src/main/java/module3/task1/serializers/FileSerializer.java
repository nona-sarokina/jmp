package module3.task1.serializers;

import module3.task1.beans.FileRecord;
import module3.task1.beans.Person;

/**
 * Created by user on 24.07.2016.
 */
public class FileSerializer implements ISerializer<Person, FileRecord> {
    @Override
    public FileRecord serialize(Person person) {
        return (FileRecord) person;
    }
}
