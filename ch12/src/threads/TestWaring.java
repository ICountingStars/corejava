package threads;

/**
 * 验证结论 {@link ThreadTest} waring 部分。
 * 从一下的代码执行中可以看出来，执行他们的线程都是main线程。
 *
 * @author chenjianglin
 * @date 2021/2/5
 * @since 1.0.0
 **/
public class TestWaring {
    public static void main(String[] args) {
        System.out.println("直接调用Thread类"+Thread.currentThread());
        Runnable runnable = () -> System.out.println("调用Runnable的run方法"+Thread.currentThread());
        runnable.run();

//        执行结果  都是同一个main线程，并没有起来一个新的线程
//        直接调用Thread类Thread[main,5,main]
//        调用Runnable的run方法Thread[main,5,main]

    }

}
