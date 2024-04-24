package ru.merion.aqa.lesson12_hw;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.merion.aqa.lesson12_hw.page.TextInputPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {
    private static final String BUTTON_NAME = "Merion";
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
    public void iCanRenameTheButton() {
        String text = new TextInputPage(driver).open().setButtonName(BUTTON_NAME).getButtonText();
        assertEquals(BUTTON_NAME, text);
    }
}
