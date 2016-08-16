package module6.task9.cdi.input;

import module3.task1.beans.Person;

import java.io.IOException;

public interface IPersonInputStream {
    Person readPerson(String name);

    void close() throws IOException;
}
