package ru.merion.aqa.homeworks.lesson7;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.merion.aqa.homeworks.lesson7.page.ImageGalleryPage;

public class Task3 {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        ImageGalleryPage gallery = new ImageGalleryPage(driver).open();
        String src = gallery.getImageProperty(2, "src");
        System.out.println(src);

        driver.quit();
    }
}