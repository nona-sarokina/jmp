package module3.task1.managers;

import module3.task1.beans.Person;

import java.util.List;

/**
 * Created by Nona_Sarokina on 7/26/2016.
 */
public interface IPersonReadWriteManager {
    List<Person> readPersons();

    Person readPerson(String name);

    void writePerson(Person person);
}
