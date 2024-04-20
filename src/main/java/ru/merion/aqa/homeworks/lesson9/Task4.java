package ru.merion.aqa.homeworks.lesson9;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Task4 {
    public static void main(String[] args) {
        open("https://bonigarcia.dev/selenium-webdriver-java/data-types.html");

        $("[name=first-name]").val("Иван");
        $("[name=last-name]").val("Петров");
        $("[name=address]").val("Ленина 55-3");
        $("[name=city]").val("Москва");
        $("[name=country]").val("Россия");
        $("[name=job-position]").val("QA");
        $("[name=company]").val("Merion");

        $("[type=submit]").click();

        String zip_code_bg = $("#zip-code").getCssValue("background-color");
        String email_bg = $("#e-mail").getCssValue("background-color");
        String phone_bg = $("#phone").getCssValue("background-color");

        System.out.println(zip_code_bg);
        System.out.println(email_bg);
        System.out.println(phone_bg);
    }

}
