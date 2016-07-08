package module1.notSoWrong.account;

/**
 * Created by user on 08.07.2016.
 */
//updated with open close, extension had made for saving amount field
public class AccountSavingOperator extends AccountCheckInOperator {
    private double savingAmount;

    public AccountSavingOperator(double amount, double depositRate, double savingAmount) {
        super(amount, depositRate);
        this.savingAmount = savingAmount;
    }

    @Override
    public void deposite() {

        System.out.println("Saving " + savingAmount + ", amount after will be " + getAmount());
    }

    @Override
    public double getAmount() {
        this.amount += savingAmount;

        return amount * depositRate;
    }
}
