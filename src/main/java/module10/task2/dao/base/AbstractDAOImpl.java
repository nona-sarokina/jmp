package module10.task2.dao.base;

import module10.task2.Connections;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 17.09.2016.
 */
public abstract class AbstractDAOImpl<T> implements DAO<T> {
    @Override
    public void addItem(T item) {
        try (Connection connection = Connections.INSTANCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(getInsertQuery())) {
                processInsertStatement(statement, item);
                statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addItems(List<T> items) {
        try (Connection connection = Connections.INSTANCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(getInsertQuery())) {
                for (T item : items) {
                    processInsertStatement(statement, item);
                    statement.addBatch();
                }
                statement.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T getItem(int id) {
        return null;
    }

    @Override
    public void deleteItem(int id) {

    }

    @Override
    public void updateItem(T item) {

    }

    @Override
    public List<T> getAllItems() {
        List<T> result = new ArrayList<>();

        try (Connection connection = Connections.INSTANCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(getSelectQuery())) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    result.add(parseFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }


    @Override
    public void createTable() throws SQLException {
        try (Connection connection = Connections.INSTANCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(getCreateQuery())) {
                statement.execute();
            }
        }
    }

    protected abstract String getSelectQuery();

    protected abstract String getCreateQuery();

    protected abstract String getInsertQuery();

    protected abstract T parseFromResultSet(ResultSet resultSet) throws SQLException;

    protected abstract void processInsertStatement(PreparedStatement statement, T itemToInsert) throws SQLException;

    //protected abstract void executeStatement();
    //protected abstract getConditionSelector();

}
