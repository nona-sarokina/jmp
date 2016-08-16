package module6.task9.cdi.output;

import module3.task1.beans.Person;
import module3.task1.serializers.FileSerializer;
import module3.task1.serializers.ISerializer;

import javax.inject.Inject;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class PersonOutputStream implements IPersonOutputStream {
    @Inject
    protected OutputStream stream;
    private OutputStreamWriter writer = null;
    ISerializer<Person, String> serializer = new FileSerializer();

    @Override
    public void writePerson(Person person) {
        if (writer == null) {
            writer = new OutputStreamWriter(stream);
        }
        try {
            writer.write(serializer.serialize(person) + "\n");
            writer.flush();
            System.out.println("Wrote " + person);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws IOException {
        writer.close();
        writer = null;
    }

}
