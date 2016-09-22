package module10.task2.entities;

import java.util.Date;

/**
 * Created by user on 17.09.2016.
 */
public class Friendship {
    private int userId1;
    private int userId2;
    private Date time;

    public Friendship() {
    }

    public int getUserId1() {
        return userId1;
    }

    public void setUserId1(int userId1) {
        this.userId1 = userId1;
    }

    public int getUserId2() {
        return userId2;
    }

    public void setUserId2(int userId2) {
        this.userId2 = userId2;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Friendship{");
        sb.append("time=").append(time);
        sb.append('}');
        return sb.toString();
    }


}
