package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

/***
 *
 * @author jianglinChen
 * @Date 2020/12/7 11:18
 * @since 1.0.0
 */
public class ProxyTest {
    public static void main(String[] args) {
        Object[] objects = new Object[1000];
        for (int i = 0; i < objects.length; i++) {
            Integer values = i +1;
            InvocationHandler handler = new TraceHandler(values);
            Object o = Proxy.newProxyInstance(null, new Class[]{Comparable.class}, handler);
            objects[i] = o;
        }
        Integer key = new Random().nextInt(objects.length) + 1;
        int search = Arrays.binarySearch(objects, key);
        if (search>=0) System.out.println(objects[search]);
    }
}
class TraceHandler implements InvocationHandler{
    private  Object target;

    public TraceHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.print(target);
        System.out.print("."+method.getName()+"(");
        if (args!=null){
            for (int i = 0; i < args.length; i++) {
                System.out.print(args[i]);
                if (i < args.length-1) System.out.print(",");
            }
        }
        System.out.println(")");
        return method.invoke(target,args);
    }
}
