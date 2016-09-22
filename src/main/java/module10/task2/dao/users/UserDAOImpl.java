package module10.task2.dao.users;

import module10.task2.dao.base.AbstractDAOImpl;
import module10.task2.entities.User;

import java.sql.*;

/**
 * Created by user on 16.09.2016.
 */
public class UserDAOImpl extends AbstractDAOImpl<User> {
    protected static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS  users (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50) NOT NULL, surname VARCHAR(50) NOT NULL, birthdate DATE NOT NULL,  PRIMARY KEY (id));";
    protected static final String INSERT_QUERY = "INSERT INTO users (NAME, SURNAME, BIRTHDATE) VALUES (?, ?, ?)";
    protected static final String SELECT_QUERY = "SELECT * FROM users";
    protected static final String TABLE_NAME = "users";
    protected enum ColumnNames {
        id,
        name,
        surname,
        birthdate
    }

    public UserDAOImpl() {

    }


    @Override
    protected String getCreateQuery() {
        return CREATE_TABLE_QUERY;
    }

    @Override
    protected String getInsertQuery() {
        return INSERT_QUERY;
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected String getSelectQuery() {
        return SELECT_QUERY;
    }

    @Override
    protected User parseFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(ColumnNames.id.toString()));
        user.setName(resultSet.getString(ColumnNames.name.toString()));
        user.setSurname(resultSet.getString(ColumnNames.surname.toString()));
        user.setBirthdate(resultSet.getDate(ColumnNames.birthdate.toString()));
        return user;
    }


    @Override
    protected void processInsertStatement(PreparedStatement statement, User itemToInsert) throws SQLException {
        statement.setString(1, itemToInsert.getName());
        statement.setString(2, itemToInsert.getSurname());
        statement.setDate(3, new Date(itemToInsert.getBirthdate().getTime()));
    }

}
