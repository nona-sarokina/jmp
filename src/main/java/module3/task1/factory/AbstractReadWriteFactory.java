package module3.task1.factory;

import module3.task1.io.readers.IPersonReader;
import module3.task1.io.writers.IPersonWriter;

/**
 * Created by user on 23.07.2016.
 */
public interface AbstractReadWriteFactory {
    IPersonReader getReader();
    IPersonWriter getWriter();
 }
