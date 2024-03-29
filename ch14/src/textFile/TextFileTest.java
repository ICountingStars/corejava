package textFile;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author jianglinChen
 * @date 2021/1/10
 * @since 1.0.0
 */
public class TextFileTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        Employee harry = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        Class<? extends Employee> cl = harry.getClass();
        Field f = cl.getDeclaredField("name");
        System.out.println(f);
        f.setAccessible(true);
        Object v = f.get(harry);
        System.out.println(v);

        int[] a = {1, 2, 3, 4, 5};

        a = (int[]) goodCopyOf(a, 10);
        System.out.println(Arrays.toString(a));

    }

    public static Object[] badCopyOf(Object[] a, int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(a, 0, newArray, 0, Math.min(a.length, newLength));
        return newArray;
    }

    public static Object goodCopyOf(Object a, int newLength) {
        Class<?> cl = a.getClass();
        if (!cl.isArray()) {
            return null;
        }
        Class<?> componentType = cl.getComponentType();
        int length = Array.getLength(a);
        Object newArray = Array.newInstance(componentType, newLength);
        System.arraycopy(a, 0, newArray, 0, Math.min(length, newLength));
        return newArray;
    }
}

class A {
    public static Boolean valueOf(boolean b) {
        return b ? Boolean.TRUE : Boolean.FALSE;
    }
}