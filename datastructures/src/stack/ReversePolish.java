package stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * TODO
 *
 * @author chenjianglin
 * @date 2021/7/18
 * @since 1.0.0
 **/
public class ReversePolish {
    public static void main(String[] args) {
        // 中缀表达式转后缀表达式
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀表达式：" + infixExpressionList);
        List<String> suffix = parseSuffixExpressionList(infixExpressionList);
        System.out.println("后缀表达式：" + suffix);

        System.out.println("后缀表达式计算结果=" + calculate(suffix));


//        String suffixExpression = "30 4 + 5 * 6 -";//
//        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";//76
//        List<String> rpnList = getListString(suffixExpression);
//        System.out.println("rpnList = " + rpnList);
//        int result = calculate(rpnList);
//        System.out.println("计算结果=" + result);
    }

    // 中缀表达式转后缀表达式
    public static List<String> parseSuffixExpressionList(List<String> infixExpression) {
        Stack<String> s1 = new Stack<>();// 运算符栈
        List<String> s2 = new ArrayList<>();// 存放逆波兰表达式
        // 遍历中缀表达式
        for (String item : infixExpression) {
            // 如果是一个数
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {// 如果是左括号
                s1.push(item);
            } else if (item.equals(")")) {// 如果是右括号
                // 依次弹出s1栈顶的运算符，并压入s2，知道遇到左括号，之后丢弃括号
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();// 弹出")"
            } else {
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                // item 压入栈中
                s1.push(item);
            }
        }
        // 把 运算符栈中剩余的运算符加入的逆波兰的表达式的list中
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
    }

    // 中缀表达式转list
    public static List<String> toInfixExpressionList(String expression) {
        ArrayList<String> ls = new ArrayList<>();
        int index = 0;
        String str;
        char c;
        do {
            //如果是一个非数字加入到li中去
            if ((c = expression.charAt(index)) < 48 || (c = expression.charAt(index)) > 57) {
                ls.add("" + c);
                index++;
            } else {// 如果是一个数，要开考虑多位数的情况
                str = "";
                while (index < expression.length() && (c = expression.charAt(index)) >= 48 && (c = expression.charAt(index)) <= 57) {
                    str += c;
                    index++;
                }
                ls.add(str);
            }
        } while (index < expression.length());
        return ls;
    }

    public static List<String> getListString(String suffixExpression) {
        return new ArrayList<>(Arrays.asList(suffixExpression.split(" ")));
    }

    public static int calculate(List<String> rpnList) {
        Stack<String> stack = new Stack<>();
        for (String s : rpnList) {
            if (s.matches("\\d+")) {
                // 入栈
                stack.push(s);
            } else {
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int result = 0;
                if (s.equals("+")) {
                    result = num1 + num2;
                } else if (s.equals("-")) {
                    result = num2 - num1;
                } else if (s.equals("*")) {
                    result = num1 * num2;
                } else if (s.equals("/")) {
                    result = num2 / num1;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push("" + result);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}

class Operation {
    private static final int ADD = 1;
    private static final int SUB = 1;
    private static final int MUL = 2;
    private static final int DIV = 2;

    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                break;
        }
        return result;
    }
}