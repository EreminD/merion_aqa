package ru.merion.aqa.lesson24.ext;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.opentest4j.AssertionFailedError;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class MyTestReporter implements TestWatcher, BeforeAllCallback, AfterAllCallback {

    private Map<String, Method> greenTests;
    private Map<String, Method> redTests;
    private Map<String, Method> yellowTests;

    public static final String HTML_HEAD = """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <title>Test report</title>
                <style>
                .ok {
                    background: #E5FFCC
                }
                
                .failed {
                    background: #FFFFCC
                }
                
                .broken {
                    background: #FFCCCC
                }
                </style>
            </head>
            <body>
            """;

    public static final String HTML_TAIL = """
                </body>
                </html>
            """;


    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd_MM_yy_HH_mm");
        String filename = LocalDateTime.now().format(dateTimeFormatter) + "_report.html";

        Path reportFile = Path.of(filename);

        Files.writeString(reportFile, HTML_HEAD);
        Files.writeString(reportFile, "<ol>", StandardOpenOption.APPEND);

        String content = "";
        for (String testName : greenTests.keySet()) {
            content += "<li class=\"ok\">" + testName + "</li>";
        }

        for (String testName : yellowTests.keySet()) {
            content += "<li class=\"failed\">" + testName + "</li>";
        }

        for (String testName : redTests.keySet()) {
            content += "<li class=\"broken\">" + testName + "</li>";
        }

        Files.writeString(reportFile, content, StandardOpenOption.APPEND);
        Files.writeString(reportFile, "</ol>", StandardOpenOption.APPEND);
        Files.writeString(reportFile, HTML_TAIL, StandardOpenOption.APPEND);
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        greenTests = new HashMap<>();
        redTests = new HashMap<>();
        yellowTests = new HashMap<>();
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        TestWatcher.super.testSuccessful(context);
        System.out.println(context.getDisplayName());

        greenTests.put(context.getDisplayName(), context.getRequiredTestMethod());
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        TestWatcher.super.testFailed(context, cause);
        System.out.println(context.getDisplayName());

        if (cause instanceof AssertionFailedError) {
            yellowTests.put(context.getDisplayName(), context.getRequiredTestMethod());
        } else {
            redTests.put(context.getDisplayName(), context.getRequiredTestMethod());
        }
    }
}
