package module5.task3.part1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;
/**
package module5.task3.part1;

public class Semaphore {
    public void lever() {
        System.out.println("It works!");
    }
}
*/

/**
 * Created by user on 06.08.2016.
 * -classpath C:\java\MyClasses
 */
public class Runner {
    public static void main(String[] args) {
        System.out.println("Please enter a classpath");
        Scanner s = new Scanner(System.in);
        String classPath = s.nextLine();
        ClassLoader customClassLoader = new CustomClassLoader(classPath, Runner.class.getClassLoader());
        try {
            System.out.println("Status: progress");
            Class<?> result = customClassLoader.loadClass("module5.task3.part1.Semaphore");
            Method main = result.getDeclaredMethod("lever", null);
            main.invoke(result.newInstance());
            System.out.println("Status: success, loaded by " + result.getClassLoader());
        } catch (ClassNotFoundException e) {
            System.out.println("Status: error");
            return;
        } catch (Exception  e) {
            System.out.println("Status: some exception");
            return;
        }
    }
}
