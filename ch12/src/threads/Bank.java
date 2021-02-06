package threads;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 银行类，用于统计金额、转账
 *
 * @author chenjianglin
 * @date 2021/2/5
 * @since 1.0.0
 **/
public class Bank {
    /* 账户数 */
    private final double[] accounts;

    /* 初始化账户数 */
    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
    }

    private ReentrantLock lock = new ReentrantLock();

    public  void transfer(int from, int to, double amount) {
        lock.lock();
        try {
            if (accounts[from] < amount) {
                return;
            }
            System.out.print(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d ", amount, from, to);
            accounts[to] += amount;
        /*  反编译字节码得到的机器语言  展示的指令  可以知道一个新增动作由多条指令来完成，却可以被中断
        65: aload_0
        66: getfield      #2                  // Field accounts:[D
        69: iload_2
        70: dup2
        71: daload
        72: dload_3
        73: dadd
        74: dastore
        * */
            System.out.printf(" 账户%d的余额: %10.2f ",from,accounts[from]);
            System.out.printf(" 账户%d的余额: %10.2f ",to,accounts[to]);
            System.out.printf(" 总金额: %10.2f%n ", getTotalBalance());
        } catch (Exception e) {
//            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    /**
     * 获取总的余额
     *
     * @return 返回账户余额
     */
    public double getTotalBalance() {
        double sum = 0;
        for (double account : accounts) {
            sum += account;
        }
        return sum;
    }

    /**
     * 获取银行的用户数
     *
     * @return 用户数
     */
    public int size() {
        return accounts.length;
    }
}
