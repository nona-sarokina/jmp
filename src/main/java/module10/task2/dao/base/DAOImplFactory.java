package module10.task2.dao.base;

import module10.task2.dao.friendships.FriendshipDAOImpl;
import module10.task2.dao.friendships.FriendshipDAOType;
import module10.task2.dao.likes.LikeDAOImpl;
import module10.task2.dao.likes.LikeDAOType;
import module10.task2.dao.posts.PostDAOImpl;
import module10.task2.dao.posts.PostDAOType;
import module10.task2.dao.users.AdvancedDAOImpl;
import module10.task2.dao.users.AdvancedUserDAOType;
import module10.task2.dao.users.UserDAOImpl;
import module10.task2.dao.users.UserDAOType;
import module10.task2.entities.*;

import javax.enterprise.inject.Produces;

/**
 * Created by user on 17.09.2016.
 */
public class DAOImplFactory {
    @Produces
    @LikeDAOType
    public DAO<Like> createLikeDAO() {
        return new LikeDAOImpl();
    }

    @Produces
    @UserDAOType
    public DAO<User> createUserDAO() {
        return new UserDAOImpl();
    }

    @Produces
    @PostDAOType
    public DAO<Post> createPostDAO() {
        return new PostDAOImpl();
    }

    @Produces
    @FriendshipDAOType
    public DAO<Friendship> createFriendshipDAO() {
        return new FriendshipDAOImpl();
    }

    @Produces
    @AdvancedUserDAOType
    public DAO<AdvancedUser> createAdvancedUserDAOType() {
        return new AdvancedDAOImpl();
    }
}
