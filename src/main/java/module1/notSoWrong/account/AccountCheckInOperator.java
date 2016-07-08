package module1.notSoWrong.account;

/**
 * Created by user on 08.07.2016.
 */

public class AccountCheckInOperator implements IAccountOperator {
    protected double amount;
    protected double depositRate;

    public AccountCheckInOperator(double amount, double depositRate) {
        this.amount = amount;
        this.depositRate = depositRate;
    }

    @Override
    public void deposite() {
        System.out.println("Checked in amount will be " + getAmount());
    }

    @Override
    public double getAmount() {
        return amount * depositRate;
    }
}
