package module3.task1.serializers;

import module3.task1.beans.Mode;
import module3.task1.beans.Person;
import module3.task1.utils.PersonToStringUtils;

/**
 * Created by user on 24.07.2016.
 */
public class FileSerializer implements ISerializer<Person, String> {
    @Override
    public String serialize(Person person) {
        return PersonToStringUtils.personToString(person, Mode.FILE);
    }
}
