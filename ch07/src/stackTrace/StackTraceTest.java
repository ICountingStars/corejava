package stackTrace;

import java.util.Scanner;

/***
 *
 * @author jianglinChen
 * @Date 2020/12/22 10:30
 * @since 1.0.0ji
 */
public class StackTraceTest {

    public static int factorial(int n){
        System.out.println("factorial(" + n + "):");
        Throwable throwable = new Throwable();
        StackTraceElement[] stackTrace = throwable.getStackTrace();
        for (StackTraceElement s :
                stackTrace) {
            System.out.println(s);
        }
        int r;
        if (n <1 )r= 1;
        else r = n*factorial(n-1);
        System.out.println("return"+ r);
        return r;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter n:");
        int n = scanner.nextInt();
        factorial(n);
    }
}
