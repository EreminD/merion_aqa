package ru.merion.aqa.lesson26.owner;

import org.aeonbits.owner.ConfigFactory;

public class Playground {

    public static void main(String[] args) {

        ConfigFactory.setProperty("myEnv", System.getProperty("environment"));
        TestConfig cfg = ConfigFactory.create(TestConfig.class);

        System.out.println(cfg.url());
    }
}
