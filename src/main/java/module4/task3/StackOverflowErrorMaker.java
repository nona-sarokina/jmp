package module4.task3;

/**
 * Created by user on 29.07.2016.
 */
/*
    java.lang.StackOverflowError. Do not use recursive methods.
    -XX:ThreadStackSize=1 -XX:+HeapDumpOnOutOfMemoryError  -XX:HeapDumpPath=d:\dumps  -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
*/
public class StackOverflowErrorMaker {

    public static void main(String[] args) {
        new A();
    }

    private static class A {
        final private B b = new B();
    }

    private static class B {
        final private A b = new A();
    }
}
