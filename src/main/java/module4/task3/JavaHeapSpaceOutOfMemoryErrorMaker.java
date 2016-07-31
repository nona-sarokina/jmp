package module4.task3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

/**
 * Created by user on 30.07.2016.
 */
/*
    java.lang.OutOfMemoryError: Java heap space. Create big objects continuously and make them stay in memory. Do not use arrays or collections.
    -Xmx20m -XX:+HeapDumpOnOutOfMemoryError  -XX:HeapDumpPath=d:\dumps
*/
public class JavaHeapSpaceOutOfMemoryErrorMaker {

    public static void main(String[] args) throws InterruptedException, IOException {
        for (int i = 0; i < 1_000; i++) {
            new Thread(new HeapAndGCHell()).start();
        }
    }
    private static class HeapAndGCHell implements Runnable{
        private static volatile String s = "";
        @Override
        public void run() {
            try {
                BufferedReader bufferedReader1 = new BufferedReader(
                        new InputStreamReader(JavaHeapSpaceOutOfMemoryErrorMaker.class.getClassLoader().getResourceAsStream("image.jpg")));
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}

