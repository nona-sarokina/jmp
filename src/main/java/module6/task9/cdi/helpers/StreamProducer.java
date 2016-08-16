package module6.task9.cdi.helpers;

import module6.task9.cdi.Runner;

import javax.enterprise.inject.Produces;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StreamProducer {
    public static final String FILENAME = "persons.txt";
    @Produces
    public OutputStream getOutputStream() throws URISyntaxException, FileNotFoundException {
        final URI uri = StreamProducer.class.getClassLoader().getResource(FILENAME).toURI();
        Path path = Paths.get(uri);
        return new FileOutputStream(path.toString(), true);
    }

    @Produces
    public InputStream getInputStream() throws URISyntaxException, FileNotFoundException {
        return Runner.class.getClassLoader().getResourceAsStream(FILENAME);
    }

}
