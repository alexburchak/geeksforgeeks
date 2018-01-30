package org.alexburchak.programcreek.interview.reverse_polish_notation;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Stack;

import static org.testng.Assert.assertEquals;

/**
 * @author alexburchak
 */
public class ReversePolishNotation {
    private enum OP {
        PLUS("+") {
            @Override
            int eval(int value1, int value2) {
                return value2 + value1;
            }
        },
        MINUS("-") {
            @Override
            int eval(int value1, int value2) {
                return value2 - value1;
            }
        },
        MULTIPLY("*") {
            @Override
            int eval(int value1, int value2) {
                return value2 * value1;
            }
        },
        DIVIDE("/") {
            @Override
            int eval(int value1, int value2) {
                return value2 / value1;
            }
        };

        private String op;

        OP(String op) {
            this.op = op;
        }

        abstract int eval(int value1, int value2);
    }

    @DataProvider
    public Object[][] dataProvider() {
        return new Object[][]{
                new Object[]{new String[]{"2", "1", "+", "3", "*"}, (2 + 1) * 3},
                new Object[]{new String[]{"4", "13", "5", "/", "+"}, 4 + (13 / 5)}
        };
    }

    @Test(dataProvider = "dataProvider")
    public void test(String array[], int expected) {
        assertEquals(evaluate(array), expected);
    }

    private int evaluate(String array[]) {
        Stack<Integer> stack = new Stack<>();
        Arrays.stream(array)
                .forEach(e -> parse(stack, e));
        return stack.pop();
    }

    private void parse(Stack<Integer> stack, String expr) {
        Arrays.stream(OP.values())
                .filter(e -> e.op.equals(expr))
                .map(e -> stack.push(e.eval(stack.pop(), stack.pop())))
                .findAny()
                .orElseGet(() -> stack.push(Integer.parseInt(expr)));
    }
}
