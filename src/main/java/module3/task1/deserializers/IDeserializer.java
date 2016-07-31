package module3.task1.deserializers;

/**
 * Created by user on 23.07.2016.
 */
public interface IDeserializer <F, T>{
    T deserialize(F object);
}
