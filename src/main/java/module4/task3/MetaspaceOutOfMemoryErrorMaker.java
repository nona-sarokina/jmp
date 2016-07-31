package module4.task3;


import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by user on 30.07.2016.
 */
/*
    java.lang.OutOfMemoryError: Metaspace. Load classes continuously and make them stay in memory.
    -XX:CompressedClassSpaceSize=100m
*/
public class MetaspaceOutOfMemoryErrorMaker {
    private static final int SIZE = 1_000_000;
    private static final Set<Object> instances = new HashSet<>(SIZE);

    public static void main(String[] args)  {

        Class<?> thisClass = MetaspaceOutOfMemoryErrorMaker.class;
        URL[] urls = {thisClass.getProtectionDomain().getCodeSource().getLocation()};

        for (int i = 0; i < SIZE; i++) {
            try (URLClassLoader classLoader = new URLClassLoader(urls, null)) {
                Class cla = classLoader.loadClass(thisClass.getName());
                instances.add(cla.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
   }
}


