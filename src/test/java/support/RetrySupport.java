package support;

import java.util.function.BooleanSupplier;

import static org.junit.Assert.fail;

public class RetrySupport {

    public void retryUntilConditionOrTimeoutReached(Runnable action, BooleanSupplier condition, String logMsg) {
        action.run();
        int timeoutMs = 3000;
        int pollIntervalMs = 100;

        while (!condition.getAsBoolean() && timeoutMs > 0) {
            try {
                Thread.sleep(pollIntervalMs);
                timeoutMs -= pollIntervalMs;
                System.out.println(String.format("Retrying: %s", logMsg));
                action.run();
            } catch (InterruptedException ex) {
                fail(ex.getMessage());
            }
        }

        if (timeoutMs == 0) {
            System.out.println(String.format("Retry timeout reached for: %s", logMsg));
        }
    }
}
