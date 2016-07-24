package module3.task1.factory;

import module3.task1.writers.FilePersonWriter;
import module3.task1.writers.IPersonWriter;
import module3.task1.readers.FilePersonReader;
import module3.task1.readers.IPersonReader;

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
