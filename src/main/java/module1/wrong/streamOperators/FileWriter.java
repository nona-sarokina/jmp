package module1.wrong.streamOperators;

import module1.wrong.account.AccountOperator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;

/**
 * Created by user on 08.07.2016.
 */
public class FileWriter implements IStreamOperator {
    List<AccountOperator> data;

    public FileWriter(List<AccountOperator> data) {
        this.data = data;
    }

    @Override
    public List<AccountOperator> doStreamOperation() {
        try (PrintStream outputStream = new PrintStream(new FileOutputStream("output.txt"))) {
            System.out.println("Please check the output.txt file");
            System.setOut(outputStream);
            data.forEach(e -> e.deposite());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            System.setOut(System.out);
        }
        return this.data;
    }

    @Override
    public boolean validateStreamData() {
        return false;
    }
}
