package ru.merion.aqa.homeworks.lesson10.page;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CalculatorPage {
    private final SelenideElement keyboard = $(".keys");
    private int delay = 5;

    public CalculatorPage open() {
        Selenide.open("https://bonigarcia.dev/selenium-webdriver-java/slow-calculator.html");
        return this;
    }

    public void setDelay(int delay) {
        $("#delay").val(String.valueOf(delay));
        this.delay = delay;
    }

    public void press_1() {
        keyboard.$x("//*[text() = '1']").click();
    }

    public void press_2() {
        keyboard.$x("//*[text() = '2']").click();
    }

    public void press_3() {
        keyboard.$x("//*[text() = '3']").click();
    }

    public void press_4() {
        keyboard.$x("//*[text() = '4']").click();
    }

    public void press_5() {
        keyboard.$x("//*[text() = '5']").click();
    }

    public void press_6() {
        press("6");
    }

    public void press_7() {
        press("7");
    }

    public void press_8() {
        press("8");
    }

    public void press_9() {
        press("9");
    }

    public void press_plus() {
        press("+");
    }

    public void press_eq() {
        press("=");
    }

    private void press(String val) {
        keyboard.$x("*[text() = '" + val + "']").click();
    }

    public String getResult() {
        Configuration.timeout = delay * 1000 + 100;
        $("#spinner").shouldBe(not(visible));
        return $(".screen").text();
    }
}
