package ru.merion.aqa.homeworks.lesson10;

import ru.merion.aqa.homeworks.lesson10.page.ImageGalleryPage;

public class Task3 {

    public static void main(String[] args) {
        ImageGalleryPage gallery = new ImageGalleryPage().open();
        String src = gallery.getImageProperty(2, "src");
        System.out.println(src);
    }
}