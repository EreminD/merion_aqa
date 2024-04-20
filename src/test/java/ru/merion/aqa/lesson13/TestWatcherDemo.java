package ru.merion.aqa.lesson13;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import ru.merion.aqa.lesson12.User;
import ru.merion.aqa.lesson13.ext.MyTestReporter;
import ru.merion.aqa.lesson13.ext.QualityGuard;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({MyTestReporter.class, QualityGuard.class})
@DisplayName("Тесты всех статусов")
public class TestWatcherDemo {

    @Test
    @Disabled
    @DisplayName("Проверяем математику")
    public void eqAssert() {
        int actual = 2 + 2;
        int expected = 4;
        assertEquals(expected, actual, "Математика сломалась");
    }

    @Test
    @DisplayName("Сравниваем массивы")
    public void arraysEqualityDemo() {
        int[] arrA = {1, 2, 3, 4, 5};
        int[] arrB = {1, 2, 3, 4, 5};
        assertArrayEquals(arrA, arrB);
    }

    @Test
    @DisplayName("Сравниваем массивы (негативные тесты)")
    public void arraysNonEqualityDemo() {
        int[] arrA = {1, 2, 3, 4, 5};
        int[] arrB = {1, 2, 3, 4, 9};
        assertFalse(Arrays.equals(arrA, arrB));
    }

    @Test
    @DisplayName("Сравнение объектов")
    @Tag("CRITICAL")
    public void checkTwoObjectEquality() {
        User userFromXml = new User(1, "Tester name", "TEST");
        User userFromDb = new User(2, "Tester name", "TEST");
        assertEquals(userFromXml, userFromDb);
    }

    @Test
    @DisplayName("Сравнение объектов (негатив")
    public void checkTwoObjectNonEquality() {
        User userFromXml = new User(1, "Tester name", "TEST");
        User userFromDb = new User(2, "Tester name", "TEST");
        assertNotEquals(userFromXml, userFromDb);
    }

    @Test
    @DisplayName("Проверка секурности соединения")
    public void checkConnectionIsNotSecure() {
        String url = "http://ya.ru";
        assertFalse(url.contains("https"));
    }

    @Test
    @DisplayName("Сломанный тест")
    public void brokenTest() {
        throw new IllegalArgumentException();
    }

    @Test
    @DisplayName("Проверяем, что ходим по https")
    public void checkConnectionIsSecure() {
        String url = "https://ya.ru";
        assertTrue(url.contains("https"));
    }

    public WebDriver driver;

    @Test
    @DisplayName("Проверяем, что WebDriver == null")
    public void checkDriverIsNull() {
        assertNull(driver);
    }

    @Test
    @DisplayName("Проверяем, что выброшено исключение")
    public void checkThrows() {
        assertThrows(NullPointerException.class, () -> driver.get("https://ya.ru"));
    }


}
