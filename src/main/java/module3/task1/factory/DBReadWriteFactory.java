package module3.task1.factory;

import module3.task1.managers.IPersonReadWriteManager;
import module3.task1.managers.PersonDBReadWriteManager;

/**
 * Created by user on 23.07.2016.
 */
public class DBReadWriteFactory implements AbstractReadWriteFactory {

    @Override
    public IPersonReadWriteManager getReadWriteManager() {
        return new PersonDBReadWriteManager();
    }
}
