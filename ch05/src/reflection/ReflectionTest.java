package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

/***
 * java.lang.Class 总结 API
 * · Field[] getFields();
 * · Filed[] getDeclaredFie1ds();getFields 方法将返回一个包含Field 对象的数组， 这些对象记录了这个类或其超类的
 * 公有域。getDeclaredField 方法也将返回包含Field 对象的数组， 这些对象记录了这个
 * 类的全部域。如果类中没有域， 或者Class 对象描述的是基本类型或数组类型， 这些
 * 方法将返回一个长度为0 的数组。
 * · Method[] getMethods();
 * · Method[] getDeclareMethods();返回包含Method 对象的数组： getMethods 将返回所有的公有方法， 包括从超类继承
 * 来的公有方法；getDeclaredMethods 返回这个类或接口的全部方法， 但不包括由超类
 * 继承了的方法。
 * · Constructor[] getConstructors();
 * · Constructor[] getDeclaredConstructors();返回包含Constructor 对象的数组， 其中包含了Class 对象所描述的类的所有公有构造
 * 器（getConstructors ) 或所有构造器（getDeclaredConstructors)。
 *
 * java.lang.reflect.Constructor API
 * •Class getDeclaringClass( )
 * 返冋一个用于描述类中定义的构造器、方法或域的Class 对象。
 * •Cl ass[ ] getExcepti onTypes ( ) ( 在Constructor 和Method 类中）
 * 返回一个用于描述方法抛出的异常类型的Class 对象数组。
 * •int getModifiers( )
 * 返回一个用于描述构造器、方法或域的修饰符的整型数值。使用Modifier 类中的这个
 * 方法可以分析这个返回值。
 * •String getName( )
 * 返冋一个用于描述构造器、方法或域名的字符串。
 * •Class[ ] getParameterTypes ( ) ( 在Constructor 和Method 类中）
 * 返回一个用于描述参数类型的Class 对象数组。
 * •Class getReturnType( ) ( 在Method 类中）
 * 返回一个用于描述返H类型的Class 对象。
 *
 * java.lang.reflect.Modifier
 * •static String toString( int modifiers )
 * 返回对应modifiers 中位设置的修饰符的字符串表7K。
 * •static boolean isAbstract ( int modifiers )
 * •static boolean isFinal ( int modifiers )
 * •static boolean islnterface( int modifiers )
 * •static boolean isNative( int modifiers )
 * •static boolean isPrivate( int modifiers )
 * •static boolean isProtected( int modifiers )
 * •static boolean isPublic( int modifiers )
 * •static boolean isStatic( int modifiers )
 * •static boolean isStrict( int modifiers )
 * •static boolean isSynchronized( int modifiers )
 * •static boolean isVolati 1e( int modifiers )
 * 这些方法将检测方法名中对应的修饰符在modffiers 值中的位r
 *
 * @author jianglinChen
 * @Date 2020/12/3 9:54
 * @since 1.0.0
 */
public class ReflectionTest {
    public static void main(String[] args) {
        String name;
        if (args.length>0) name=args[0];
        else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter class name (e.g java.util.Date):");
            name = scanner.next();
        }

        try {
            Class cl = Class.forName(name);
            Class superCl = cl.getSuperclass();
            String modifiers = Modifier.toString(cl.getModifiers());
            if (modifiers.length()>0) System.out.print(modifiers + " ");
            System.out.print("class "+name);
            if (superCl != null) {
                System.out.print(" extends " + superCl.getName());
            }
            System.out.println("\n{\n");
            printConstructors(cl);
            System.out.println();
            printMethods(cl);
            System.out.println();
            printFields(cl);
            System.out.println("}");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public static void printConstructors(Class cl){
        Constructor[] constructors = cl.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            String name = constructor.getName();
            System.out.print("   ");
            String s = Modifier.toString(constructor.getModifiers());
            if (s.length()>0) System.out.print(s+" ");
            System.out.print(name +"(");
            Class[] types = constructor.getParameterTypes();
            for (int i = 0; i < types.length; i++) {
                if (i >0) System.out.print(",");
                System.out.print(types[i].getName());
            }
            System.out.println(");");
        }
    }

    public static void printMethods(Class cl){
        Method[] methods = cl.getMethods();
        for (Method method : methods) {
            Class<?> type = method.getReturnType();
            String name = method.getName();
            System.out.print("   ");
            String s = Modifier.toString(method.getModifiers());
            if (s.length()>0) System.out.print(s+"  ");
            System.out.print(type.getName()+" "+name+"(");
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                if (i>0) System.out.print(", ");
                System.out.print(parameterTypes[i].getName());
            }
            System.out.println(");");
        }
    }

    public static void printFields(Class cl){
        Field[] fields = cl.getFields();
        for (Field field : fields) {
            Class<?> type = field.getType();
            String name = type.getName();
            System.out.print("  ");
            String s = Modifier.toString(field.getModifiers());
            if (s.length()>0) System.out.print(s+" ");
            System.out.println(type.getName()+" "+name+";");
        }
    }
}
