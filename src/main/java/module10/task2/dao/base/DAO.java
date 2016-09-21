package module10.task2.dao.base;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by user on 17.09.2016.
 */
public interface DAO <T> {
    void addItem(T item);
    void addItems(List<T> items);
    T getItem(int id);
    void deleteItem(int id);
    void updateItem(T item);
    List<T> getAllItems();
    void createTable() throws SQLException;
}
