package module3.task1.serializers;

/**
 * Created by user on 23.07.2016.
 */
public interface ISerializer<F,T> {
    T serialize(F person);
}
