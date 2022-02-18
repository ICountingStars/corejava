package textFile;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * TODO
 *
 * @author chenjianglin
 * @date 2021/6/26
 * @since 1.0.0
 **/
public class CopyOfTest {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5};
        a = (int[]) goodCopyOf(a, 10);
        System.out.println(Arrays.toString(a)); //输出: [1, 2, 3, 4, 5, 0, 0, 0, 0, 0]

        String[] s = {"Tom", "Jerry", "Jack", "Rose"};
        s = (String[]) goodCopyOf(s, 8);
        System.out.println(Arrays.toString(s)); // 输出: [Tom, Jerry, Jack, Rose, null, null, null, null]

        // throw java.lang.ClassCastException because  [Ljava.lang.Object; cannot be cast to [Ljava.lang.String
        s = (String[]) badCopyOf(s, 8);
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
