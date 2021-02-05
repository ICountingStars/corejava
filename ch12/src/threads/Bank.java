package threads;

import java.util.Arrays;

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

    public void transfer(int from, int to, double amount) {
        if (accounts[from] < amount) {
            return;
        }
        System.out.print(Thread.currentThread());
        accounts[from] -= amount;
        System.out.printf(" %10.2f from %d to %d ", amount, from, to);
        accounts[to] += amount;
        System.out.printf(" 账户%d的余额: %10.2f ",from,accounts[from]);
        System.out.printf(" 账户%d的余额: %10.2f ",to,accounts[to]);
        System.out.printf(" 总金额: %10.2f%n ", getTotalBalance());
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
