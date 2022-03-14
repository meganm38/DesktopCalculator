package Operation;

import Exceptions.InvalidEquation;

import java.util.Stack;

public class CalculationPerformer {

    public String calculate(String equation) {

        String[] operands = equation.split("[+\\-x/]");
        String operator = equation.substring(operands[0].length(), operands[0].length() + 1);

        int result;
        switch (operator) {
            case "+":
                result = Integer.parseInt(operands[0]) + Integer.parseInt(operands[1]);
                break;
            case "-":
                result = Integer.parseInt(operands[0]) - Integer.parseInt(operands[1]);
                break;
            case "x":
                result = Integer.parseInt(operands[0]) * Integer.parseInt(operands[1]);
                break;
            default:
                result = Integer.parseInt(operands[0]) / Integer.parseInt(operands[1]);
                break;
        }
        return result + "";

    }

    public void checkValidEquation(String equation) throws InvalidEquation {
        //only supports integer addition now
        if (!equation.matches("((0|[1-9](\\d)*)([+\\-x/])(0|[1-9](\\d)*))+")) {
            throw new InvalidEquation();
        }
    }

    private String toPostFix(String equation) {
        StringBuilder stringBuilder = new StringBuilder();
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < equation.length(); i++) {
            if (equation.substring(i, i + 1).matches("\\d")) {
                stringBuilder.append(equation.substring(i, i + 1));
            }
        }


        return stringBuilder.toString();
    }

    private int precedence(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "x":
            case "/":
                return 2;
            case "^":
                return 3;
            default:
                return 0;
        }
    }
}
