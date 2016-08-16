package module6.task9.cdi.input;

import module3.task1.beans.Person;
import module3.task1.deserializers.FileDeserializer;
import module3.task1.deserializers.IDeserializer;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class PersonInputStream implements IPersonInputStream {
    @Inject
    InputStream stream;
    IDeserializer<String, Person> deserializer = new FileDeserializer();
    private BufferedReader bufferedReader;
    Map<String, Person> personsCache = new HashMap<>();
    public Person readPerson(String name) {
        if (null == bufferedReader) {
            bufferedReader = new BufferedReader(new InputStreamReader(stream));
            personsCache = bufferedReader.lines()
                    .map(line -> deserializer.deserialize(line))
                    .distinct()
                    .collect(Collectors.toMap(Person::getName, p -> p, (p1, p2) -> p1));
        }

        return personsCache.get(name);
    }

    @Override
    public void close() throws IOException {
        personsCache.clear();
        bufferedReader.close();
        bufferedReader = null;
    }
}
