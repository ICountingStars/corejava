package recoverlinklist;


/**
 * @author chenjianglin
 * @date 2021/7/11
 * @since 1.0.0
 **/
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        Node songJiang = new Node(1, "宋江", "及时雨");
        Node luJunYi = new Node(2, "卢俊义", "玉麒麟");
        Node wuYong = new Node(3, "吴用", "智多星");
        Node linCong = new Node(4, "林冲", "豹子头");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.addNode(songJiang);
//        singleLinkedList.addNode(luJunYi);
//        singleLinkedList.addNode(wuYong);
//        singleLinkedList.addNode(linCong);
        singleLinkedList.orderAddNode(songJiang);
        singleLinkedList.orderAddNode(luJunYi);
        singleLinkedList.orderAddNode(linCong);
        singleLinkedList.orderAddNode(wuYong);
        singleLinkedList.orderAddNode(linCong);

        singleLinkedList.showLinkList();
//
        Node newNode = new Node(2, "小卢", "玉麒麟～～");
        singleLinkedList.updateNode(newNode);
        System.out.println("修改后的情况");
        singleLinkedList.showLinkList();
//
        singleLinkedList.deleteNode(1);
        singleLinkedList.deleteNode(4);
        singleLinkedList.deleteNode(2);
        singleLinkedList.deleteNode(3);
        System.out.println("删除后的链表情况");
        singleLinkedList.showLinkList();
    }
}

// 单向链表
class SingleLinkedList {
    static final Node head = new Node(0, "", "");// 头节点

    // 新增节点
    public void addNode(Node newNode) {
        // 添加一个临时变量用于操作节点
        Node temp = head;
        // 判断连标是否为空
        while (true) {
            // 找到链表的尾部，插入节点
            if (temp.next == null) {
                temp.next = newNode;
                break;
            }
            // 未找到链表的尾部时，temp指针后移
            temp = temp.next;
        }
    }

    // 按顺序新增节点
    public void orderAddNode(Node newNode) {
        Node temp = head;
        boolean flag = false;// 默认设置标志flag false表示未找到相同节点
        while (true) {
            if (temp.next == null) {
                // 判断temp的下一个节点是否为空
                break;
            }
            if (temp.next.getNo() > newNode.getNo()) {
                // 节点的no小于temp.next节点的no则，满足新增条件
                break;
            } else if (temp.next.getNo() == newNode.getNo()) {
                // no相同则表示为相同的节点
                flag = true;
                break;
            }
            // 指针往后移动
            temp = temp.next;
        }
        if (flag) {
            System.out.printf("节点 %d 已存在,添加失败\n", newNode.getNo());
        } else {
            // 做顺序插入
            newNode.next = temp.next;
            temp.next = newNode;
        }
    }

    // 修改节点
    public void updateNode(Node newNode) {
        if (head.next == null) {// 判断是否为空链表
            System.out.println("空链表");
        }
        Node temp = head;
        boolean flag = false;
        // 遍历链表，找到这个节点
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.next.getNo() == newNode.getNo()) {
                flag = true;// no 与 no 相同表示节点相同,找到此节点
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            // 修改这个节点
            temp.next.setName(newNode.getName());
            temp.next.setNickName(newNode.getNickName());
        } else {
            System.out.printf("节点%d不存在", newNode.getNo());
        }
    }

    // 删除节点
    public void deleteNode(int no) {
        Node temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                System.out.println("空链表");
                return;
            }
            if (temp.next.getNo() == no) { // 找到节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("节点%d不存在", no);
        }
    }

    // 展示链表
    public void showLinkList() {
        if (head.next == null) {
            System.out.println("空链表");
            return;
        }
        Node temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

}

// 节点数据结构
class Node {
    private int no;
    private String name;
    private String nickName;
    Node next;

    public Node(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    public int getNo() {
        return no;
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
        return "Node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}