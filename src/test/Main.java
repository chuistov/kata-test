package test;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String expression = new Scanner(System.in).nextLine();
        String result = calc(expression);
        System.out.println(result);
    }

    public static String calc(String string) {
        var expression = new ArithmeticExpression(string);
        expression.prepare();
        var calculator = new Calculator();
        return calculator.process(expression);
    }
}