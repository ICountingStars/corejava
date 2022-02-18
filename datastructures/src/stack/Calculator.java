package stack;

/**
 * TODO
 *
 * @author chenjianglin
 * @date 2021/7/17
 * @since 1.0.0
 **/
public class Calculator {
    public static void main(String[] args) {
        String expression = "7*2*2-5+1-5+3-4";
        ArrayStack2 numberStack = new ArrayStack2(10);
        ArrayStack2 operationStack = new ArrayStack2(10);
        int index = 0;
        int num1, num2, operation, result;
        String keepNum = "";
        char ch = ' ';
        while (true) {
            ch = expression.substring(index, index+1).charAt(0);
            // 判断ch
            if (operationStack.isOperation(ch)) {// 是运算符
                // 判断运算符栈是否为空
                if (!operationStack.isEmpty()) {//运算符栈不为空
                    // 判断运算符的优先级 栈内 >= ch (当前)
                    if (operationStack.priority(ch) <= operationStack.priority(operationStack.peek())) {
                        // 数值栈pop出两个数 运算符栈pop出一个数 进行运算
                        // 运算的得到的值 push 回数值栈
                        // 当前的运算符 push 回运算符栈
                        num1 = numberStack.pop();
                        num2 = numberStack.pop();
                        operation = operationStack.pop();
                        result = numberStack.calculator(num1, num2, operation);
                        numberStack.push(result);
                        operationStack.push(ch);
                    } else { // 栈内 < ch
                        // 直接入栈
                        operationStack.push(ch);
                    }
                } else {//运算符栈为空
                    // 直接入栈
                    operationStack.push(ch);
                }
            } else { // 是数直接入数栈
//                numberStack.push(ch - 48);
                // 处理多位数
                keepNum += Character.toString(ch);
                if (index == expression.length() - 1) {// 最后一个数直接入数栈
                    numberStack.push(Integer.parseInt(keepNum));
                    keepNum = "";
                } else {//非最后一个数
                    // 判断数值后面是否为表达式
                    if (operationStack.isOperation(expression.substring(index + 1, index + 2).charAt(0))) {
                        numberStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }

            }
            // 扫描完运算符后
            if (++index >= expression.length()) {
                break;
            }
        }

        // 顺序从数栈和运算符栈中计算数
        while (true) {
            if (operationStack.isEmpty()) {
                break;
            }
            num1 = numberStack.pop();
            num2 = numberStack.pop();
            operation = operationStack.pop();
            result = numberStack.calculator(num1, num2, operation);
            numberStack.push(result);
        }
        System.out.printf("表达式:%s=%d", expression, numberStack.pop());
    }
}

class ArrayStack2 {
    private int maxSize;
    private int[] stack;
    private int top = -1;

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    // 查看栈顶
    public int peek() {
        return stack[top];
    }

    // 栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    // 栈空
    public boolean isEmpty() {
        return top == -1;
    }

    // 入栈
    public void push(int value) {
        if (isFull()) {
            throw new RuntimeException("栈满");
        }
        top++;
        stack[top] = value;
    }

    // 出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        return stack[top--];
    }

    // 遍历栈
    public void show() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d\n", i, stack[i]);
        }
    }

    // 返回运算符优先级
    public int priority(int operation) {
        if (operation == '*' || operation == '/') {
            return 1;
        } else if (operation == '+' || operation == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    //判断是不是一个运算符
    public boolean isOperation(char value) {
        return value == '+' || value == '-' || value == '*' || value == '/';
    }

    //计算方法
    public int calculator(int num1, int num2, int operation) {
        int result = 0;
        switch (operation) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num2 - num1;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num2 / num1;
                break;
            default:
                break;
        }
        return result;
    }
}