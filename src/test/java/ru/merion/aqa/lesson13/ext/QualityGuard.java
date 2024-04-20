package ru.merion.aqa.lesson13.ext;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class QualityGuard implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        TestWatcher.super.testFailed(context, cause);
        if (context.getTags().contains("CRITICAL")){
            System.err.println("ALARM!!! CRIT TEST FAILED");
            // TG! Email
        }
    }
}
