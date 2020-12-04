package method;

import java.lang.reflect.Method;

/***
 * java.lang.reflect.Method
 *  •public Object invokeCObject implicitParameter,Object[] explicitParamenters)
 * 调用这个对象所描述的方法， 传递给定参数，并返回方法的返回值。对于静态方法，
 * 把mill 作为隐式参数传递。在使用包装器传递基本类型的值时， 基本类型的返回值必
 * 须是未包装的。
 *
 * @author jianglinChen
 * @Date 2020/12/4 19:41
 * @since 1.0.0
 */
public class MethodTableTest {
    public static void main(String[] args) throws Exception{
        Method square = MethodTableTest.class.getMethod("square", double.class);
        Method sqrt = Math.class.getMethod("sqrt", double.class);
        printTable(1,10,10,square);
        printTable(1,10,10,sqrt);
    }
    public static double square(double x){
        return x*x;
    }
    public static void printTable(double from, double to, int n, Method f){
        System.out.println(f);
        double dx = (to - from) / (n - 1);
        for (double x = from; x <= to; x+=dx){
            try {
                double y = (double) f.invoke(null, x);
                System.out.printf("X10.4f | %10.4f%n" , x, y) ;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
