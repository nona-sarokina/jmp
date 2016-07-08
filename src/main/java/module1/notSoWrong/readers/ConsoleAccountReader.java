package module1.notSoWrong.readers;

import module1.notSoWrong.account.IAccountOperator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 08.07.2016.
 */
public class ConsoleAccountReader extends AbstractAccountReader {
    public ConsoleAccountReader() {
        super(System.in);
    }

    @Override
    public List<IAccountOperator> read() {
        final List<IAccountOperator> accounts = new ArrayList<>();
        final int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            accounts.add(getAccount());
        }
        return accounts;
    }
}
