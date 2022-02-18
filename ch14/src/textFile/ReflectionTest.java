package textFile;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

/**
 * 分析Java编译器能够加载的任何类
 *
 * @author chenjianglin
 * @date 2021/6/24
 * @since 1.0.0
 **/
public class ReflectionTest {
    public static void main(String[] args) throws ReflectiveOperationException {
        // 读取来自命令行输入的类名
        String name;
        if (args.length > 0) {
            name = args[0];
        } else {
            Scanner in = new Scanner(System.in);
            System.out.println("请输入类名(例如: java.util.Date :)");
            name = in.next();
        }

        // 打印类名和超类的类名
        Class<?> cl = Class.forName(name);
        Class<?> superCl = cl.getSuperclass();
        String modifiers = Modifier.toString(cl.getModifiers());
        if (modifiers.length() > 0) {
            System.out.print(modifiers + " ");
        }
        System.out.print("class " + name);
        if (superCl != null && superCl != Object.class) {
            System.out.print(" extends " + superCl.getName());
        }
        System.out.print("\n{\n");
        printConstructors(cl);
        System.out.println();
        printMethods(cl);
        System.out.println();
        printFields(cl);
        System.out.println("}");
    }

    /**
     * 打印一个类的所有构造器
     *
     * @param cl cl是一个类
     */
    public static void printConstructors(Class<?> cl) {
        Constructor<?>[] constructors = cl.getDeclaredConstructors();

        for (Constructor<?> c : constructors) {
            String name = c.getName();
            System.out.print("  ");
            String modifiers = Modifier.toString(c.getModifiers());
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            System.out.print(name + "(");

            // 打印参数类型
            Class<?>[] parameterTypes = c.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                if (i > 0) {
                    System.out.print(", ");
                }
                System.out.print(parameterTypes[i].getName());
            }
            System.out.println(");");
        }
    }

    /**
     * 打印一个类的所有方法
     *
     * @param cl cl是一个类
     */
    public static void printMethods(Class<?> cl) {
        Method[] methods = cl.getDeclaredMethods();

        for (Method m : methods) {
            Class<?> returnType = m.getReturnType();
            String name = m.getName();

            System.out.print("   ");
            // 打印修饰符，返回类型与方法名
            String modifiers = Modifier.toString(m.getModifiers());
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            System.out.print(returnType.getName() + " " + name + "(");

            // 打印参数类型
            Class<?>[] parameterTypes = m.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                if (i > 0) {
                    System.out.print(", ");
                }
                System.out.print(parameterTypes[i].getName());
            }
            System.out.println(");");
        }
    }

    /**
     * 打印一个类的所有字段
     *
     * @param cl cl是一个类
     */
    public static void printFields(Class<?> cl) {
        Field[] fields = cl.getDeclaredFields();

        for (Field f : fields) {
            Class<?> type = f.getType();
            String name = f.getName();
            System.out.print("   ");
            String modifiers = Modifier.toString(f.getModifiers());
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            System.out.println(type.getName() + " " + name + ";");
        }
    }
}
