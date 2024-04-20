package ru.merion.aqa.lesson25.labirint.page;

import com.codeborne.selenide.Selenide;

public class MainPage {
    
    public void open() {
        Selenide.open("https://www.labirint.ru/");
    }

}
