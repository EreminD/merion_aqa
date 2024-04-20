package ru.merion.aqa.lesson12_hw;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.merion.aqa.lesson12_hw.page.CalculatorPage;

public class Task3 {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        CalculatorPage calculator = new CalculatorPage(driver).open();

        calculator.setDelay(10);
        calculator.press_7();
        calculator.press_plus();
        calculator.press_8();
        calculator.press_eq();

        calculator.press("7");

        String result = calculator.getResult();
        System.out.println(result);

        driver.quit();
    }
}
