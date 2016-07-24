package module3.task1.deserializers;

import module3.task1.beans.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by user on 23.07.2016.
 */
public interface IDeserializer <F, T>{
    T deserialize(F object);
}
