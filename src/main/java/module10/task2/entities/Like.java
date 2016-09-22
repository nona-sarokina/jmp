package module10.task2.entities;

import java.util.Date;

/**
 * Created by user on 17.09.2016.
 */
public class Like {
    private int userId;
    private int postId;
    private Date time;

    public Like() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Like(int userId, int postId, Date time) {
        this.userId = userId;
        this.postId = postId;
        this.time = time;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Like{");
        sb.append("userId=").append(userId);
        sb.append(", postId=").append(postId);
        sb.append(", time=").append(time);
        sb.append('}');
        return sb.toString();
    }
}
