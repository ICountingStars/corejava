package singletonpattern.threadsafe;

/**
 * 解决"懒汉式"线程安全的单例实现2
 * 减少使用同步和耗时
 *
 * @author jianglinChen
 * @date 2021/1/9
 * @since 1.0.0
 */
public class SafeLazySecond {
    /* volatileJDK>=1.5 当uniqueInstance变量被实例化时，多个线程正确地处理uniqueInstance变量 */
    private volatile static SafeLazySecond uniqueInstance;

    private SafeLazySecond() {
    }

    public static SafeLazySecond getUniqueInstance() {
        if (uniqueInstance == null) {
            synchronized (SafeLazySecond.class) {
                if (uniqueInstance == null) {
                    return uniqueInstance = new SafeLazySecond();
                }
            }
        }
        return uniqueInstance;
    }
}
