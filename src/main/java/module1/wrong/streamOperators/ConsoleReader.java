package module1.wrong.streamOperators;

import module1.wrong.account.AccountOperator;
import module1.wrong.account.AccountBuilder;
import module1.wrong.account.AccountOperationType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by user on 08.07.2016.
 */
public class ConsoleReader implements IStreamOperator {
    @Override
    public List<AccountOperator> doStreamOperation() {
        final List<AccountOperator> accounts = new ArrayList<>();
        final AccountBuilder builder = new AccountBuilder();
        try (final Scanner scanner = new Scanner(System.in)) {
            final int n = scanner.nextInt();
            for (int i = 0; i < n; i++) {
                //DRY - begin
                final double amount = scanner.nextDouble();
                final double discountRate = scanner.nextDouble();
                final int type = scanner.nextInt();
                AccountOperationType accountOperationType = AccountOperationType.getByOrder(type);
                if (AccountOperationType.SAVING.equals(accountOperationType)) {
                    final double savingAmount = scanner.nextDouble();
                    builder.setSavingAmount(savingAmount);
                }
                accounts.add(builder.setAmount(amount)
                        .setDepositRate(discountRate)
                        .setOperationType(accountOperationType)
                        .createAccount());
                //DRY - end
            }
        }

        return accounts;
    }

    @Override
    public boolean validateStreamData() {
        return true;
    }

}
