package threads;

/**
 * 1.什么是线程？
 * 1.1 线程就是程序执行的基本单元。可以看作是人体中的一个细胞。
 * 2.什么是多线程？
 * 2.1 一个程序可以执行多个线程，这程序就叫做多线程。
 * 3.多进程与多线程之间的本质区别？
 * 3.1 每一个进程都拥有自己都一套变量，而线程共享数据。
 *
 * waring: 不要调用Thread类或Runnable对象的run方法。
 * 直接调用run方法只会在同一个线程中执行这个任务，而没用启动新的线程。
 * 应当调用Thread.start方法，这会创建一个执行run方法都新线程
 *
 * @author chenjianglin
 * @date 2021/2/5
 * @since 1.0.0
 **/
public class ThreadTest {
    public static final int DELAY = 10;
    public static final int STEPS = 100;
    public static final double MAX_AMOUNT = 1000;

    public static void main(String[] args) {
        Bank bank = new Bank(4, 100000);
        Runnable task1 = () -> {
            try {
                for (int i = 0; i < STEPS; i++) {
                    double amount = Math.random() * MAX_AMOUNT;
                    bank.transfer(0, 1, amount);
                    Thread.sleep((int) (Math.random() * DELAY));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable task2 = () -> {
            try {
                for (int i = 0; i < STEPS; i++) {
                    double amount = Math.random() * MAX_AMOUNT;
                    bank.transfer(2, 3, amount);
                    Thread.sleep((int) (Math.random() * DELAY));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        new Thread(task1).start();
        new Thread(task2).start();
    }
}
