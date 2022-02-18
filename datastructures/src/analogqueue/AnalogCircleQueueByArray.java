package analogqueue;

import java.util.Scanner;

/**
 * 数组方式实现环形队列
 *
 * @author chenjianglin
 * @date 2021/7/4
 * @since 1.0.0
 **/
public class AnalogCircleQueueByArray {
    public static void main(String[] args) {
        // 创建一个队列
        CircleQueue queue = new CircleQueue(4);
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
                    try {
                        queue.showCircleQueue();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'a':
                    System.out.println("请输入一个数");
                    try {
                        queue.addCircleQueue(scanner.nextInt());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'q':
                    try {
                        System.out.printf("取出的数据是%d\n", queue.quitCircleQueue());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        System.out.printf("队列头数据是%d\n", queue.getCircleHead());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    loop = false;
                    scanner.close();
                    System.out.println("退出程序");
                    break;
                default:
                    break;
            }
        }
    }
}

class CircleQueue {
    private int maxSize;// 队列最大值
    private int front;// 队列头部
    private int rear;// 队列尾部
    private int[] arr;// 存储的队列的数据

    public CircleQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    // 判断环形队列是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    // 判断环形队列是否空
    public boolean isEmpty() {
        return rear == front;
    }

    // 添加数据到环形队列
    public void addCircleQueue(int n) {
        if (isFull()) {
            throw new RuntimeException("环形队列已满");
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    // 从环形队列中取出数据
    public int quitCircleQueue() {
        if (isEmpty()) {
            throw new RuntimeException("环形队列已空");
        }
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    // 获取环形队列到有效个数
    public int size() {
        return (rear - front + maxSize) % maxSize;
    }

    // 显示队列
    public void showCircleQueue() {
        if (isEmpty()) {
            throw new RuntimeException("环形队列已空");
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    // 显示环形队列的头元素
    public int getCircleHead() {
        if (isEmpty()) {
            throw new RuntimeException("环形队列已空");
        }
        return arr[front];
    }
}
