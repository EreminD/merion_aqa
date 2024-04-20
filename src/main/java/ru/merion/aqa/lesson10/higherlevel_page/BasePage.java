package ru.merion.aqa.lesson10.higherlevel_page;

public abstract class BasePage {

    public BasePage() {
        this.header = new HeaderElement();
    }

    private HeaderElement header;

    public HeaderElement header(){
        return this.header;
    };

}
