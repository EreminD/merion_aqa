package ru.merion.aqa.homeworks.lesson9;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.time.Duration.*;

public class Task1 {
    public static void main(String[] args) {

        open("http://uitestingplayground.com/ajax");

        $("#ajaxButton").click();
        String content = $("#content p").shouldBe(visible, ofSeconds(16)).getText();
        System.out.println(content);
    }
}
