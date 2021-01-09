package singletonpattern;

/**
 * "懒汉式"单例实现：这里的懒汉得名的由来式因为它的延迟实例化(lazy instantiaze)
 * 缺点：线程不安全，多个线程一起操作时，线程执行顺序会导致实例化出多个对象。
 *
 * @author jianglinChen
 * @date 2021/1/9
 * @since 1.0.0
 */
public class Lazy {
    /* 私有的Lazy类型的属性记录实例对象 */
    private static Lazy uniqueInstance;

    /* 私有的构造器，防止其他类实例化Lazy类，只有本类才能调用构造器 */
    private Lazy() {
    }

    /* 公开静态的getUniqueInstance()方法，暴露出去来获取Lazy类的实例 */
    public static Lazy getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Lazy();
        }
        return uniqueInstance;
    }
}