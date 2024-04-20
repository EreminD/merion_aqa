package ru.merion.aqa.lesson12.ext;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectWebDriver {
    // (chrome|firefox|safari|edge)
    String browserName() default "chrome";
}
