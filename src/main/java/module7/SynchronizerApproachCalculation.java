package module7;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by user on 21.08.2016.
 */
public class SynchronizerApproachCalculation {
    private static AtomicLong result = new AtomicLong(0);

    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(CalculationUtils.SIZE, () -> System.out.println(result));
        for (int i = 0; i < CalculationUtils.SIZE; i++) {
            final int threadStartValue = i;
            new Thread(() -> {
                result.getAndAdd(CalculationUtils.calculate(threadStartValue));
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();

        }
    }

}
