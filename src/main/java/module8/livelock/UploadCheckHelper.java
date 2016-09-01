package module8.livelock;

import org.apache.log4j.Logger;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 31.08.2016.
 */
public class UploadCheckHelper {

    private final static Logger LOGGER = Logger.getLogger(UploadCheckHelper.class);
    private static UploadChecker currentUploadChecker;
    private static List<UploadChecker> checkerList = new ArrayList();

    public static synchronized void log() {
        final UploadChecker checker = getCurrentUploadChecker();
        LOGGER.debug("uploaded " + checker.calculateLoadedPercentage() + "% of " + checker.getFile().getName() + " (" + Thread.currentThread().getName() +  ")");
    }

    public static synchronized UploadChecker getCurrentUploadChecker() {
        return currentUploadChecker;
    }

    public static synchronized void setCurrentUploadChecker(UploadChecker uploadChecker) {
        System.out.println("setting " + Thread.currentThread().getName() + " " +  checkerList.stream().filter(u -> u.isLogging()).toArray().length);
        currentUploadChecker = uploadChecker;
    }

    private static void registerUploader(UploadChecker uploader) {
        checkerList.add(uploader);
    }

    public static UploadChecker getUploadChecker(Path sourcePath, Path destinationPath) {
        UploadChecker uploadChecker = new UploadChecker(sourcePath, destinationPath);
        UploadCheckHelper.registerUploader(uploadChecker);
        UploadCheckHelper.setCurrentUploadChecker(uploadChecker);
        return uploadChecker;
    }

    public static List<UploadChecker> getCheckerList() {
        return checkerList;
    }



}
