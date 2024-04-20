package ru.merion.aqa.lesson8;

import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TrySelenide {

    public static void main(String[] args) {
        open("http://uitestingplayground.com/textinput");

        $("#newButtonName").setValue("Merion");
        $("#newButtonName").press(Keys.chord(Keys.LEFT_SHIFT, Keys.ARROW_UP));
        $("#newButtonName").press(Keys.BACK_SPACE);
        $("#newButtonName").setValue("Selenide");
        $("#updatingButton").click();

        $("#updatingButton").shouldHave(exactText("Selenide"));


    }
}
