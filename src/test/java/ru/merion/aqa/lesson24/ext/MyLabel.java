package ru.merion.aqa.lesson24.ext;

import io.qameta.allure.LabelAnnotation;

import java.lang.annotation.*;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@LabelAnnotation(name = "CustomLabelName")
public @interface MyLabel {
    String value();
}
