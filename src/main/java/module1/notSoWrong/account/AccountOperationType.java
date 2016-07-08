package module1.notSoWrong.account;

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
        //much clearer now. it's not expected to add new operations now, so, it's a kind of using YAGNI too.
        if (order == 1) return SAVING;
        return CHECK_IN;

    }

}
