package module3.task1.db;

import org.apache.commons.dbcp.BasicDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


/**
 * Created by user on 23.07.2016.
 */
public enum Connections {
    INSTANCE;
    private BasicDataSource source = new BasicDataSource();

    private Connections() {
        Properties properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("db_properties.properties")) {
            properties.load(inputStream);
            source.setDriverClassName(properties.getProperty("class_name"));
            source.setUsername(properties.getProperty("user_name"));
            source.setPassword(properties.getProperty("password"));
            source.setUrl(properties.getProperty("url"));
            source.setMaxActive(5);
            source.setMaxIdle(2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = source.getConnection();
        } catch (SQLException e) {
            System.out.println("Can't get a connection by reason: " + e);
        }
        return connection;
    }
}
