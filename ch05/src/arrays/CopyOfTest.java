package arrays;

import java.lang.reflect.Array;
import java.util.Arrays;

/***
 * java.lang.reflect.Array API 总结
 * • static Object get(Object array,int index)
 * • static xxx get/xx(Object array,int index)
 * ( xxx 是boolean、byte、char、double、float、int、long、short 之中的一种基本类M。）
 * 这些方法将返回存储在给定位置上的给定数组的内容。
 * • static void set(Object array,int index,Object newValue)
 * •static setXxx(Object array,int index,xxx newValue)
 * ( xxx 是boolean、byte、char、double、float、int、long、short 之中的一种基本类型。）
 * 这些方法将一个新值存储到给定位置上的给定数组中。
 * •static int getLength(Object array)
 * 返回数组的长度。
 * • static Object newInstance(Class componentType,int length)
 * •static Object newInstance(Class componentType,int[]lengths)
 * 返回一个具有给定类型、给定维数的新数组。
 * @author jianglinChen
 * @Date 2020/12/4 19:12
 * @since 1.0.0
 */
public class CopyOfTest {
    public static void main(String[] args) {
        int[] a = {1,2,3};
        a = (int[]) goodCopyOf(a, 10);
        System.out.println(Arrays.toString(a));

        String[] b = {"Tom","Dick","Harry"};
        b = (String[]) goodCopyOf(b, 10);
        System.out.println(Arrays.toString(b));

        System.out.println("The following call will generate an exception.");
        b = (String[]) badCopyOf(b,10);
    }
    public static Object[] badCopyOf(Object[] a, int newLength){
        Object[] newArrays = new Object[newLength];
        System.arraycopy(a,0,newArrays,0,Math.min(a.length,newLength));
        return newArrays;
    }
    public static Object goodCopyOf(Object a,int newLength){
        Class<?> aClass = a.getClass();
        if (!aClass.isArray()) return null;
        Class<?> componentType = aClass.getComponentType();
        int length = Array.getLength(a);
        Object newArrays = Array.newInstance(componentType, length);
        System.arraycopy(a,0,newArrays,0,Math.min(length,newLength));
        return newArrays;
    }
}
