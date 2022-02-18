package recoveranalogqueue;

import java.util.Scanner;

/**
 * TODO
 *
 * @author chenjianglin
 * @date 2021/7/5
 * @since 1.0.0
 **/
public class AnalogQueueByArray {
    public static void main(String[] args) {
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
                    try {
                        queue.showQueue();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
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
                        System.out.printf("取出的数据是%d\n", queue.getQueue());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        System.out.printf("队列头数据是%d\n", queue.headQueue());
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

// 一次性队列
class ArrayQueue {
    private int maxSize;
    private int front;
    private int rear;
    private int[] value;

    // 让头指针、尾指针初始位置设置为-1
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        front = -1;
        rear = -1;
        value = new int[maxSize];
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return front == rear;
    }

    // 判断队列是否为满
    public boolean isFull() {
        return maxSize == rear + 1;// 这样判断有缺陷
    }

    // 向队列中添加元素
    public void addQueue(int value) {
        if (isFull()) {
            throw new RuntimeException("队列已满");
        }
        this.value[++rear] = value;
    }

    // 从队列中获取元素
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列已空");
        }
        return this.value[++front];
    }

    // 展示队列
    public void showQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列已空");
        }
        for (int i = 0; i < value.length; i++) {
            System.out.printf("value[%d]=%d\n", i, value[i]);
        }
    }

    // 展示队列的头部元素
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列已空");
        }
        return this.value[front + 1];
    }
}
