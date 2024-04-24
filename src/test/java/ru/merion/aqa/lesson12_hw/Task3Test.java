package ru.merion.aqa.lesson12_hw;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.merion.aqa.lesson12_hw.page.CalculatorPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {
    private WebDriver driver;

    @BeforeEach
    public void open(){
        driver = new ChromeDriver();
    }

    @AfterEach
    public void close(){
        if(driver != null){
            driver.quit();
        }
    }

    @Test
    public void checkSlowCalculator() {
        CalculatorPage calculator = new CalculatorPage(driver).open();

        calculator.setDelay(10);
        calculator.press_7();
        calculator.press_plus();
        calculator.press_8();
        calculator.press_eq();

        assertEquals("15", calculator.getResult());
    }
}
