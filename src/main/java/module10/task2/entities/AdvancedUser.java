package module10.task2.entities;

/**
 * Created by user on 21.09.2016.
 */
public class AdvancedUser {
    private int id;
    private String name;
    private String surname;
    private int likesCount;
    private int friendCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int getFriendCount() {
        return friendCount;
    }

    public void setFriendCount(int friendCount) {
        this.friendCount = friendCount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AdvancedUser{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", likesCount=").append(likesCount);
        sb.append(", friendCount=").append(friendCount);
        sb.append('}');
        return sb.toString();
    }
}
