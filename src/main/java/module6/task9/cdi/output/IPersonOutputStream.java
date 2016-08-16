package module6.task9.cdi.output;

import module3.task1.beans.Person;

import java.io.IOException;

public interface IPersonOutputStream {
    void writePerson(Person person);

    void close() throws IOException;
}
