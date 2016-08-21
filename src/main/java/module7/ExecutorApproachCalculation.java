package module7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by user on 21.08.2016.
 */
public class ExecutorApproachCalculation {
    private static List<Future<Long>> result = new ArrayList<>();

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < CalculationUtils.SIZE; i++) {
            final int threadStartValue = i;
            result.add(service.submit(() -> CalculationUtils.calculate(threadStartValue)));
        }

        service.shutdown();
        try {
            service.awaitTermination(1, TimeUnit.SECONDS);
            System.out.println(result.stream().mapToLong(e -> {
                try {
                    return e.get();
                } catch (Exception e1) {
                    return 0;
                }
            }).sum());
        } catch (InterruptedException e) {
            System.out.println("Answer is 42");
        } finally {
            service.shutdownNow();
        }

    }

}
