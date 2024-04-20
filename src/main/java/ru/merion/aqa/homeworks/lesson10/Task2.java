package ru.merion.aqa.homeworks.lesson10;

import ru.merion.aqa.homeworks.lesson10.page.TextInputPage;

public class Task2 {

    public static void main(String[] args) {
        String text = new TextInputPage()
                .open()
                .setButtonName("Merion")
                .getButtonText();

        System.out.println("text = " + text);
    }
}
