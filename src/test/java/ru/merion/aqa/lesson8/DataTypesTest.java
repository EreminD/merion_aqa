package ru.merion.aqa.lesson8;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Selenide.*;

public class DataTypesTest {

    @BeforeAll
    public static void setUp() {
        Configuration.headless = true;
    }

    // 12.7
    // 12.8
    // 14.4
    @RepeatedTest(30) // 13.5
    public void test() {
        open("https://bonigarcia.dev/selenium-webdriver-java/data-types.html");
        $("[name=first-name]").val("Иван");
        $("[name=last-name]").val("Петров");
        $("[name=address]").val("Ленина 55-3");
        $("[name=city]").val("Москва");
        $("[name=country]").val("Россия");
        $("[name=job-position]").val("QA");
        $("[name=company]").val("inzhenerka");

        $("[type=submit]").click();

        $("#zip-code").shouldHave(cssValue("background-color", "rgba(248, 215, 218, 1)"));
        $("#e-mail").shouldHave(cssValue("background-color", "rgba(248, 215, 218, 1)"));
        $("#phone").shouldHave(cssValue("background-color", "rgba(248, 215, 218, 1)"));
    }

    @Test
    public void todoTest(){
        open("https://sky-todo-list.herokuapp.com");
        $$("tbody tr").shouldHave(size(4));
    }
}
