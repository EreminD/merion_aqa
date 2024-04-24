package ru.merion.aqa.lesson12_hw;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.merion.aqa.lesson12_hw.page.RegisterPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {
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
    public void checkValidationError() {
        RegisterPage page = new RegisterPage(driver).open();

        page.set("first-name", "Иван");
        page.set("last-name", "Петров");
        page.set("job-position", "QA");
        page.set("address", "Ленина 55-3");
        page.set("city", "Москва");
        page.set("country", "Россия");
        page.set("company", "Merion");

        page.submitForm();

        String phone_bg = page.getCssProperty("#phone", "background-color");
        String email_bg = page.getCssProperty("#e-mail", "background-color");
        String zip_code_bg = page.getCssProperty("#zip-code", "background-color");

        assertEquals("rgba(248, 215, 218, 1)", zip_code_bg);
        assertEquals("rgba(248, 215, 218, 1)", email_bg);
        assertEquals("rgba(248, 215, 218, 1)", phone_bg);
    }
}
