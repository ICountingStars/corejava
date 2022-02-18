package linklist;

/**
 * Josephu
 *
 * @author chenjianglin
 * @date 2021/7/15
 * @since 1.0.0
 **/
public class JosephDemo {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addChild(10);

        circleSingleLinkedList.showCircleLinkedList();
        circleSingleLinkedList.countChild(1, 6, 20);
        //6->2->9->7->5->8->1->10->4->3
    }
}

class CircleSingleLinkedList {
    private Child first;// 创建一个first节点

    //创建环形链表
    public void addChild(int nums) {
        if (nums < 1) {
            System.out.println("nums数值不正确");
            return;
        }
        Child currentChild = null;
        for (int i = 1; i <= nums; i++) {
            Child newChild = new Child(i);
            if (i == 1) {
                first = newChild;
                first.setNext(first);
                currentChild = first;
            } else {
                currentChild.setNext(newChild);
                newChild.setNext(first);
                currentChild = newChild;
            }
        }
    }

    // 遍历环形链表
    public void showCircleLinkedList() {
        // 判断是否为空
        if (first == null) {
            System.out.println("空链表");
            return;
        }
        Child currentChild = first;// 临时变量
        while (true) {
            System.out.printf("小孩编号 %d \n", currentChild.getNo());
            if (currentChild.getNext() == first) {
                break;
            }
            currentChild = currentChild.getNext();
        }
    }

    public void countChild(int startNo, int countNum, int nums) {
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("传参有误");
            return;
        }
        Child helper = first;
        while (true) {// 最后一个节点
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        // 报数前，移动到从第个孩子开始数的位置
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        // 报数时，同时移动，报数次数-1 次
        while (true) {
            if (helper == first) {//只剩最后一个
                System.out.printf("%d", first.getNo());
                break;
            }
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.printf("%d->", first.getNo());
            // 小孩出圈
            first = first.getNext();
            helper.setNext(first);
        }
    }
}

class Child {
    private int no;
    private Child next;

    public Child(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Child getNext() {
        return next;
    }

    public void setNext(Child next) {
        this.next = next;
    }
}
