package module3.task1.managers;

import module3.task1.beans.Person;
import module3.task1.deserializers.FileDeserializer;
import module3.task1.deserializers.IDeserializer;
import module3.task1.serializers.FileSerializer;
import module3.task1.serializers.ISerializer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nona_Sarokina on 7/26/2016.
 */
public class PersonFileReadWriteManager implements IPersonReadWriteManager{
    IDeserializer<String, Person> deserializer = new FileDeserializer();
    ISerializer<Person, String> serializer = new FileSerializer();

    @Override
    public List<Person> readPersons() {
        List<Person> persons = new ArrayList<>();

        try (BufferedReader bufferedReader =
                     new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("persons.txt")))) {
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
        try (BufferedReader bufferedReader =
                     new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("persons.txt")))) {
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                Person person = deserializer.deserialize(line);
                if (name.equals(person.getName())) {
                    return person;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void writePerson(Person person) {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            URI uri = classLoader.getResource("persons.txt").toURI();
            Files.write(Paths.get(uri), ("\n" + serializer.serialize(person)).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
