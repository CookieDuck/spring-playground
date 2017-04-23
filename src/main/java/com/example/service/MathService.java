package com.example.service;

import java.util.Formatter;
import java.util.List;

public class MathService {
    public static final Operation DEFAULT_OPERATION = Operation.add;

    public enum Operation {
        add { public String symbol() { return "+"; } },
        subtract { public String symbol() { return "-"; } },
        multiply { public String symbol() { return "*"; } },
        divide { public String symbol() { return "/"; } };

        public abstract String symbol();
    }

    public static String generateExpressionAndResult(Operation operation, Integer x, Integer y) {
        return new Formatter().format("%d %s %d = %d", x, operation.symbol(), y, evaluate(operation, x, y)).toString();
    }

    public static String generateExpressionAndSum(List<Integer> values) {
        Integer sum = 0;
        StringBuilder output = new StringBuilder();
        for (Integer value : values) {
            sum += value;
            output.append(value).append(" + ");
        }
        output.setCharAt(output.length() - 2, '=');
        output.append(String.valueOf(sum));
        return output.toString();
    }

    public static String generateExpressionAndVolume(Integer length, Integer width, Integer height) {
        return new Formatter().format(
                "The volume of a %dx%dx%d rectangle is %d",
                length, width, height, (length * width * height)).toString();
    }

    private static Integer evaluate(Operation op, Integer x, Integer y) {
        switch (op) {
            case subtract:
                return x - y;
            case multiply:
                return x * y;
            case divide:
                return x / y;
            case add:
            default:
                return x + y;
        }
    }
}
