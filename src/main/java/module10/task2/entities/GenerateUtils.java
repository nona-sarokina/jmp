package module10.task2.entities;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by user on 14.09.2016.
 */

public class GenerateUtils {

    private static final String USER_GENERATOR_LINK_ONE = "http://uinames.com/api/?amount=1&region=united+states";
    private static final String USER_GENERATOR_LINK__500 = "http://uinames.com/api/?amount=500&region=united+states";
    private static final String POST_GENERATOR_LINK = "https://baconipsum.com/api/?type=all-meat&sentences=1000&start-with-lorem=1";
    private static final int INITIAL_USERS_CAPACITY = 2000;
    private static final int USER_CREATION_LOOP_ITERATIONS = INITIAL_USERS_CAPACITY / 500;



    public static List<User> generateUsers() throws IOException {
        List<User> userList = new ArrayList<User>(INITIAL_USERS_CAPACITY);
        //due to the name generator API limit - 500 max
        for (int i = 0; i < USER_CREATION_LOOP_ITERATIONS; i++) {
            userList.addAll(GenerateUtils.generateUsersData());
        }
        return userList;
    }
    private static List<User> generateUsersData() throws IOException {
        final URL url = new URL(USER_GENERATOR_LINK__500);
        ObjectMapper mapper = new ObjectMapper();
        return Arrays.stream(mapper.readValue(url, GeneratedUser[].class))
                .map(user -> convertUser(user))
                .collect(Collectors.toCollection(ArrayList::new));

    }

    private static User convertUser(GeneratedUser generatedUser) {
        User user = new User();
        user.setName(generatedUser.getName());
        user.setSurname(generatedUser.getSurname());
        user.setBirthdate(new Date());
        return user;
    }

    public static List<Friendship> generateFriendships() {
        List<Friendship> friendshipList = new ArrayList<Friendship>();
        for (int i = 1; i <= INITIAL_USERS_CAPACITY; i++) {
            for (int j = 1; j <= INITIAL_USERS_CAPACITY; j++) {
                //let's imagine that friend requests are present two, so may be a situation when one user made a request and second user didn't accept it yet

                if (i != j && (j + i) % 5 == 0) {
                    Friendship friendship = new Friendship();
                    friendship.setUserId1(i);
                    friendship.setUserId2(j);
                    friendship.setTime(GenerateUtils.getRandomDate(2010, 2016));
                    friendshipList.add(friendship);
                }
            }
        }
        return friendshipList;
    }

    public static List<Post> generatePosts() throws IOException {
        List<Post> postsList = new ArrayList<Post>();
        String textToInsert = generateText();
        for (int i = 1; i < INITIAL_USERS_CAPACITY; i++) {
            for (int j = 0; j < i % 10 + 100; j++) {
                Post post = new Post();
                post.setUserId(i);
                post.setText(textToInsert);
                post.setTime(getRandomDate(2010, 2015));
                postsList.add(post);
            }

        }
        return postsList;

    }

    public static List<Like> generateLikes() {
        List<Like> likesList = new ArrayList<Like>();
        for (int i = 1; i < INITIAL_USERS_CAPACITY; i++) {
            for (int j = 1; j < INITIAL_USERS_CAPACITY; j++) {

                if (i != j && j % 3 == 0) {
                    Like like = new Like();
                    like.setUserId(i);
                    like.setPostId(j);
                    like.setTime(GenerateUtils.getRandomDate(2015, 2016));
                    likesList.add(like);
                }
            }
        }
        return likesList;
    }

    public static Date getRandomDate(int start, int end) {
        GregorianCalendar gc = new GregorianCalendar();
        int year = getRandomNumberBetween(start, end);
        gc.set(gc.YEAR, year);
        int dayOfYear = getRandomNumberBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));
        gc.set(gc.DAY_OF_YEAR, dayOfYear);
        return new Date(gc.getTimeInMillis());
    }

    private static int getRandomNumberBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    public static String generateText() throws IOException {
        final URL url = new URL(POST_GENERATOR_LINK);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(url, String[].class)[0];
    }

    private static User generateUser() throws IOException {
        final URL url = new URL(USER_GENERATOR_LINK_ONE);
        ObjectMapper mapper = new ObjectMapper();
        return convertUser(mapper.readValue(url, GeneratedUser.class));
    }

    private static class GeneratedUser {
        String name;
        String surname;
        String gender;
        String region;

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

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("User{");
            sb.append("name='").append(name).append('\'');
            sb.append(", surname='").append(surname).append('\'');
            sb.append(", gender='").append(gender).append('\'');
            sb.append(", region='").append(region).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }




}
