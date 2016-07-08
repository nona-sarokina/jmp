package module1.wrong.account;


/**
 * Created by user on 08.07.2016.
 */
public class AccountBuilder {
    private double amount;
    private double depositRate;
    private AccountOperationType operationType;
    private double savingAmount;

    public AccountBuilder setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public AccountBuilder setDepositRate(double depositRate) {
        this.depositRate = depositRate;
        return this;
    }

    public AccountBuilder setOperationType(AccountOperationType operationType) {
        this.operationType = operationType;
        return this;
    }

    public AccountOperator createAccount() {
        switch (operationType) {
            case SAVING:
                return new AccountOperator(amount, depositRate, operationType, savingAmount);
            case CHECK_IN:
                return new AccountOperator(amount, depositRate, operationType);
        }
        return new AccountOperator(amount, depositRate, operationType);
    }

    public AccountBuilder setSavingAmount(double savingAmount) {
        this.savingAmount = savingAmount;
        return this;
    }

}
