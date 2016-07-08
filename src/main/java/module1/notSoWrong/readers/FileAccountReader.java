package module1.notSoWrong.readers;

import module1.notSoWrong.account.IAccountOperator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by user on 08.07.2016.
 */
public class FileAccountReader extends AbstractAccountReader {
    private String path;

    public FileAccountReader(String path) throws FileNotFoundException {
        this.path = path;
        this.inputStream = new FileInputStream(new File(path));
        setScanner(getInputStream());
    }

    private void setScanner(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    @Override
    public List<IAccountOperator> read() {
        final List<IAccountOperator> accounts = new ArrayList<>();
        while (scanner.hasNextLine()) {
            accounts.add(getAccount());
        }
        return accounts;
    }
}
