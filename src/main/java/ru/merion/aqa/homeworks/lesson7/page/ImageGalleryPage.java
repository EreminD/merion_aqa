package ru.merion.aqa.homeworks.lesson7.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfElementsToBeMoreThan;

public class ImageGalleryPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public ImageGalleryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public ImageGalleryPage open() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/loading-images.html");
//      wait.until(textToBePresentInElementLocated(By.cssSelector("#text"), "Done!"));
//      wait.until(invisibilityOfElementLocated(By.cssSelector("#spinner")));
//      wait.until(stalenessOf(driver.findElement(By.cssSelector("#spinner"))));
        return this;
    }

    public String getImageProperty(int imageIndex, String propertyName) {
        List<WebElement> images = wait.until(numberOfElementsToBeMoreThan(By.cssSelector("#image-container img"), imageIndex));
        return images.get(imageIndex).getAttribute(propertyName);
    }
}
