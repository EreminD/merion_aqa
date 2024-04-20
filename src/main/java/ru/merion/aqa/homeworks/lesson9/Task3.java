package ru.merion.aqa.homeworks.lesson9;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static java.time.Duration.*;

public class Task3 {

    public static void main(String[] args) {
        open("https://bonigarcia.dev/selenium-webdriver-java/loading-images.html");

        ElementsCollection images = $$("#image-container img").shouldHave(sizeGreaterThan(2), ofSeconds(10));
        String src = images.get(2).attr("src");
        System.out.println(src);
    }
}