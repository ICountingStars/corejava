package recoveranalogqueue2;

import java.util.Scanner;

/**
 * TODO
 *
 * @author chenjianglin
 * @date 2021/7/6
 * @since 1.0.0
 **/
public class AnalogCircleQueueByArray {
    public static void main(String[] args) {
        CircleQueue queue = new CircleQueue(5);
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
                        System.out.printf("队列头数据是%d\n", queue.headShow());
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
    private int maxSize;
    private int front;
    private int rear;
    private int[] value;

    public CircleQueue(int maxSize) {
        this.maxSize = maxSize;
        this.value = new int[maxSize];
    }
    // 1. 环形队列中有一约定空一位置，这是设计的巧妙之处
    // 2. 环形队列中必有一等式表示队列中的有效值 (rear+maxSize-front)% maxSize
    // 3. 环形队列中判断队列为空的条件是 rear == front
    // 4. 环形队列中判断队列为满的添加是 (rear+1) %maxSize == front

    // 判断环形队列是否空
    public boolean isEmpty() {
        return rear == front;
    }

    // 判断环形队列是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    // 获取环形队列中的有效值数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    // 向环形队列添加元素
    public void addQueue(int value) {
        if (isFull()) {
            throw new RuntimeException("队列已满");
        }
        this.value[rear] = value;
        rear = (rear + 1) % maxSize;
    }

    // 从环形队列中获取元素
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列已空");
        }
        int tempValue = this.value[front];
        front = (front + 1) % maxSize;
        return tempValue;
    }

    // 展示环形队列
    public void showQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列已空");
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("value[%d]=%d\n", i % maxSize, this.value[i % maxSize]);
        }
    }

    // 展示环形队列的头元素
    public int headShow() {
        if (isEmpty()) {
            throw new RuntimeException("队列已空");
        }
        return this.value[front];
    }

}
