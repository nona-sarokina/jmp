package module10.task2.dao.friendships;

import module10.task2.dao.base.AbstractDAOImpl;
import module10.task2.entities.Friendship;

import java.sql.*;

/**
 * Created by user on 17.09.2016.
 */
public class FriendshipDAOImpl extends AbstractDAOImpl<Friendship> {
    protected static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS friendships ( userID1 INT       NOT NULL, userID2 INT       NOT NULL, timest  TIMESTAMP NOT NULL, CONSTRAINT friendships_userID1_userID2_pk PRIMARY KEY (userID1, userID2), CONSTRAINT FRIENDSHIPS_USERS1__FK FOREIGN KEY (USERID1) REFERENCES USERS (ID), CONSTRAINT FRIENDSHIPS_USERS2__FK FOREIGN KEY (USERID2) REFERENCES USERS (ID) );";
    protected static final String SELECT_QUERY = "SELECT * FROM friendships";
    protected static final String INSERT_QUERY = "INSERT INTO friendships (userId1, userId2, timest) VALUES (?, ?, ?)";
    protected static final String TABLE_NAME = "friendships";

    protected enum ColumnNames {
        userId1,
        userId2,
        timest
    }

    public FriendshipDAOImpl() {
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
    protected Friendship parseFromResultSet(ResultSet resultSet) throws SQLException {
        Friendship friendship = new Friendship();
        friendship.setUserId1(resultSet.getInt(ColumnNames.userId1.toString()));
        friendship.setUserId2(resultSet.getInt(ColumnNames.userId2.toString()));
        friendship.setTime(resultSet.getDate(ColumnNames.timest.toString()));
        return friendship;
    }


    @Override
    protected void processInsertStatement(PreparedStatement statement, Friendship itemToInsert) throws SQLException {
        statement.setInt(1, itemToInsert.getUserId1());
        statement.setInt(2, itemToInsert.getUserId2());
        statement.setDate(3, new Date(itemToInsert.getTime().getTime()));
    }
}
