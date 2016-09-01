package module8.livelock;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 * Created by user on 28.08.2016.
 */
/*
Implement erroneous class for uploading a file that will cause livelock (threads are doing useless work while waiting for the resources).

Do the following when livelock appears:
- Thread dump.
- Analyze memory consumption.
- Collect heap dump.

Decide what is helpful in identification and fix of livelock.

 */
public class Runner {

    public static final String STOP = "stop";
    public static String destination;

    private static List<Future<Long>> results = new ArrayList<>();
    private static List<ScheduledFuture<Boolean>> logResults = new ArrayList<>();

    public static void main(String[] args) {
        RuntimeMXBean runtimeBean = ManagementFactory.getRuntimeMXBean();
        String jvmName = runtimeBean.getName();
        System.out.println("JVM Name = " + jvmName);
        long pid = Long.valueOf(jvmName.split("@")[0]);
        System.out.println("PID is " + pid);
        if (args.length < 1) {
            System.out.println("Please pass server root as a parameter");
            return;
        } else {
            destination = args[0];
        }

        ScheduledExecutorService pool = Executors.newScheduledThreadPool(20);
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter path files to upload:");
        String input = scanner.nextLine();
        while (!STOP.equalsIgnoreCase(input)) {
            System.out.println(input);
            Path sourcePath = Paths.get(input);
            String fileName = sourcePath.getFileName().toString();
            File file = new File(destination + File.separator + fileName);
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error during file creation");
                System.out.println(e.getStackTrace());
            }

            final Path destinationPath = file.toPath();

            FileUploader uploader = new FileUploader(sourcePath, destinationPath);
            results.add(pool.submit(uploader));
            UploadChecker uploadChecker = UploadCheckHelper.getUploadChecker(sourcePath, destinationPath);
            pool.submit(uploadChecker);
            input = scanner.nextLine();
        }

        processResultsAndFinish(pool);

    }

    private static void processResultsAndFinish(ScheduledExecutorService pool) {

        try {
            for (Future f : results) {
                try {
                    f.get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.shutdownNow();
    }
}
