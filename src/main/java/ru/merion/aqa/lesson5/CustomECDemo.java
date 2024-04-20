package ru.merion.aqa.lesson5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.merion.aqa.WebDriverFactory;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

import static ru.merion.aqa.lesson5.CustomEC.*;

public class CustomECDemo {

    public static void main(String[] args) {
        Path relativePath = Path.of("src/main/resources/dummy_pages/old_vk.html");
        String filePath = Paths.get("").toAbsolutePath().resolve(relativePath).toString();

        WebDriver driver = WebDriverFactory.create();
        driver.get("file://" + filePath);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20), Duration.ofSeconds(1));
        wait.until(textContainsAfterRefresh(By.cssSelector("#msg_counter"), "(1)"));

        String txt = driver.findElement(By.cssSelector("p")).getText();
        System.out.println(txt); // Входящие (1)

        driver.quit();
    }
}
