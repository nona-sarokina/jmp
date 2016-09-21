/*
CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  surname VARCHAR(50) NOT NULL,
  birthdate DATE NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE friendships (userID1 INT NOT NULL,userID2 INT NOT NULL,time TIMESTAMP NOT NULL,CONSTRAINT friendships_userID1_userID2_pk PRIMARY KEY (userID1, userID2), CONSTRAINT friendships_Users1__fk FOREIGN KEY (userID1) REFERENCES Users (id), CONSTRAINT friendships_Users2__fk FOREIGN KEY (userID2) REFERENCES Users (id));

CREATE TABLE posts ( id INT AUTO_INCREMENT PRIMARY KEY, userID VARCHAR(50) NOT NULL, text LONGTEXT NOT NULL, timest TIMESTAMP NOT NULL, CONSTRAINT posts_users__fk FOREIGN KEY (userID) REFERENCES Users (id) );

CREATE TABLE likes(
  postID INT NOT NULL,
  userID INT NOT NULL,
  timest TIMESTAMP NOT NULL,
  CONSTRAINT likes_postID_userID_pk PRIMARY KEY (postID, userID),
  CONSTRAINT likes_posts__fk FOREIGN KEY (postID) REFERENCES posts (id),
  CONSTRAINT likes_users__fk FOREIGN KEY (userID) REFERENCES users (id)
);
*/

Select * from users
