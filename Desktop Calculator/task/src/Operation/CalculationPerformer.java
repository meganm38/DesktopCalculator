package Operation;

import Exceptions.InvalidEquation;

import java.util.Stack;

public class CalculationPerformer {

    public String calculate(String equation) {
        Stack<String> stack = new Stack<>();
        double result = 0.0;

        int startIndex = 0;
        String postFix = toPostFix(equation);
        for (int i = 0; i < postFix.length(); i++) {
            if (postFix.charAt(i) == ' ') {
                if (postFix.substring(startIndex, i).matches("(0(.(\\d)+)?|[1-9](\\d)*(.(\\d)+)?)")) {
                    stack.push(postFix.substring(startIndex, i));
                } else {
                    String operand1;
                    String operand2;

                    operand1 = stack.pop();
                    operand2 = stack.pop();

                    switch (postFix.substring(startIndex, i)) {
                        case "+":
                            result = Double.parseDouble(operand2) + Double.parseDouble(operand1);
                            break;
                        case "-":
                            result = Double.parseDouble(operand2) - Double.parseDouble(operand1);
                            break;
                        case "\u00D7":
                            result = Double.parseDouble(operand2) * Double.parseDouble(operand1);
                            break;
                        case "\u00F7":
                            result = Double.parseDouble(operand2) / Double.parseDouble(operand1);
                            break;
                        case "^":
                            result = Math.pow(Double.parseDouble(operand2), Double.parseDouble(operand1));
                            break;
                    }
                    stack.push(result + "");
                    result = 0.0;
                }
                startIndex = i + 1;
            }
        }
        String answer = stack.pop();
        if (answer.contains(".")) {
            int dotIndex = answer.indexOf(".");
            if (answer.substring(dotIndex + 1).matches("0+")) {
                answer = answer.substring(0, dotIndex);
            }
        }

        return answer;
    }

    public void checkValidEquation(String equation) throws InvalidEquation {
        //only supports integer addition now
        if (!equation.matches("((0(.(\\d)+)?|[1-9](\\d)*(.(\\d)+)?)([+\\-\u00D7\u00F7])(0(.(\\d)+)?|[1-9](\\d)*(.(\\d)+)?))+(([+\\-\u00D7\u00F7])(0(.(\\d)+)?|[1-9](\\d)*(.(\\d)+)?))*")) {
            throw new InvalidEquation();
        }

        if (equation.contains("\u00F70")) {
            throw new InvalidEquation();
        }
    }

    public String toPostFix(String equation) {
        Stack<String> stack = new Stack<>();
        StringBuilder stringBuilder = new StringBuilder();
        int startIndex = 0;
        stack.push("#");

        for (int i = 0; i < equation.length(); i++) {
            if (equation.substring(i, i + 1).matches("[+\\-\u00D7\u00F7()^]")) {
                stringBuilder.append(equation, startIndex, i).append(" ");
                startIndex = i + 1;

                if (equation.charAt(i) == '(') {
                    stack.push(equation.substring(i, i + 1));
                } else if (equation.charAt(i) == ')') {
                    while (!stack.peek().equals("(")) {
                        stringBuilder.append(stack.pop()).append(" ");
                    }
                    stack.pop();
                } else if (precedence(equation.substring(i, i + 1)) > precedence(stack.peek())) {
                    stack.push(equation.substring(i, i + 1));
                } else if (precedence(equation.substring(i, i + 1)) < precedence(stack.peek())) {
                    do {
                        stringBuilder.append(stack.pop()).append(" ");
                    } while (precedence(equation.substring(i, i + 1)) <= precedence(stack.peek()));
                    stack.push(equation.substring(i, i + 1));
                } else if (precedence(equation.substring(i, i + 1)) == precedence(stack.peek())) {
                    stringBuilder.append(stack.pop()).append(" ");
                    stack.push(equation.substring(i, i + 1));
                }
            }
            if (i == equation.length() - 1) {
                stringBuilder.append(equation, startIndex, i + 1).append(" ");
            }
        }
        while (!stack.peek().equals("#")) {
            stringBuilder.append(stack.pop()).append(" ");
        }
        return stringBuilder.toString();
    }

    private int precedence(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "\u00D7":
            case "\u00F7":
                return 2;
            case "^":
                return 3;
            default:
                return 0;
        }
    }
}
