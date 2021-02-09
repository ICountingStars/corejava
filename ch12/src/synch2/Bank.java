package synch2;

import java.util.Arrays;

/**
 * synchronized 关键字来解决银行转账带来的线程不安全问题
 *
 * @author chenjianglin
 * @date 2021/2/9
 * @since 1.0.0
 **/
public class Bank {
    private final double[] accounts;

    public Bank(int n,double initialBalance) {
        accounts = new double[n];
        Arrays.fill(accounts,initialBalance);
    }

    public synchronized void transfer(int from, int to, double amount) throws InterruptedException {
        while (accounts[from]<amount) wait();
        System.out.print(Thread.currentThread());
        accounts[from] -= amount;
        System.out.printf(" %10.2f from %d to %d ", amount, from, to);
        accounts[to] += amount;
        System.out.printf(" 总金额: %10.2f%n ", getTotalBalance());
        notifyAll();
    }

    public synchronized double getTotalBalance() {
        double sum = 0;
        for (double a : accounts) {
            sum += a;
        }
        return sum;
    }

    public int size() {
        return accounts.length;
    }
}
