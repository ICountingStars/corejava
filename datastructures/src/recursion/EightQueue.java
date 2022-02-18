package recursion;

/**
 * TODO
 *
 * @author chenjianglin
 * @date 2021/7/18
 * @since 1.0.0
 **/
public class EightQueue {
    int max = 8;
    int[] array = new int[max];
    static int COUNT = 0;
    static int JUDGE_COUNT = 0;

    public static void main(String[] args) {
        EightQueue eightQueue = new EightQueue();
        eightQueue.check(0);
        System.out.printf("一共有%d解法\n", COUNT);
        System.out.printf("一共判断%d次\n", JUDGE_COUNT);
    }

    private void check(int n) {
        if (n == max) {
            print();
            return;
        }
        for (int i = 0; i < max; i++) {
            array[n] = i;
            if (judge(n)) { // 不冲突
                check(n + 1);
            }
        }
    }

    private void print() {
        COUNT++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    private boolean judge(int n) {
        JUDGE_COUNT++;
        for (int i = 0; i < n; i++) {
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

}
