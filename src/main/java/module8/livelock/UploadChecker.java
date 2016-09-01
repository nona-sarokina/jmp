package module8.livelock;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributeView;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by user on 30.08.2016.
 */
public class UploadChecker implements Callable<Boolean> {
    private volatile File file;
    private long finalSize;

    private volatile boolean isLogging = false;


    public UploadChecker(Path sourcePath, Path destinationPath) {
        this.file = destinationPath.toFile();
        BasicFileAttributeView attributeView = Files.getFileAttributeView(sourcePath, BasicFileAttributeView.class);
        try {
            this.finalSize = attributeView.readAttributes().size();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Boolean call() throws Exception {
        if (file.length() < finalSize) {
            isLogging = true;
            //trying to find out is there any other upload checker that ready to log and give the ability to log first to it
            UploadChecker anotherActiveChecker = UploadCheckHelper.getCheckerList().stream()
                    .filter(u -> u.isLogging())
                    .findFirst()
                    .orElse(this);
            if (this != anotherActiveChecker) {
                UploadCheckHelper.setCurrentUploadChecker(anotherActiveChecker);
            }
            while (UploadCheckHelper.getCurrentUploadChecker() != this) {
                System.out.println("I'm " + Thread.currentThread().getName() + " and I'm lazy. Let me get some sleep while other uploader works ");
                TimeUnit.MILLISECONDS.sleep(100);
            }
            //common resource
            UploadCheckHelper.log();
        }
        isLogging = false;
        return true;
    }

    public int calculateLoadedPercentage() {
        return (int) (file.length() * 100 / finalSize);
    }

    public boolean isLogging() {
        return isLogging;
    }

    public File getFile() {
        return file;
    }
}
