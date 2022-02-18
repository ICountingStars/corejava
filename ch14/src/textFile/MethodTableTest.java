package textFile;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * TODO
 *
 * @author chenjianglin
 * @date 2021/6/26
 * @since 1.0.0
 **/
public class MethodTableTest {
    public static void main(String[] args) throws NoSuchMethodException {
        //
        Method square = MethodTableTest.class.getMethod("square", double.class);//平方
        Method sqrt = Math.class.getMethod("sqrt", double.class); //开方

        //
        printTable(1, 10, 10, square);
        printTable(1, 10, 10, sqrt);
    }

    public static double square(double x) {
        return x * x;
    }

    public static void printTable(double from, double to, int n, Method f) {
        //打印表头的方法
        System.out.println(f);
        double dx = (to - from) / (n - 1);
        for (double x = from; x <= to; x += dx) {
            try {
                double y = (Double) f.invoke(null, x);
                System.out.printf("%10.4f | %10.4f%n", x, y);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
