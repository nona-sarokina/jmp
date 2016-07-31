package module4.task4;


import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * Created by user on 31.07.2016.
 */
/*
Write a simple program that repeatedly creates some objects structure (for example ArrayList collection) and
then release memory (set reference to null). Please note that in order to avoid JVM optimizations use some kind of randomness.

Then try to run this program using different types of GC (listed below) and monitor memory utilization using Java VisualVm.

What should we get in result in repository:

Source code of the program
README file with all GC command line configuration parameters
Screenshots from VisualVM for all types of GC running for 5 mins.


*/
public class Runner {

    static Random r = new Random();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10_000; i++) {
            int size = r.nextInt(100) + 10;
            Collection collection = createCollection(size);
            for (int j = 0; j < size; j++) {
                collection.add(new Integer(j));
            }
            TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
            collection = null;
        }
    }

    private static Collection createCollection(int size) {
        switch (size % 10) {
            case 0:
                return new ArrayList(size);
            case 1:
                return new Vector(size);
            case 2:
                return new Stack();
            case 3:
                return new LinkedList();
            case 4:
                return new HashSet(size);
            case 5:
                return new TreeSet();
            case 6:
                return new CopyOnWriteArrayList();
            case 7:
                return new ArrayDeque(size);
            case 8:
                return new LinkedHashSet(size);
            case 9:
                return new LinkedBlockingDeque(size);
            default:
                return new ArrayBlockingQueue(size);

        }

    }
}
