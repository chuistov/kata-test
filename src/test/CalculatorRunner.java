package test;

import java.util.Scanner;

public class CalculatorRunner {

    public static void main(String[] args) {
        new CalculatorRunner().go();
    }

    private void go() {
        while (true) {
            var expression = getExpression();
            expression.prepare();
            String result = new Calculator().process(expression);
            output(result);
        }
    }

    private ArithmeticExpression getExpression() {
        String expression = new Scanner(System.in).nextLine();
        return new ArithmeticExpression(expression);
    }

    private void output(String result) {
        System.out.println(result);
    }
}