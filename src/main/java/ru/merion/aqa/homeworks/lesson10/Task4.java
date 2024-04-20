package ru.merion.aqa.homeworks.lesson10;

import ru.merion.aqa.homeworks.lesson10.page.RegisterPage;

public class Task4 {

    public static void main(String[] args) {

        RegisterPage page = new RegisterPage().open();

        page.set("first-name", "Иван");
        page.set("last-name", "Петров");
        page.set("job-position", "QA");
        page.set("address", "Ленина 55-3");
        page.set("city", "Москва");
        page.set("country", "Россия");
        page.set("company", "Merion");

        page.submitForm();

        String phone_bg = page.getCssProperty("#phone", "background-color");
        String email_bg = page.getCssProperty("#e-mail", "background-color");
        String zip_code_bg = page.getBgColor("#zip-code");

        System.out.println(zip_code_bg);
        System.out.println(email_bg);
        System.out.println(phone_bg);
    }
}
