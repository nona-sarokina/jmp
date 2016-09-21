package module10.task2;

import org.apache.commons.dbcp.BasicDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by user on 20.09.2016.
 */
public enum Connections {
    INSTANCE;
    private static final String MODULE10_URL = "module10.url";
    private static final String PASSWORD = "password";
    private static final String USER_NAME = "user_name";
    private static final String CLASS_NAME = "class_name";
    private static final String DB_PROPERTIES_PROPERTIES = "db_properties.properties";
    private BasicDataSource source = new BasicDataSource();

    Connections() {
        Properties properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(DB_PROPERTIES_PROPERTIES)) {
            properties.load(inputStream);
            source.setDriverClassName(properties.getProperty(CLASS_NAME));
            source.setUsername(properties.getProperty(USER_NAME));
            source.setPassword(properties.getProperty(PASSWORD));
            source.setUrl(properties.getProperty(MODULE10_URL));
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
