package module6.task9.cdi;

import module3.task1.beans.Person;
import module6.task9.cdi.helpers.PersonCreator;
import module6.task9.cdi.input.IPersonInputStream;
import module6.task9.cdi.output.IPersonOutputStream;
import org.jboss.weld.environment.se.events.ContainerInitialized;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

/**
 * Created by user on 15.08.2016.
 */

/*
Creational Patterns. Task 9: Decorator
Create a PersonOutputStream that implements the writePerson(Person) method and can decorate any given OutputStream.
Create a PersonOutputStream that implements the readPerson() method that returns a Person and can decorate any given InputStream.
The PersonOutputStream decorator must check if the name of the person starts with a capital letter and if it doesn't – it should update it before writing it to the destination.
The InputStreamDecorator decorator must check if the name of the person starts with a low letter and if it doesn’t – it should update it before writing it to the destination.
Write a program that uses the two decorators to write and read persons to and from a file


Main class is org.jboss.weld.environment.se.StartMain
 */
public class Runner {
    @Any
    @Inject
    IPersonOutputStream outputStream;

    @Any
    @Inject
    IPersonInputStream inputStream;

    public void startup(@Observes ContainerInitialized event) throws URISyntaxException {
        write();
        read();
    }

    private void write() throws URISyntaxException {
        try {
            Person person = PersonCreator.getPersonSample();
            outputStream.writePerson(person);
            person.setName(PersonCreator.NAME_WITH_UPPERCASE_FIRST_LETTER);
            outputStream.writePerson(person);
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void read(){
        System.out.println("Read " + inputStream.readPerson(PersonCreator.NAME_WITH_UPPERCASE_FIRST_LETTER));
        System.out.println("Read " + inputStream.readPerson(PersonCreator.NAME_WITH_LOWERCASE_FIRST_LETTER));
        System.out.println("Read " + inputStream.readPerson(PersonCreator.NAME_WITH_UPPERCASE_FIRST_LETTER));
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
