package test;

public final class ArithmeticExpression {

    private final int MAX_NUMBER = 10;
    private final String expression;
    private String leftPart;
    private String rightPart;
    private String operator;
    private boolean containsRomanNumbers;

    public ArithmeticExpression(String expression) {
        this.expression = expression;
    }

    public String getLeftPart() {
        return leftPart;
    }

    public String getRightPart() {
        return rightPart;
    }

    public String getOperator() {
        return operator;
    }

    public boolean containsRomanNumbers() {
        return containsRomanNumbers;
    }

    public void prepare() {
        validateExpression();
        parse();
        validateParts();
    }

    private void validateExpression() {
        if (isExpressionBlankOrEmpty()) {
            throw new RuntimeException("Expression must not be empty or blank");
        }
        if (!containsArithmeticOperator()) {
            throw new RuntimeException("Expression must contain one arithmetic operator: *, /, + or -");
        }
    }

    private boolean isExpressionBlankOrEmpty() {
        return expression.isBlank() || expression.isEmpty();
    }

    private boolean containsArithmeticOperator() {
        return expression.contains("*") ||
               expression.contains("/") ||
               expression.contains("+") ||
               expression.contains("-");
    }

    private void parse() {
        int operatorIndex = getOperatorIndex();
        operator = expression.substring(operatorIndex, operatorIndex+1);
        leftPart = expression.substring(0, operatorIndex)
                .trim()
                .toUpperCase();
        rightPart = expression.substring(operatorIndex+1)
                .trim()
                .toUpperCase();
    }

    private int getOperatorIndex() {
        // Returning index of first occurrence of any arithmetic operator (at least one operator exists)
        // Don't check for presence of any more operators
        if (expression.contains("*")) {
            return expression.indexOf("*");
        } else if (expression.contains("/")) {
            return expression.indexOf("/");
        } else if (expression.contains("+")) {
            return expression.indexOf("+");
        } else if (expression.contains("-")) {
            return expression.indexOf("-");
        } else {
            return -1; // this point won't be reached, the line is added just for compiler
        }
    }

    private void validateParts() {
        if (!isNumberInPermittedRange(leftPart) || !isNumberInPermittedRange(rightPart)) {
            throw new RuntimeException("""
                    
                    Both expression parts (left and right to the operator)
                    must be Roman numbers or integer Arabic numbers from 1 to 10
                    """);
        }
        if (!isBothArabicOrBothRoman()) {
            throw new RuntimeException("Numbers must be either both Arabic or both Roman");
        }
        if (isExpressionNotInteger()) {
            throw new RuntimeException("Expression must contain only integers");
        }
    }

    private boolean isNumberInPermittedRange(String number) {
        for (RomanNumber romanNumber : RomanNumber.values()) {
            int currentIntValue = romanNumber.getIntValue();
            if (currentIntValue > MAX_NUMBER) {
                return false;
            }

            if (number.matches("[0-9]+") && currentIntValue == Integer.parseInt(number)) {
                return true;
            }

            String currentRomanValue = romanNumber.getRomanValue();
            if (currentRomanValue.equals(number)) {
                return true;
            }
        }
        return false;
    }

    private boolean isBothArabicOrBothRoman() {
        return (containsArabic(leftPart) && containsArabic(rightPart)) ||
               (containsRoman(rightPart) && containsRoman(leftPart));
    }

    private boolean containsArabic(String number) {
        return Character.isDigit(number.charAt(0));
    }

    private boolean containsRoman(String number) {
        containsRomanNumbers = true;
        return Character.isLetter(number.charAt(0));
    }

    private boolean isExpressionNotInteger() {
        return expression.contains(".") || expression.contains(",");
    }
}