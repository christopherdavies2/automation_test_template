package com.automatedtesting.support;

import org.apache.log4j.Logger;
import java.util.function.BooleanSupplier;

import static org.junit.Assert.fail;

public class RetrySupport {
    private static final Logger LOG = Logger.getLogger(RetrySupport.class);

    public void retryUntilConditionOrTimeout(Runnable action, BooleanSupplier condition, String logMsg) {
        action.run();

        int timeoutMs = 5000;
        int pollIntervalMs = 100;

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
