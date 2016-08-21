package module7;

/**
 * Created by user on 21.08.2016.
 */
public class CalculationUtils {
    public static int SIZE = 1_000;

    public static Long calculate(int threadStartValue) {
        long tmpResult = 0L;
        int startValue = threadStartValue * SIZE + 1;
        int endValue = startValue + SIZE;
        for (int i = startValue; i < endValue; i++) {
            tmpResult += i;
        }
        return tmpResult;
    }
}
