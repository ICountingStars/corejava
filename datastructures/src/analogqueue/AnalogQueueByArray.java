package analogqueue;

import java.util.Scanner;

/**
 * 一次性队列
 *
 * @author chenjianglin
 * @date 2021/7/4
 * @since 1.0.0
 **/
public class AnalogQueueByArray {
    public static void main(String[] args) {
        // 创建一个队列
        ArrayQueue queue = new ArrayQueue(3);
        char key;
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("q(quit):获取队列");
            System.out.println("h(head):获取队列头部");
            System.out.println("e(exit):退出队列");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数");
                    try {
                        queue.addQueue(scanner.nextInt());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'q':
                    try {
                        System.out.printf("取出的数据是%d\n", queue.quitQueue());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        System.out.printf("队列头数据是%d\n", queue.showFront());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    loop = false;
                    scanner.close();
                    break;
                default:
                    break;
            }
        }
    }
}

class ArrayQueue {
    private int maxSize;// 队列最大值
    private int front;// 队列头部
    private int rear;// 队列尾部
    private int[] arr;// 存储的队列的数据

    // 初始化一个队列
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        front = -1;
        rear = -1;
        arr = new int[maxSize];
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return front == rear;
    }

    // 判断队列是否为满
    public boolean isFull() {
        return maxSize - 1 == rear;
    }

    // 往队列添加数据
    public void addQueue(int n) {
        if (isFull()) {
            throw new RuntimeException("队列已满");
        }
        arr[++rear] = n;
    }

    // 队列中的数据出队列
    public int quitQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列已空");
        }
        return arr[++front];
    }

    // 展示队列
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列已空");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    // 展示队列的头部位置
    public int showFront() {
        if (isEmpty()) {
            throw new RuntimeException("队列已空");
        }
        return arr[++front];
    }
}
