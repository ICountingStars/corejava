package recoveranalogqueue;

import java.util.Scanner;

/**
 * TODO
 *
 * @author chenjianglin
 * @date 2021/7/5
 * @since 1.0.0
 **/
public class AnalogCircleQueueByArray {
    public static void main(String[] args) {
        CircleQueueArray queue = new CircleQueueArray(3);
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
                    System.out.println("退出程序");
                    break;
                default:
                    break;
            }
        }
    }
}

class CircleQueueArray {
    private int maxSize;
    private int[] value;
    private int front;
    private int rear;
    // 头部与尾部约定空一个空间
    // front、rear 头与尾指针初始化位置为0
    // 环形队列中有效个数的等式 (rear+maxSize-front) % maxSize
    // 判断队列为空的等式 rear == front
    // 判断队列为满的等式 (rear+1)%maxSize == front


    public CircleQueueArray(int maxSize) {
        this.maxSize = maxSize;
        value = new int[maxSize];
    }

    // 判断队列为空
    public boolean isEmpty() {
        return rear == front;
    }

    // 判断队列为满
    public boolean isFull() {
        // 尾指针加上约定空出来的一个元素的和 与 数组最大值 取模 的结果与头指针相同则为满，反之为空
        return (rear + 1) % maxSize == front;
    }

    // 向队列中添加元素
    public void addQueue(int value) {
        if (isFull()) {
            throw new RuntimeException("队列已满");
        }
        this.value[rear] = value;
        rear = (rear + 1) % maxSize;
    }

    // 获取队列中的元素
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列已空");
        }
        int tempValue = this.value[front];
        front = (front + 1) % maxSize;
        return tempValue;
    }

    // 展示队列
    public void showQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列已空");
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("value[%d]=%d\n", i % maxSize, this.value[i % maxSize]);
        }
    }

    // 展示队列的头元素
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列已空");
        }
        return this.value[front];
    }

    // 获取队列的有效个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }
}
