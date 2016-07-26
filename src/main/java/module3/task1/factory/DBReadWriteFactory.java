package module3.task1.factory;

import module3.task1.managers.IPersonReadWriteManager;
import module3.task1.managers.PersonDBReadWriteManager;
import module3.task1.readers.DBPersonReader;
import module3.task1.writers.DBPersonWriter;
import module3.task1.readers.IPersonReader;
import module3.task1.writers.IPersonWriter;

/**
 * Created by user on 23.07.2016.
 */
public class DBReadWriteFactory implements AbstractReadWriteFactory {

    @Override
    public IPersonReadWriteManager getReadWriteManager() {
        return new PersonDBReadWriteManager();
    }
}
