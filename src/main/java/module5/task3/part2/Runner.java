package module5.task3.part2;

import module5.task3.part1.CustomClassLoader;

import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * Created by user on 06.08.2016.
 */
public class Runner {
    static Scanner s = new Scanner(System.in);
    static ClassLoader customClassLoader;

    public static void main(String[] args) throws ClassNotFoundException {
        String classPath = "";
        String answer = "";
        CustomClassLoader customClassLoader = new ExtendedCustomClassloader(module5.task3.part1.Runner.class.getClassLoader());
        do {
            System.out.println("Please enter a classpath");
            classPath = s.nextLine();
            customClassLoader.setClassPath(classPath);

            try {
                System.out.println("Status: progress");
                Class<?> result = customClassLoader.loadClass("module5.task3.part1.Semaphore");
                Method main = result.getDeclaredMethod("lever", null);
                main.invoke(result.newInstance());
                System.out.println("Status: success, loaded by " + result.getClassLoader());
            } catch (ClassNotFoundException e) {
                System.out.println("Status: error");
            } catch (Exception e) {
                System.out.println("Status: some exception");
            }
            System.out.println("Do you want to load more classes? (Y/N)");
            answer = s.nextLine();
        } while ("Y".equals(answer.toUpperCase()));
    }

}
