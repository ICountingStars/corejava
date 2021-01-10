package singletonpattern;

/**
 * "饿汉式"单例实现
 * 一开时就创建好一个实例对象，
 * 这个也由个缺点，就是一开始就初始化，要是初始化的这个过程十分的浪费资源，而且在整个程序执行过程中都没有去使用到这个实例，那就造成了浪费
 *
 * @author jianglinChen
 * @date 2021/1/9
 * @since 1.0.0
 */
public class Hungry {
    private static Hungry uniqueInstance = new Hungry();

    private Hungry() {

    }

    public static Hungry getInstance() {
        return uniqueInstance;
    }
}
