package ru.merion.aqa.homeworks.lesson10;

import ru.merion.aqa.homeworks.lesson10.page.CalculatorPage;

public class Task5 {

    public static void main(String[] args) {
        CalculatorPage calculator = new CalculatorPage().open();

        calculator.setDelay(10);
        calculator.press_7();
        calculator.press_plus();
        calculator.press_8();
        calculator.press_eq();

        String result = calculator.getResult();
        System.out.println(result);
    }
}
