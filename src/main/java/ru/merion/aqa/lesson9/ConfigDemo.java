package ru.merion.aqa.lesson9;

import com.codeborne.selenide.AssertionMode;
import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;

public class ConfigDemo {



    public static void main(String[] args) {
        Configuration.timeout = 1000*8;
        Configuration.headless = true;
        Configuration.browser = "firefox";
        Configuration.assertionMode = AssertionMode.SOFT;
        Configuration.fastSetValue = false;
        Configuration.baseUrl = "https://habr.com";

        open("/ru");
        System.out.println(title());
    }



}
