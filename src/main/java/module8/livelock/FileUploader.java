package module8.livelock;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Callable;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * Created by user on 30.08.2016.
 */
public class FileUploader implements Callable<Long> {
    private Path sourcePath;
    private Path destinationPath;

    public FileUploader(Path sourcePath, Path destinationPath) {
        this.sourcePath = sourcePath;
        this.destinationPath = destinationPath;
    }

    @Override
    public Long call() throws Exception {
        return Files.copy(Files.newInputStream(sourcePath), destinationPath, REPLACE_EXISTING);
    }

}
