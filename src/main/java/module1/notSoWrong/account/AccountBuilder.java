package module1.notSoWrong.account;


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

    public IAccountOperator createAccount() {
        switch (operationType) {
            case SAVING:
                return new AccountSavingOperator(amount, depositRate, savingAmount);
            case CHECK_IN:
            default:
                return new AccountCheckInOperator(amount, depositRate);
        }

    }

    public AccountBuilder setSavingAmount(double savingAmount) {
        this.savingAmount = savingAmount;
        return this;
    }

}
