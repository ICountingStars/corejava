package synch2;

/**
 * 模拟死锁
 * 死锁情况
 * 1.账户1:¥200
 * 2.账户2:¥300
 * 3.线程1:从账户1转¥300到账户2
 * 4.线程2:从账户2¥400到账户1
 *
 *
 *
 * @author chenjianglin
 * @date 2021/2/9
 * @since 1.0.0
 **/
public class SynchBankTest2 {
    public static final int NACCOUNTS = 10;//修改为10
    public static final double INITIAL_BALANCE = 1000;
    public static final double MAX_AMOUNT = 2000;//修改为2000
    public static final int DELAY = 10;
    public static void main(String[] args) {
        synch2.Bank bank = new synch2.Bank(NACCOUNTS, INITIAL_BALANCE);
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
