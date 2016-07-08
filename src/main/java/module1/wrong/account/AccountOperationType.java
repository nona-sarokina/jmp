package module1.wrong.account;

import java.util.Arrays;

/**
 * Created by user on 08.07.2016.
 */
public enum AccountOperationType {
    SAVING(1),
    CHECK_IN(2);

    private final int order;

    AccountOperationType(int order) {
        this.order = order;
    }


    public static AccountOperationType getByOrder(int order) {
         try {
             //KISS breaking - takes a lot of time to get into the sense of these operations. but it works
             return Arrays.asList(AccountOperationType.values()).stream()
                     .filter(x -> x.order == order)
                     .findFirst().get();
         } catch (Exception e) {
             return CHECK_IN;
         }
    }
}
