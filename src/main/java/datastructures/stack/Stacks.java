package datastructures.stack;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Stacks {

    static final String PLUS = "+";
    static final String MINUS = "-";
    static final String MULTIPLE = "*";
    static final String DIVIDE = "/";

    public static boolean isLeftParenthesis(String s) {
        return "(".equals(s);
    }

    public static boolean isRightParenthesis(String s) {
        return ")".equals(s);
    }

    static boolean hasLowerPrecedence(String a, String b) {
        boolean result = ((PLUS.equals(a) || MINUS.equals(a)) && (MULTIPLE.equals(b)) || DIVIDE.equals(b));
        return result;
    }

    public static boolean isOperator(String value) {
        switch (value) {
            case PLUS:
            case MINUS:
            case MULTIPLE:
            case DIVIDE:
                return true;
            default:
                return false;
        }
    }

    public static List<String> infixToPostfix(List<String> infix) {
        var postfix = new ArrayList<String>(infix.size());
        var stack = new ArrayDeque<String>();

        for (String e : infix) {

            if (!isOperator(e) && !isLeftParenthesis(e) && !isRightParenthesis(e)) {
                postfix.add(e);
            }

            if (isLeftParenthesis(e)) {
                stack.push(e);
            }

            if (isRightParenthesis(e)) {

                while(!stack.isEmpty() && !isLeftParenthesis(stack.peek())) {
                    postfix.add(stack.pop());
                }

                if (!stack.isEmpty() && isLeftParenthesis(stack.peek())) {
                    // remove left parenthesis
                    stack.pop();
                }

            }

            if (isOperator(e)) {
                while (!stack.isEmpty() && !isLeftParenthesis(stack.peek()) && !hasLowerPrecedence(stack.peek(), e)) {
                    postfix.add(stack.pop());
                }
                stack.push(e);
            }

        }

        while(!stack.isEmpty()) {
            postfix.add(stack.pop());
        }

        return postfix;
    }

    public static BigDecimal evaluate(List<String> postfix) {
        var stack = new ArrayDeque<BigDecimal>();
        for (String e : postfix) {
            if (!isOperator(e)) {
                stack.push(new BigDecimal(e).setScale(6, RoundingMode.HALF_UP));
            } else {

                BigDecimal result = null;

                BigDecimal fistOperand = stack.pop();

                BigDecimal  secondOperand = stack.pop();

                switch (e) {
                    case MINUS:
                        result = fistOperand.subtract(secondOperand).setScale(6, RoundingMode.HALF_UP);
                        System.out.println(e + " minus");
                        break;
                    case PLUS:
                        result = fistOperand.add(secondOperand).setScale(6, RoundingMode.HALF_UP);
                        break;
                    case DIVIDE:
                        result = fistOperand.divide(secondOperand).setScale(6, RoundingMode.HALF_UP);
                        break;
                    case MULTIPLE:
                        result = fistOperand.multiply(secondOperand).setScale(6, RoundingMode.HALF_UP);
                        break;
                }

                stack.push(result);
            }
        }

        return stack.pop();
    }

    public static void main(String[] args) {
        var infix = List.of("2", "+", "1", "*", "5", "+", "(", "6", "*", "4", "+", "6", ")", "*", "10");

        var postfix = infixToPostfix(infix);

        System.out.println(infix.stream().collect(Collectors.joining(" ")));

        System.out.println(postfix.stream().collect(Collectors.joining(" ")));

        System.out.println(evaluate(postfix));

    }

}
