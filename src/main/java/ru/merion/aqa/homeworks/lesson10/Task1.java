package ru.merion.aqa.homeworks.lesson10;

import com.codeborne.selenide.Configuration;
import ru.merion.aqa.homeworks.lesson10.page.AjaxPage;

public class Task1 {
    public static void main(String[] args) {
        Configuration.timeout = 16 * 1000;

        AjaxPage page = new AjaxPage();
        page.open();
        page.clickTheButton();

        System.out.println("content = " + page.getContent());
    }
}
