package ru.merion.aqa.lesson6;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import ru.merion.aqa.WebDriverFactory;

import java.time.Duration;
import java.util.List;

public class DragAndDrop {

    public static void main(String[] args) {
        String token = "eyJraWQiOiJzZXNzaW9uLXNlcnZpY2UvcHJvZC0xNTkyODU4Mzk0IiwiYWxnIjoiUlMyNTYifQ.eyJhc3NvY2lhdGlvbnMiOltdLCJzdWIiOiI2MTc5MGZlZDIwOTcyMjAwNzEyYjRhMzMiLCJlbWFpbERvbWFpbiI6ImluYm94LnJ1IiwiaW1wZXJzb25hdGlvbiI6W10sImNyZWF0ZWQiOjE3MDc0MjQzNjAsInJlZnJlc2hUaW1lb3V0IjoxNzA4MjA1MjcyLCJ2ZXJpZmllZCI6dHJ1ZSwiaXNzIjoic2Vzc2lvbi1zZXJ2aWNlIiwic2Vzc2lvbklkIjoiODM0NjE2YzgtZjQ5YS00NjllLTljNjQtYjQ3YjY3MjI1ZWFmIiwic3RlcFVwcyI6W10sImF1ZCI6ImF0bGFzc2lhbiIsIm5iZiI6MTcwODIwNDY3MiwiZXhwIjoxNzEwNzk2NjcyLCJpYXQiOjE3MDgyMDQ2NzIsImVtYWlsIjoiZG1pdHJ5LmVyZW1pbkBpbmJveC5ydSIsImp0aSI6IjgzNDYxNmM4LWY0OWEtNDY5ZS05YzY0LWI0N2I2NzIyNWVhZiJ9.Jwq4SaZcwGe1rcQSwp32NzhvbtFVHWMJN45hV7-hHrGjCL7rciDCMg_B3XSeyHfZOYhbq6pLvYbDc_WM34EynlkBCGPV3kwG4_kFEK6zAnZvSHH5xYZbx-liDdMmfQkCbGNPPX4w4e3l0-xFLDmab0qgOuf_8Mn1tLfUfqAaRZzICAFLRWzG80YaLH0xJYjnV23jZnzZbqV2J17ZfKgMAJXi1mxg_MCZ2tYG38tXd_71KkB-uYqp8qK0yAqKYtdStNTKBE4CobxS5VdJK-S_yUN_c_DFTdx7BYyazmBhsjvM9lKiafNthzpXYAZYb2QVtrrvMCYwkAEDPLClWLH67A";

        WebDriver driver = WebDriverFactory.create();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://trello.com");
        driver.manage().addCookie(new Cookie("cloud.session.token", token));
        driver.get("https://trello.com/b/naHsb3BY/test");

        List<WebElement> columns = driver.findElements(By.cssSelector("li[data-testid=list-wrapper]"));
        List<WebElement> cards = columns.get(0).findElements(By.cssSelector("li[data-testid=list-card]"));

        new Actions(driver)
                .clickAndHold(cards.get(0))
                .moveToElement(columns.get(2))
                .release()
                .perform();

        cards = columns.get(0).findElements(By.cssSelector("li[data-testid=list-card]"));
        new Actions(driver)
                .clickAndHold(cards.get(0))
                .release(columns.get(2))
                .perform();

        cards = columns.get(0).findElements(By.cssSelector("li[data-testid=list-card]"));
        new Actions(driver)
                .dragAndDrop(cards.get(0), columns.get(2))
                .perform();

        driver.quit();
    }
}
