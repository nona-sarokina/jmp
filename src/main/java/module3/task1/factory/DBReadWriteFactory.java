package module3.task1.factory;

import module3.task1.readers.DBPersonReader;
import module3.task1.writers.DBPersonWriter;
import module3.task1.readers.IPersonReader;
import module3.task1.writers.IPersonWriter;

/**
 * Created by user on 23.07.2016.
 */
public class DBReadWriteFactory implements AbstractReadWriteFactory {
    @Override
    public IPersonReader getReader() {
        return new DBPersonReader();
    }

    @Override
    public IPersonWriter getWriter() {
        return new DBPersonWriter();
    }
}
