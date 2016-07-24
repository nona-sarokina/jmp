package module3.task1.io.readers;

import module3.task1.beans.Person;
import module3.task1.deserializers.FileDeserializer;
import module3.task1.deserializers.IDeserializer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 23.07.2016.
 */

public class FilePersonReader implements IPersonReader {
    IDeserializer<String, Person> deserializer = new FileDeserializer();

    @Override
    public List<Person> readPersons() {
        List<Person> persons = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("persons.txt")))) {
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                persons.add(deserializer.deserialize(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return persons;
    }

    @Override
    public Person readPerson(String name) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("persons.txt")))) {
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {

                Person person = deserializer.deserialize(line);
                System.out.println(person);
                System.out.println(name);
                System.out.println(name.equals(person.getName()));
                if (name.equals(person.getName())) {
                    return person;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
