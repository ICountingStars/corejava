package textFile;

/**
 * TODO
 *
 * @author chenjianglin
 * @date 2021/6/24
 * @since 1.0.0
 **/
public class GetClass {
    public static void main(String[] args) {
        // 1. 获取类运行时的的Class instance 通过Object类中的getClass()方法
        Number n = 0;
        Class<? extends Number> nClass = n.getClass();//返回一个Class类型的实例
        System.out.println(nClass.getClass().getName() + " " + nClass.getName());

        // 如果类在一个包里，包的名字也作为类名的一部分
        GetClass aClass = new GetClass();
        Class<? extends GetClass> getClass = aClass.getClass();
        System.out.println(getClass.getName());// 输出: textFile.GetClass

        // 2. 获取类运行时的Class instance 使用静态方法forName()获取类对应的Class instance
        try {
            String getClassName = "textFile.GetClass";
            Class<?> name = Class.forName(getClassName);
            System.out.println(name); // 输出: class textFile.GetClass
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 3. 假设T是任意的Java类型(或void关键字),通过T.class获取类对应的Class instance
        Class<Double> doubleClass = Double.class;
        System.out.println(doubleClass); // 输出: class java.lang.Double

        // Tip: 一个Class对象实际上表示的是一个类型，这可能是类，也可能不是类。
        // eg: int不是类,但int.class是一个Class类型的对象
        Class<Integer> integerClass = int.class; // <--int.class 是一个Class类型的对象
        System.out.println(integerClass); // 输出: int
    }
}
