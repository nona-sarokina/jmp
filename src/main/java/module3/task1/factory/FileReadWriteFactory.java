package module3.task1.factory;

import module3.task1.io.readers.FilePersonReader;
import module3.task1.io.writers.FilePersonWriter;
import module3.task1.io.readers.IPersonReader;
import module3.task1.io.writers.IPersonWriter;

/**
 * Created by user on 23.07.2016.
 */
public class FileReadWriteFactory implements AbstractReadWriteFactory {

    @Override
    public IPersonReader getReader() {
        return new FilePersonReader();
    }

    @Override
    public IPersonWriter getWriter() {
        return new FilePersonWriter();
    }
}
