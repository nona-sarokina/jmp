package module10.task1.copy;

import module10.task1.beans.TableData;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by user on 25.09.2016.
 */
public interface ITableCopier {

    void copy(Order order);

    List<TableData> getTableData();

    void insert(List<TableData> data, Order order);

    void addConstrains(List<TableData> data);


    Connection getSourceConnection(String url) throws SQLException;
}
