package module3.task1.factory;

import module3.task1.managers.IPersonReadWriteManager;
import module3.task1.managers.PersonFileReadWriteManager;

/**
 * Created by user on 23.07.2016.
 */
public class FileReadWriteFactory implements AbstractReadWriteFactory {

    @Override
    public IPersonReadWriteManager getReadWriteManager() {
        return new PersonFileReadWriteManager();
    }
}
