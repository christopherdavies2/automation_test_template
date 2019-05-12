package support;

import org.apache.log4j.Logger;
import java.util.function.BooleanSupplier;

import static org.junit.Assert.fail;

public class RetrySupport {
    private static final Logger LOG = Logger.getLogger(RetrySupport.class);

    private int timeoutMs = 3000;
    private int pollIntervalMs = 100;

    public void setTimeoutMs(int timeoutMs) {
        this.timeoutMs = timeoutMs;
    }

    public int getTimeoutMs() {
        return timeoutMs;
    }

    public void setPollIntervalMs(int pollIntervalMs) {
        this.pollIntervalMs = pollIntervalMs;
    }

    public int getPollIntervalMs() {
        return pollIntervalMs;
    }

    public void retryUntilConditionOrTimeout(Runnable action, BooleanSupplier condition, String logMsg) {
        action.run();

        while (!condition.getAsBoolean() && timeoutMs > 0) {
            try {
                Thread.sleep(pollIntervalMs);
                timeoutMs -= pollIntervalMs;
                LOG.info(String.format("Retrying: %s", logMsg));
                action.run();
            } catch (InterruptedException ex) {
                fail(ex.getMessage());
            }
        }

        if (timeoutMs == 0) {
            LOG.info(String.format("Retry timeout reached for: %s", logMsg));
        }
    }
}
