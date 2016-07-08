package module1.wrong.streamOperators;

import module1.wrong.account.AccountBuilder;
import module1.wrong.account.AccountOperationType;
import module1.wrong.account.AccountOperator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by user on 08.07.2016.
 */
public class FileReader implements IStreamOperator {

    private String path;

    public FileReader(String path) {
        this.path = path;
    }

    @Override
    //also not single responsibility
    public List<AccountOperator> doStreamOperation() {
        final List<AccountOperator> accounts = new ArrayList<>();
        final AccountBuilder builder = new AccountBuilder();
        try (Scanner scanner = new Scanner(new File(path))) {

            while (scanner.hasNextLine()) {
                //DRY - begin - same code for creating accounts for both readers
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


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return accounts;
    }

    @Override
    public boolean validateStreamData() {
        return false;
    }
}
