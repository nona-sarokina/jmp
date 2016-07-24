package module3.task1.writers;

import module3.task1.beans.Mode;
import module3.task1.beans.Person;
import module3.task1.serializers.DBSerializer;
import module3.task1.serializers.FileSerializer;
import module3.task1.serializers.ISerializer;
import module3.task1.utils.PersonToStringUtils;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by user on 23.07.2016.
 */
public class FilePersonWriter implements IPersonWriter {
    ISerializer<Person, String>  serializer = new FileSerializer();

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
