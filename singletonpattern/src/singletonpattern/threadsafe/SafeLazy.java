package singletonpattern.threadsafe;

/**
 * 解决"懒汉式"线程安全的单例实现1
 * 公开静态方法上上锁，
 * 优点：简单
 * 缺点：性能底下，开销大
 *
 * @author jianglinChen
 * @date 2021/1/9
 * @since 1.0.0
 */
public class SafeLazy {
    private static SafeLazy uniqueInstance;

    private SafeLazy() {
    }

    public static synchronized SafeLazy getInstance() {
        if (uniqueInstance == null) {
            return uniqueInstance = new SafeLazy();
        }
        return uniqueInstance;
    }
}
