package ru.merion.aqa.lesson11.my_test_framework;

import org.openqa.selenium.WebDriver;
import ru.merion.aqa.lesson7.WDFactory;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class TestRunnerDIY {
    static WebDriver driver = null;

    public static void main(String[] args) {
        System.out.println("Run Tests");

        LabirintTestDIY testClass = new LabirintTestDIY();
        Method[] methods = testClass.getClass().getMethods();
        int testsTotal = 0;
        Set<Method> success = new HashSet<>();
        Set<Method> failed = new HashSet<>();

        for (Method testMethod : methods) {
            try {
                if (testMethod.isAnnotationPresent(TestDIY.class)) {
                    testsTotal++;
                    System.out.println("--------------------------------------------------------");
                    System.out.println();
                    driver = WDFactory.create();
                    testMethod.invoke(testClass, driver);
                    success.add(testMethod);
                }
            } catch (Exception ex) {
                failed.add(testMethod);
                printExeption(ex);
            } finally {
                quitDriver();
            }
        }

        System.out.println("testsTotal = " + testsTotal);
        System.out.println("success = " + success.size());
        System.out.println("failed = " + failed.size());
    }

    private static void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    private static void printExeption(Exception ex) {
        System.err.println("Тест провалился");
        System.out.println(ex);
    }

}
