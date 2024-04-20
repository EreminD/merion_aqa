package ru.merion.aqa.homeworks.lesson9;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Task2 {

    public static void main(String[] args) {
        SelenideElement button = $("#updatingButton");

        open("http://uitestingplayground.com/textinput");

        $("#newButtonName").val("Merion");
        button.click();
        String text = button.text();
        System.out.println(text);
    }
}
