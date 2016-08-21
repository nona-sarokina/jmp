package module7;

/**
 * Created by user on 21.08.2016.
 */
public class ClassicApproachCalculation {
    private static Long result = 0L;

    public static void main(String[] args) {
        Thread t;
        for (int i = 0; i < CalculationUtils.SIZE; i++) {
            int threadStartValue = i;
            t = new Thread(() -> addToResult(CalculationUtils.calculate(threadStartValue)));
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Result is " + result);
    }

    private static synchronized void addToResult(Long valueToAdd) {
        result += valueToAdd;
    }
}
