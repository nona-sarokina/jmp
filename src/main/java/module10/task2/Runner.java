package module10.task2;

import module10.task2.dao.base.DAO;
import module10.task2.dao.friendships.FriendshipDAOType;
import module10.task2.dao.likes.LikeDAOType;
import module10.task2.dao.posts.PostDAOType;
import module10.task2.dao.users.UserDAOType;
import module10.task2.entities.*;
import org.jboss.weld.environment.se.events.ContainerInitialized;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

/**
 * JDBC Advanced Techniques. Task 2: JDBC data generation and simple reporting
 * 1. Create simple database with tables
 * Users (id, name, surname, birthdate),
 * Friendships (userid1, userid2, timestamp),
 * Posts(id, userId, text, timestamp),
 * Likes (postid, userid, timestamp).
 * 2. Fill tables with data which are make sense (> 1 000 users, > 70 000 friendships, > 300 000 likes in 2015).
 * 3. Program should print out all names (only distinct) of users
 * who has more when 100 friends and 100 likes in March 2015.
 * <p>
 * All actions (table creation, insert data and reading) should be implemented with JDBC.
 */
public class Runner {
    @Inject
    @UserDAOType
    DAO<User> userDAO;

    @Inject
    @PostDAOType
    DAO<Post> postDAO;

    @Inject
    @LikeDAOType
    DAO<Like> likeDAO;

    @Inject
    @FriendshipDAOType
    DAO<Friendship> friendshipDAO;


    public void startup(@Observes ContainerInitialized event) throws URISyntaxException {

        List<User> userList = null;
        List<Friendship> friendshipList = null;
        List<Post> postsList = null;
        List<Like> likesList = null;
        try {
            userList = GenerateUtils.generateUsers();
            friendshipList = GenerateUtils.generateFriendships();
            postsList = GenerateUtils.generatePosts();
            likesList = GenerateUtils.generateLikes();
        } catch (IOException e) {
            System.out.println("Error during data generation:\n" + e.getStackTrace());
            return;
        }


        try {
            //creation
            userDAO.createTable();
            friendshipDAO.createTable();
            postDAO.createTable();
            likeDAO.createTable();


            userDAO.addItems(userList);
            System.out.println(userDAO.getAllItems());

            friendshipDAO.addItems(friendshipList);
            System.out.println(friendshipDAO.getAllItems());

            postDAO.addItems(postsList);
            System.out.println(postDAO.getAllItems());


            likeDAO.addItems(likesList);
            System.out.println(likeDAO.getAllItems());

            return;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
