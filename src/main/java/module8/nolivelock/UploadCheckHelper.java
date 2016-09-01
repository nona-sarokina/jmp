package module8.nolivelock;

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

    public static synchronized void log() {
        final UploadChecker checker = getCurrentUploadChecker();
        if (!checker.isUploaded()) {
            LOGGER.debug("uploaded " + checker.calculateLoadedPercentage() + "% of " +
                    checker.getFile().getName() + " (" + Thread.currentThread().getName() + ")");
        }
    }

    public static synchronized UploadChecker getCurrentUploadChecker() {
        return currentUploadChecker;
    }

    public static synchronized void setCurrentUploadChecker(UploadChecker uploadChecker) {
        currentUploadChecker = uploadChecker;
    }

    public static UploadChecker getUploadChecker(Path sourcePath, Path destinationPath) {
        UploadChecker uploadChecker = new UploadChecker(sourcePath, destinationPath);
        return uploadChecker;
    }

}
