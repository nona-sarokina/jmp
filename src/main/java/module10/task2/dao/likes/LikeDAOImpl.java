package module10.task2.dao.likes;

import module10.task2.dao.base.AbstractDAOImpl;
import module10.task2.entities.Like;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by user on 17.09.2016.
 */
public class LikeDAOImpl extends AbstractDAOImpl<Like> {
    private static final String CREATE_TABLE_QUERY = "CREATE TABLE  IF NOT EXISTS likes ( postID INT NOT NULL, userID INT NOT NULL, timest TIMESTAMP NOT NULL, CONSTRAINT likes_postID_userID_pk PRIMARY KEY (postID, userID), CONSTRAINT likes_posts__fk FOREIGN KEY (postID) REFERENCES posts (id), CONSTRAINT likes_users__fk FOREIGN KEY (userID) REFERENCES users (id) );";
    private static final String INSERT_QUERY = "INSERT INTO likes (postId, userId, timest) VALUES (?, ?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM likes";

    private enum ColumnNames {
        postId,
        userId,
        timest
    }

    public LikeDAOImpl() {

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
    protected Like parseFromResultSet(ResultSet resultSet) throws SQLException {
        Like like = new Like();
        like.setPostId(resultSet.getInt(ColumnNames.postId.toString()));
        like.setUserId(resultSet.getInt(ColumnNames.userId.toString()));
        like.setTime(resultSet.getDate(ColumnNames.timest.toString()));
        return like;
    }

    @Override
    protected void processInsertStatement(PreparedStatement statement, Like itemToInsert) throws SQLException {
        statement.setInt(1, itemToInsert.getPostId());
        statement.setInt(2, itemToInsert.getUserId());
        statement.setDate(3, new Date(itemToInsert.getTime().getTime()));
    }
}
