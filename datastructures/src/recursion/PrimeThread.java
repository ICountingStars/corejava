package recursion;

import org.omg.SendingContext.RunTime;

/**
 * TODO
 *
 * @author chenjianglin
 * @date 2021/8/11
 * @since 1.0.0
 **/
public class PrimeThread extends Thread {
    long minPrime;

    PrimeThread(long minPrime) {
        this.minPrime = minPrime;
    }

    @Override
    public void run() {
        System.out.println("初始化");
    }

    public static void main(String[] args) {
        PrimeThread primeThread = new PrimeThread(143);
        primeThread.start();
    }
}
