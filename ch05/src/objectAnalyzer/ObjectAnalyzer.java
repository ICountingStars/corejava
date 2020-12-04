package objectAnalyzer;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/***
 * java.Iang.reflect.AccessibleObject
 * • void setAccessible(boolean flag)
 * 为反射对象设置可访问标志。flag 为true 表明屏蔽Java 语言的访问检查，使得对象的
 * 私有属性也可以被査询和设置。
 * •boolean isAccessible( )
 * 返回反射对象的可访问标志的值。
 * • static void setAccessible(AccessibleObject[ ] array,boolean flag)
 * 是一种设置对象数组可访问标志的快捷方法。
 *  java.lang.reflect.Field;
 * •Object get(Object obj )
 * 返回obj 对象中用Field 对象表示的域值。
 * •void set(Object obj ,Object newValue)
 * 用一个新值设置Obj 对象中Field 对象表示的域。
 *
 * @author jianglinChen
 * @Date 2020/12/4 11:08
 * @since 1.0.0
 */
public class ObjectAnalyzer {
    private ArrayList<Object> visited = new ArrayList<>();

    public String toString(Object obj) {
        if (obj == null) return "null";
        if (visited.contains(obj)) return "...";
        visited.add(obj);
        Class<?> cl = obj.getClass();
        if (cl == String.class) return (String) obj;
        if (cl.isArray()) {
            String r = cl.getCanonicalName() + "[]{";
            for (int i = 0; i < Array.getLength(obj); i++) {
                if (i > 0) r += ",";
                Object val = Array.get(obj, i);
                if (cl.getComponentType().isPrimitive()) r += val;
                else r += toString(val);
            }
            return r + "}";
        }
        String r = cl.getName();
        do {
            r += "[";
            Field[] fields = cl.getDeclaredFields();
            AccessibleObject.setAccessible(fields, true);
            for (Field f : fields) {
                if (!Modifier.isStatic(f.getModifiers())) {
                    if (!r.endsWith("[")) r += ",";
                    r += f.getName() + "=";
                    Class<?> t = f.getType();
                    try {
                        Object val = f.get(obj);
                        if (t.isPrimitive()) r += val;
                        else r += toString(val);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            r += "]";
            cl = cl.getSuperclass();
        } while (cl != null);
        return r;
    }
}
