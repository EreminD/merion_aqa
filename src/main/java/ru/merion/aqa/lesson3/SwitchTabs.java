package ru.merion.aqa.lesson3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.merion.aqa.WebDriverFactory;

import java.util.ArrayList;
import java.util.Set;

public class SwitchTabs {

    public static void main(String[] args) {
        WebDriver driver = WebDriverFactory.create();

        driver.get("http://the-internet.herokuapp.com/windows");
        driver.findElement(By.cssSelector("#content a")).click();

        String firstTabId = driver.getWindowHandle();
        Set<String> tabIds = driver.getWindowHandles();
        tabIds.remove(firstTabId);
        String sendTabId = new ArrayList<>(tabIds).get(0);

        String url = driver.getCurrentUrl();
        System.out.println("url = " + url);

        driver.switchTo().window(sendTabId);

        url = driver.getCurrentUrl();
        System.out.println("url = " + url);




    }

}
