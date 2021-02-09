package synch;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 利用锁来解决银行转账时存在的线程安全问题
 * lesson3 条件对象
 *
 * 通常，线程进去临界区后却发现只有满足来某个条件后才能执行。
 * 可以使用一个条件对象来管理那些已经获得来一个锁却不能工作的线程。(条件对象又叫条件变量)
 *
 * 一个锁对象可以有一个或多个相关联的条件对象{@link Condition}。
 * 可以使用newCondition()来获取一个条件对象。
 *
 * 一个线程调用await()方法，它就进入这个条件的等待集.
 * 处于等待集的线程，实际上是保持着非活动状态的，知道另外一个线程在同一条件上调用signalAll方法，这个方法会重新激活等待这个条件的所有线程。
 * 注意了，是激活等待这个条件的所有线程。被激活的线程们是会竞争刚释放出来的锁的。
 *
 * 一个线程调用了await()方法是没办法重新自行激活的。只能寄希望于其他线程。
 * 要是其他线程没有进行激活，将进去死锁现象.
 *
 * 假设其他线程都进入了阻塞状态，此时没有线程可以解除其他线程都阻塞状态，程序将被永远挂起。
 *
 * signal()随机选择等待集都一个线程，并解除这个线程的阻塞状态。
 *
 *
 * @author chenjianglin
 * @date 2021/2/9
 * @since 1.0.0
 **/
public class Bank {
    private final double[] accounts;
    private final Lock bankLock;
    private final Condition sufficientFunds;

    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        Arrays.fill(accounts,initialBalance);
        bankLock = new ReentrantLock();
        sufficientFunds = bankLock.newCondition();
    }

    public void transfer(int from, int to, double amount) throws InterruptedException {
        bankLock.lock();
        try {
            while (accounts[from]< amount) sufficientFunds.await();
            System.out.print(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d ", amount, from, to);
            accounts[to] += amount;
            System.out.printf(" 总金额: %10.2f%n ", getTotalBalance());
            sufficientFunds.signalAll();
        }finally {
            bankLock.unlock();
        }
    }

    public double getTotalBalance(){
        bankLock.lock();
        try {
            double sum = 0;
            for (double a :
                    accounts) {
                sum += a;
            }
            return sum;
        } finally {
            bankLock.unlock();
        }
    }

    public int size(){
        return accounts.length;
    }
}
