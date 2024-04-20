package ru.merion.aqa.lesson4;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import ru.merion.aqa.WebDriverFactory;

public class JSExecution {

    public static void main(String[] args) {

        String jsRemoveBanner = "document.querySelector(\".tgb-wrapper\").remove()";
        String jsSetLocalStorage = "localStorage.setItem(\"bestScore\", \"7777777\");";

        WebDriver driver = WebDriverFactory.create();

        driver.get("https://mail.ru/");
        ((JavascriptExecutor)driver).executeScript(jsRemoveBanner);


        driver.get("https://play2048.co/");
        ((JavascriptExecutor)driver).executeScript(jsSetLocalStorage);
        driver.navigate().refresh();

        driver.quit();


    }
}
