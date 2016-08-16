package module6.task9.cdi.input;

import module3.task1.beans.Person;
import module3.task1.deserializers.FileDeserializer;
import module3.task1.deserializers.IDeserializer;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PersonInputStream implements IPersonInputStream {
    @Inject
    InputStream stream;
    IDeserializer<String, Person> deserializer = new FileDeserializer();
    private BufferedReader bufferedReader;

    public Person readPerson(String name) {
        if (null == bufferedReader) {
            bufferedReader = new BufferedReader(new InputStreamReader(stream));
        }
        return bufferedReader.lines()
                .map(line -> deserializer.deserialize(line))
                .filter(e -> e.getName().equals(name))
                .findFirst().orElseGet(null);
    }

    @Override
    public void close() throws IOException {
        bufferedReader.close();
        bufferedReader = null;
    }
}
