package module8.nolivelock;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributeView;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by user on 30.08.2016.
 */
public class UploadChecker implements Runnable {
    private volatile File file;
    private long finalSize;
    private final static Lock LOCK = new ReentrantLock();
    private volatile boolean isUploaded = false;


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
    public void run() {
        if (file.length() <= finalSize) {
            LOCK.lock();
            try {
                UploadCheckHelper.setCurrentUploadChecker(this);
                UploadCheckHelper.log();
            } finally {
                LOCK.unlock();
            }
        } else {
            isUploaded = true;
        }
    }

    public int calculateLoadedPercentage() {
        return (int) (file.length() * 100 / finalSize);
    }

    public File getFile() {
        return file;
    }

    public boolean isUploaded() {
        return isUploaded;
    }
}
