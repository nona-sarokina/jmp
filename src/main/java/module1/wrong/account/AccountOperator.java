package module1.wrong.account;

/**
 * Created by user on 08.07.2016.
 */

//single responsibility. Operator should do one type of operation - saving or check in

public class AccountOperator{
    private double savingAmount;
    private double amount;
    private double depositRate;
    private AccountOperationType operationType;


    public AccountOperator(double amount, double depositRate, AccountOperationType operationType, double savingAmount) {
        this.savingAmount = savingAmount;
        this.amount = amount;
        this.depositRate = depositRate;
        this.operationType = operationType;
    }

    public AccountOperator(double amount, double depositRate, AccountOperationType operationType) {

        this.amount = amount;
        this.depositRate = depositRate;
        this.operationType = operationType;
    }

    public void deposite() {
        if (AccountOperationType.SAVING.equals(this.operationType)) {
            System.out.println("Saving " + savingAmount + ", amount after will be " + getAmount(savingAmount));
        } else {
            System.out.println("Checked in amount will be " + getAmount());

        }
    }

    private double getAmount() {
        return amount * depositRate;
    }

    private double getAmount(double savingAmount) {
        this.amount += savingAmount;

        return amount * depositRate;
    }
}
