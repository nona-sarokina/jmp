package module10.task2.dao.users;

import module10.task2.dao.base.AbstractDAOImpl;
import module10.task2.entities.AdvancedUser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by user on 21.09.2016.
 */
public class AdvancedDAOImpl extends AbstractDAOImpl<AdvancedUser>{
    protected static final String TABLE_NAME = "users";
    protected static final String SELECT = "SELECT DISTINCT USERS.ID as id, USERS.NAME as name, USERS.SURNAME as surname, friendsCount, likescount\n" +
            "FROM USERS,\n" +
            "  (SELECT\n" +
            "     COUNT(*) AS friendsCount,\n" +
            "     USERS.ID AS id\n" +
            "   FROM USERS, FRIENDSHIPS AS f1, FRIENDSHIPS AS f2\n" +
            "   WHERE users.ID = f1.USERID1 AND f1.USERID2 = f2.USERID1 AND f2.USERID2 = users.ID\n" +
            "   GROUP BY ID) AS friendshipsTable,\n" +
            "  (SELECT\n" +
            "     COUNT(*) AS likescount,\n" +
            "     USERS.ID AS id\n" +
            "   FROM USERS, POSTS, LIKES\n" +
            "   WHERE USERS.ID = POSTS.USERID AND POSTS.ID = LIKES.POSTID AND MONTH(LIKES.TIMEST) = 3 AND YEAR(LIKES.TIMEST) = 2015\n" +
            "   GROUP BY USERS.ID) AS likesTable\n" +
            "\n" +
            "WHERE USERS.Id = friendshipsTable.id AND USERS.ID = likesTable.id AND friendsCount > 100 AND likescount > 10;\n" +
            "\n";

    protected enum ColumnNames {
        id,
        name,
        surname,
        friendsCount,
        likescount
    }

    @Override
    protected String getSelectQuery() {
        return SELECT;
    }

    @Override
    protected String getCreateQuery() {
        return null;
    }

    @Override
    protected String getInsertQuery() {
        return null;
    }

    @Override
    protected String getTableName() {
        return null;
    }

    @Override
    protected AdvancedUser parseFromResultSet(ResultSet resultSet) throws SQLException {
        AdvancedUser user = new AdvancedUser();
        user.setId(resultSet.getInt(ColumnNames.id.toString()));
        user.setName(resultSet.getString(ColumnNames.name.toString()));
        user.setSurname(resultSet.getString(ColumnNames.surname.toString()));
        user.setLikesCount(resultSet.getInt(ColumnNames.likescount.toString()));
        user.setFriendCount(resultSet.getInt(ColumnNames.friendsCount.toString()));

        return user;
    }

    @Override
    protected void processInsertStatement(PreparedStatement statement, AdvancedUser itemToInsert) throws SQLException {

    }
}
