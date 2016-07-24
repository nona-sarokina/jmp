package module3.task1.readers;

import module3.task1.beans.Person;

import java.util.List;

/**
 * Created by user on 23.07.2016.
 */
public interface IPersonReader {
    List<Person> readPersons();

    Person readPerson(String name);
}
