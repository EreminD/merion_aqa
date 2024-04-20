package ru.merion.aqa.lesson4;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import ru.merion.aqa.WebDriverFactory;

public class SendKeys {

    public static void main(String[] args) {

        WebDriver driver = WebDriverFactory.create();
        driver.get("http://uitestingplayground.com/textinput");
        driver.findElement(By.cssSelector("#newButtonName")).sendKeys("Merion");
        driver.findElement(By.cssSelector("#newButtonName")).sendKeys("123", "4",  "5");
        driver.findElement(By.cssSelector("#newButtonName")).sendKeys(Keys.chord(Keys.LEFT_SHIFT, Keys.ARROW_UP));
        driver.findElement(By.cssSelector("#newButtonName")).sendKeys(Keys.BACK_SPACE);
        driver.findElement(By.cssSelector("#updatingButton")).click();


        // file upload
        driver.get("http://the-internet.herokuapp.com/upload");

        driver.findElement(By.cssSelector("#file-upload")).sendKeys("/Users/eremin/Documents/java-projects/merion_aqa/btn.png");
        driver.findElement(By.cssSelector("#file-submit")).click();

        driver.quit();
    }
}
