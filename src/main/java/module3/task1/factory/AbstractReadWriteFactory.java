package module3.task1.factory;

import module3.task1.managers.IPersonReadWriteManager;

/**
 * Created by user on 23.07.2016.
 */
public interface AbstractReadWriteFactory {
    IPersonReadWriteManager getReadWriteManager();
 }
