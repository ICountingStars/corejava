package unsynch;

import threads.Bank;

import java.util.Random;

/**
 * lesson2
 * 1. 线程状态 {@link Thread}
 *    - new (新建)
 *    - runnable (可运行)
 *    - blocked (阻塞)
 *    - waiting (等待)
 *    - timed-waiting (计时等待)
 *    - terminated (终止)
 * 2. 线程属性
 *    - 中断线程
 *       中断线程的条件: 1.在执行run方法的最后一条语句后在执行return语句 2.出现异常未捕获
 *    - 守护线程
 *       守护线程的作用就是为其他线程提供服务。标准 setDaemon(true)
 *    - 线程名
 *       默认情况线程有容易记住的名字
 *    - 未捕获异常的处理器
 *       未捕获的RuntimeException运行时异常会终止线程,而在线程死亡时,会将异常传递到一个用于处理未捕获异常到处理器。
 *       此处理器必须满足一个条件，就是实现了接口Thread.UncaughtExceptionHandler
 *       此外，没有设置处理器，默认处理器为null。单个线程除外，他要是没有设置的话，处理器会时该线程的ThreadGroup对象
 *    - 线程优先级
 *       Java程序设计中,每个线程都有一个优先级。
 *       默认下，在继承结构中，子类会继承父类的优先级
 *       setPriority()设置优先级 [1,10]
 *  3. 同步
 *     出现竞态条件的本质是，操作系统是抢占式的调度方式。
 *     一个新增动作，其底层是由多条指令来完成的涉及到寄存、计算和存储。
 *     这个过程可以被随时中断，导致正常的数据访问次序被打乱，而最终出现数据异常的现象。
 *
 *     Java处理这情况可以采用以下两种方式
 *        3.1 synchronized 关键字
 *        3.2 ReentrantLock类
 *
 * @author chenjianglin
 * @date 2021/2/6
 * @since 1.0.0
 **/
public class UnsynchBankTest {
    public static final int NACCOUNTS = 100;
    public static final double INITIAL_BALANCE = 1000;
    public static final double MAX_AMOUNT = 1000;
    public static final int DELAY = 10;
    public static void main(String[] args) {
        Bank bank = new Bank(NACCOUNTS, INITIAL_BALANCE);
        for (int i = 0; i < NACCOUNTS; i++) {
            int fromAccount = i;
            Runnable r = () ->{
                try {
                    while (true) {
                        int toAccount = (int) (bank.size() * Math.random());
                        double amount = MAX_AMOUNT * Math.random();
                        bank.transfer(fromAccount, toAccount, amount);
                        Thread.sleep((int) (DELAY * Math.random()));
                    }
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                }
            };
            new Thread(r).start();
        }
    }
}
