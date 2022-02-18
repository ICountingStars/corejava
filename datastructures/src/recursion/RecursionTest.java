package recursion;

/**
 * @author chenjianglin
 * @date 2021/7/18
 * @since 1.0.0
 **/
public class RecursionTest {
    public static void main(String[] args) {
        // 打印问题
        print(4);
        // 阶乘问题
        System.out.println("5的阶乘：" + factorial(5));
    }

    // 阶乘问题
    private static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1) * n;
        }
    }

    // 打印问题
    public static void print(int n) {
        if (n > 2) {
            print(n - 1);
        }
        System.out.println("n=" + n);
    }
}
