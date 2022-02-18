package recoverlinklist;


/**
 * TODO
 *
 * @author chenjianglin
 * @date 2021/7/14
 * @since 1.0.0
 **/
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleNode songJiang = new DoubleNode(1, "宋江", "及时雨");
        DoubleNode luJunYi = new DoubleNode(2, "卢俊义", "玉麒麟");
        DoubleNode wuYong = new DoubleNode(3, "吴用", "智多星");
        DoubleNode linCong = new DoubleNode(4, "林冲", "豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
//        doubleLinkedList.addDoubleNode(linCong);
//        doubleLinkedList.addDoubleNode(songJiang);
//        doubleLinkedList.addDoubleNode(wuYong);
//        doubleLinkedList.addDoubleNode(luJunYi);
        System.out.println("顺序添加");
        doubleLinkedList.orderAddDoubleLinkedList(linCong);
        doubleLinkedList.orderAddDoubleLinkedList(songJiang);
        doubleLinkedList.orderAddDoubleLinkedList(wuYong);
        doubleLinkedList.orderAddDoubleLinkedList(luJunYi);

        doubleLinkedList.showDoubleLinkedList();
        System.out.println("修改节点");
        doubleLinkedList.updateDoubleLinkedList(new DoubleNode(2, "小卢", "!!"));
        doubleLinkedList.showDoubleLinkedList();

        System.out.println("删除节点");
        doubleLinkedList.deleteDoubleLinkedList(2);
        doubleLinkedList.showDoubleLinkedList();
        doubleLinkedList.deleteDoubleLinkedList(1);
        doubleLinkedList.deleteDoubleLinkedList(3);
        doubleLinkedList.deleteDoubleLinkedList(4);
        doubleLinkedList.showDoubleLinkedList();

    }
}

class DoubleLinkedList {
    static DoubleNode head = new DoubleNode(0, "", "");

    // 获取头节点
    public DoubleNode getHead() {
        return head;
    }

    // 遍历双向链表的 与单向链表相同
    public void showDoubleLinkedList() {
        if (head.next == null) {
            System.out.println("空链表");
            return;
        }
        DoubleNode temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    // 添加双向链表的节点(无序)
    public void addDoubleNode(DoubleNode newNode) {
        DoubleNode temp = head;
        while (true) {
            if (temp.next == null) {// 遍历找到链表的最后一个节点
                break;
            }
            temp = temp.next;
        }
        // 添加节点到
        temp.next = newNode;
        newNode.pre = temp;
    }

    // 添加双向链表的节点(有序)
    public void orderAddDoubleLinkedList(DoubleNode newNode) {
        DoubleNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {// 遍历找到链表的最后一个节点
                break;
            }
            if (temp.next.getNo() > newNode.getNo()) {
                break;
            } else if (temp.next.getNo() == newNode.getNo()) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            // 节点已存在
            System.out.printf("节点 %d 已存在", newNode.getNo());
        } else {
            if (temp.next != null) {// 得在中间添加进去
                newNode.next = temp.next;
                newNode.pre = temp.next.pre;//newNode.pre = temp;
                temp.next.pre = newNode;
                temp.next = newNode;
            } else { // temp.next == null 说明是最后一个节点，直接添加
                temp.next = newNode;
                newNode.pre = temp;
            }
        }
    }

    // 修改双向链表的节点
    public void updateDoubleLinkedList(DoubleNode newNode) {
        if (head.next == null) {
            System.out.println("空链表");
            return;
        }
        // 判断链表是否为空
        DoubleNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {//头节点的情况
                System.out.printf("节点 %d 不存在\n", newNode.getNo());
                break;
            }
            if (temp.next.getNo() == newNode.getNo()) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //修改节点
        if (flag) {
            temp.next.setName(newNode.getName());
            temp.next.setNickName(newNode.getNickName());
        }
    }

    // 删除双向链表的节点
    public void deleteDoubleLinkedList(int no) {
        // 遍历链表，找到这个节点(非最后一个节点)，然后删除
        //temp.next.pre = temp.pre ; temp.pre.next = temp.next
        if (head.next == null) {
            System.out.println("空链表");
            return;
        }
        DoubleNode temp = head.next;
        boolean flag = false;
        while (temp != null) {
            if (temp.getNo() == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            // 找到此节点
            temp.pre.next = temp.next;
            if (temp.next != null) {// 最后一个节点时 temp.next 要做非空校验
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("节点 %d 不存在\n", no);
        }
    }
}

class DoubleNode {
    private int no;
    private String name;
    private String nickName;
    DoubleNode pre;
    DoubleNode next;

    public DoubleNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "DoubleNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}