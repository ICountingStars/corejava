package recoverlinklist2;


import java.util.Stack;

/**
 * TODO
 *
 * @author chenjianglin
 * @date 2021/7/12
 * @since 1.0.0
 **/
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode songJiang = new HeroNode(1, "宋江", "及时雨");
        HeroNode luJunYi = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode wuYong = new HeroNode(3, "吴用", "智多星");
        HeroNode linCong = new HeroNode(4, "林冲", "豹子头");
        SingleLinkList list = new SingleLinkList();
        // 不按顺序添加
//        list.addNode(songJiang);
//        list.addNode(wuYong);
//        list.addNode(luJunYi);
//        list.addNode(linCong);
//        list.showList();
        // 顺序添加
//        list.orderAddNode(songJiang);
//        list.orderAddNode(linCong);
//        list.orderAddNode(wuYong);
//        list.orderAddNode(luJunYi);
//        list.showList();
//        System.out.println("修改节点");
//        list.showList();
//        list.updateNode(new HeroNode(2, "卢哥", "玉麒麟~~"));
//
//        list.deleteNode(1);
//        list.deleteNode(2);
//        System.out.println("删除后展示");
//        list.showList();
//        list.deleteNode(3);
//        System.out.println("再删除后展示");
//
//        list.showList();
//        list.deleteNode(4);
//        list.showList();
//        list.deleteNode(2);


        list.orderAddNode(songJiang);
        list.orderAddNode(luJunYi);
        list.orderAddNode(wuYong);
        list.orderAddNode(linCong);

        System.out.printf("链表有效个数 %d \n",getLinkSize(list.getHeadNode()));

        System.out.println("链表倒数第3个节点： "+findLastLinkList(list.getHeadNode(),2));

        System.out.println("反转链表");
        reverseLinkedList2(list.getHeadNode());
        list.showList();
        // 逆序打印
        System.out.println("逆序打印");
        reversePrintLinkedList(list.getHeadNode());
    }

    // 逆序打印链表
    public static void reversePrintLinkedList(HeroNode head) {
        // 使用stack类
        if (head.next == null) {
            System.out.println("空链表");
            return;
        }
        HeroNode temp = head.next;
        Stack<HeroNode> heroNodeStack = new Stack<>();
        // 遍历链表并压入栈内
        while (temp != null) {
            heroNodeStack.push(temp);
            temp = temp.next;
        }
        // 遍历栈，出栈
        while (heroNodeStack.size()>0) {
            System.out.println(heroNodeStack.pop());
        }
    }

    public static void reverseLinkedList2(HeroNode head) {
        // 链表为空和链表内只有一个元素时 无需反转
        if (head.next==null || head.next.next==null) {
            return;
        }
        HeroNode current = head.next;// 当前节点
        HeroNode next = null; // 原链表存放的临时空间
        HeroNode reverseLinked = new HeroNode(0, "", "");
        while (current != null) {
            next = current.next; // 存放当前节点的下一个节点
            current.next = reverseLinked.next; // 当前节点的下一个指针，指向反转链表的最前端
            reverseLinked.next = current; // 当前节点 指向 反转链表
            current = next;// 原链表向下移动一个节点
        }
        head.next = reverseLinked.next; // 反转后的链表重新指向原链表的头节点
    }

    // 反转链表
    public static void reverseLinkedList(HeroNode head) {
        if (head.next==null || head.next.next==null) { // 没有节点和只有一个节点时不用反转
            return;
        }
        HeroNode current = head.next; // 临时节点
        HeroNode next = null;
        HeroNode reverseList = new HeroNode(0, "", "");
        while (current != null) {
            next = current.next;
            current.next = reverseList.next;
            reverseList.next = current;
            current = next;
        }
        head.next = reverseList.next;
    }

    // 找到链表中的倒数第n个数
    public static HeroNode findLastLinkList(HeroNode head, int index) {
        if (head.next==null) {
            return null;
        }
        int linkSize = getLinkSize(head);
        if (linkSize < 0 || index > linkSize) {
            return null;
        }
        HeroNode current = head.next;
        for (int i = 0; i < linkSize-index; i++) {
            current = current.next;
        }
        return current;
    }

    //获取到单链表中的有效个数
    public static int getLinkSize(HeroNode head) {
        if (head.next==null) {
            return 0;
        }
        int length = 0;
        while (head.next != null) {
            length++;
            head = head.next;
        }
        return length;
    }

}

class SingleLinkList {
    // 头节点
    static final HeroNode headNode = new HeroNode(0, "", "");

    // 获取头节点
    public HeroNode getHeadNode() {
        return headNode;
    }

    // 添加节点入链表
    public void addNode(HeroNode newHeroNode) {
        // 建立临时变量，通过临时变量来遍历链表
        HeroNode temp = headNode;
        while (true) {
            if (temp.next==null) {
                // 链表为空时插入
                break;
            }
            temp = temp.next;
        }
        // 插入
        temp.next = newHeroNode;
    }

    // 顺序添加节点入链表
    public void orderAddNode(HeroNode newHeroNode) {
        // 建立临时变量，通过临时变量来遍历链表
        HeroNode temp = headNode;
        boolean flag = false;// 标志是否找到合适的插入位置
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.getNo() > newHeroNode.getNo()) {
                break;
            } else if (temp.next.getNo() == newHeroNode.getNo()) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            // 已存在节点
            System.out.printf("节点 %d 已存在", newHeroNode.getNo());
        } else {
            //插入
            newHeroNode.next = temp.next;
            temp.next = newHeroNode;
        }
    }

    // 展示链表
    public void showList() {
        if (headNode.next==null) {
            System.out.println("空链表");
            return;
        }
        HeroNode temp = headNode.next;
        while (temp!=null) {
//            if (temp.next==null) {
//                break;
//            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    public void updateNode(HeroNode newHeroNode) {
        HeroNode temp = headNode;
        boolean flag = false; // 用于标记是否找到对应的节点 TRUE表示找到
        while (true) {
            if (temp.next==null) {
                System.out.printf("节点 %d 不存在\n", newHeroNode.getNo());
                break;
            }
            if (temp.next.getNo() == newHeroNode.getNo()) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {// 修改节点
            temp.next.setName(newHeroNode.getName());
            temp.next.setNickName(newHeroNode.getNickName());
        }
    }

    public void deleteNode(int no) {
        HeroNode temp = headNode;
        boolean flag = false;//是否找到要删除的节点
        if (temp.next==null) {
            System.out.println("空链表");
            return;
        }
        while (true) {
            if (temp==null) {
                System.out.println("节点不存在");
                break;
            }
            if (temp.next==null) {
                System.out.println("节点不存在");
                break;
            }
            if (temp.next.getNo() == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        }
    }

}

class HeroNode {
    private int no;
    private String name;
    private String nickName;
    HeroNode next;

    public HeroNode(int no, String name, String nickName) {
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

    public String getNickName() {
        return nickName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
