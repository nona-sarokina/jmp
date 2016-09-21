package module10.task2.dao.posts;

import module10.task2.dao.base.AbstractDAOImpl;
import module10.task2.entities.Post;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by user on 17.09.2016.
 */
public class PostDAOImpl extends AbstractDAOImpl<Post> {
    private static final String CREATE_TABLE_QUERY = "CREATE TABLE  IF NOT EXISTS posts ( id INT AUTO_INCREMENT PRIMARY KEY, userID VARCHAR(50) NOT NULL, text LONGTEXT NOT NULL, timest TIMESTAMP NOT NULL, CONSTRAINT posts_users__fk FOREIGN KEY (userID) REFERENCES Users (id));";
    private static final String INSERT_QUERY = "INSERT INTO posts (userId, text,  timest) VALUES (?, ?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM posts";

    private enum ColumnNames {
        id,
        userId,
        text,
        timest
    }

    public PostDAOImpl() {
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
    protected String getSelectQuery() {
        return SELECT_QUERY;
    }


    @Override
    protected Post parseFromResultSet(ResultSet resultSet) throws SQLException {
        Post post = new Post();

        post.setId(resultSet.getInt(ColumnNames.id.toString()));
        post.setUserId(resultSet.getInt(ColumnNames.userId.toString()));
        post.setText(resultSet.getString(ColumnNames.text.toString()));
        post.setTime(resultSet.getDate(ColumnNames.timest.toString()));

        return post;
    }

    @Override
    protected void processInsertStatement(PreparedStatement statement, Post itemToInsert) throws SQLException {
        statement.setInt(1, itemToInsert.getUserId());
        statement.setString(2, itemToInsert.getText());
        statement.setDate(3, new Date(itemToInsert.getTime().getTime()));
    }


}
