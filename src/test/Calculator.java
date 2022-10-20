package test;

class Calculator {
    private boolean expressionContainsRomanNumbers;
    private String leftPart;
    private String rightPart;
    private int leftNumber;
    private int rightNumber;
    private int intResult;
    private String result;

    public String process(ArithmeticExpression expression) {
        intResult = calculate(expression);
        validateResult();
        result = prepareResult();
        return result;
    }

    private int calculate(ArithmeticExpression expression) {
        expressionContainsRomanNumbers = expression.containsRomanNumbers();

        leftPart = expression.getLeftPart();
        rightPart = expression.getRightPart();
        if (expressionContainsRomanNumbers) {
            leftPart = convertRomanToArabic(leftPart);
            rightPart = convertRomanToArabic(rightPart);
        }

        leftNumber = Integer.parseInt(leftPart);
        rightNumber = Integer.parseInt(rightPart);

        return calculateIntegers(expression);
    }

    private String convertRomanToArabic(String number) {
        for (RomanNumber romanNumber : RomanNumber.values()) {
            String currentNumber = romanNumber.getRomanValue();
            if (currentNumber.equals(number)) {
                return String.valueOf(romanNumber.getIntValue());
            }
        }
        return "conversion error"; // must be never reached
//
//        return switch (number) { // TODO un-hardcode it using enum RomanNumber
//            case "I" -> "1";
//            case "II" -> "2";
//            case "III" -> "3";
//            case "IV" -> "4";
//            case "V" -> "5";
//            case "VI" -> "6";
//            case "VII" -> "7";
//            case "VIII" -> "8";
//            case "IX" -> "9";
//            case "X" -> "10";
//        };
    }

    private int calculateIntegers(ArithmeticExpression expression) {
        return switch (expression.getOperator()) {
            case "+" -> leftNumber + rightNumber;
            case "-" -> leftNumber - rightNumber;
            case "*" -> leftNumber * rightNumber;
            case "/" -> leftNumber / rightNumber;
            default -> -1000; // must be never reached
        };
    }

    private void validateResult() {
        if((intResult <= 0) && expressionContainsRomanNumbers) {
            throw new RuntimeException("Expression result for Roman numbers must be greater than zero");
        }
    }

    private String prepareResult() {
        if (!expressionContainsRomanNumbers) {
            return String.valueOf(intResult);
        } else {
            for (RomanNumber number : RomanNumber.values()) {
                if (intResult == number.getIntValue()) {
                    return number.getRomanValue();
                }
            }
        }
        return "result error"; // must never be reached
    }
}
