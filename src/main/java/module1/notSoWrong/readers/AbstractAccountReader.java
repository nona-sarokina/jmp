package module1.notSoWrong.readers;

import module1.notSoWrong.account.AccountBuilder;
import module1.notSoWrong.account.AccountOperationType;
import module1.notSoWrong.account.IAccountOperator;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by user on 08.07.2016.
 */
public abstract class AbstractAccountReader implements IAccountReader {
    protected InputStream inputStream;

    protected Scanner scanner;

    public AbstractAccountReader(InputStream inputStream) {
        this.inputStream = inputStream;
        this.scanner = new Scanner(getInputStream());
    }

    protected AbstractAccountReader() {
    }

    protected IAccountOperator getAccount() {
        final AccountBuilder builder = new AccountBuilder();
        final double amount = scanner.nextDouble();
        final double discountRate = scanner.nextDouble();
        final int type = scanner.nextInt();
        AccountOperationType accountOperationType = AccountOperationType.getByOrder(type);

        if (AccountOperationType.SAVING.equals(accountOperationType)) {
            final double savingAmount = scanner.nextDouble();
            builder.setSavingAmount(savingAmount);
        }

        return builder.setAmount(amount)
                .setDepositRate(discountRate)
                .setOperationType(accountOperationType)
                .createAccount();
    }

    public InputStream getInputStream() {
        return this.inputStream;
    }
}
