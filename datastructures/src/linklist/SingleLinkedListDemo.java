package linklist;

import java.util.Stack;

/**
 * TODO
 *
 * @author chenjianglin
 * @date 2021/7/6
 * @since 1.0.0
 **/
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode songJiang = new HeroNode(1, "宋江", "及时雨");
        HeroNode luJunYi = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode wuYong = new HeroNode(3, "吴用", "智多星");
        HeroNode linCong = new HeroNode(4, "林冲", "豹子头");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(songJiang);
//        singleLinkedList.add(luJunYi);
//        singleLinkedList.add(wuYong);
//        singleLinkedList.add(linCong);
        // 面试题
//        System.out.println("原链表情况：");
//        singleLinkedList.showList();
//        System.out.println("链表反转,链表反转后情况: ");
//        reverseLinkedList(singleLinkedList.getHeadNode());
//        singleLinkedList.showList();
//        System.out.println("逆序打印情况1: ");
//        reversePrint(singleLinkedList.getHeadNode());
//        System.out.println("逆序打印情况2: ");
//        reversePrint(singleLinkedList.getHeadNode());


        singleLinkedList.addByOrder(songJiang);
        singleLinkedList.addByOrder(linCong);
        singleLinkedList.addByOrder(wuYong);
        singleLinkedList.addByOrder(luJunYi);
        singleLinkedList.addByOrder(linCong);

        singleLinkedList.showList();

        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟～～");
        singleLinkedList.updateNode(newHeroNode);
        System.out.println("修改后的情况");
        singleLinkedList.showList();

        singleLinkedList.deleteNode(1);
        singleLinkedList.deleteNode(4);
//        singleLinkedList.deleteNode(2);
//        singleLinkedList.deleteNode(3);
        System.out.println("删除后的链表情况");
        singleLinkedList.showList();

        // 有效个数
        System.out.printf("有效的节点个数:%d \n", linkedSize(singleLinkedList.getHeadNode()));


        // 倒数n个
        HeroNode res = findLastIndexNode(singleLinkedList.getHeadNode(), 1);
//        HeroNode res = findLastIndexNode(singleLinkedList.getHeadNode(), 2);
//        HeroNode res = findLastIndexNode(singleLinkedList.getHeadNode(), 3);
        System.out.println("res = " + res);
    }

    // 逆序打印链表
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode temp = head.next;
        while (temp != null) {
            // 压入栈
            stack.push(temp);
            temp = temp.next;
        }

        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    // 反转链表
    public static void reverseLinkedList(HeroNode head) {
        if (head.next == null || head.next.next == null) {
            return;
        }
        // 辅助指针帮助遍历原来的指针
        HeroNode current = head.next;
        HeroNode next = null;
        HeroNode reverseHead = new HeroNode(0, "", "");
        // 遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead的最前端
        while (current != null) {
            next = current.next;// 暂存当前节点的下一个节点
            current.next = reverseHead.next;// current 的下一个节点指向新链表的最前端
            reverseHead.next = current;// 反转链表指向当前节点
            current = next;// 移动到原链表的下一个节点
        }
        // head.next 指向 reverseHead.next 实现反转
        head.next = reverseHead.next;
    }

    // 找到链表中的倒数第n个数
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        if (head.next == null) {
            return null;
        }
        int linkedSize = linkedSize(head);
        if (index < 0 || index > linkedSize) {
            return null;
        }
        HeroNode current = head.next;
        for (int i = 0; i < linkedSize - index; i++) {
            current = current.next;
        }
        return current;
    }

    //获取到单链表中的有效个数
    public static int linkedSize(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        // 定义一个辅助变量，没有统计头节点
        HeroNode current = head.next;
        while (current != null) {
            length++;
            current = current.next;
        }
        return length;
    }
}

class SingleLinkedList {
    private HeroNode headNode = new HeroNode(0, "", "");// 头节点

    // 添加节点
    public void add(HeroNode node) {
        HeroNode tempNode = headNode;
        while (true) {
            if (tempNode.next == null) {
                break;
            }
            tempNode = tempNode.next;
        }
        tempNode.next = node;//
    }

    // 按编号顺序添加
    public void addByOrder(HeroNode node) {
        HeroNode tempNode = headNode;
        boolean flag = false; // 默认给false
        while (true) {
            if (tempNode.next == null) {
                break;
            }
            if (tempNode.next.getNo() > node.getNo()) {
                break;
            } else if (tempNode.next.getNo() == node.getNo()) {
                flag = true; // 编号存在
                break;
            }
            tempNode = tempNode.next;
        }
        if (flag) {
            // 提示已存在
            System.out.printf("编号 %d 已存在\n", node.getNo());
        } else {
            // 添加进去
            node.next = tempNode.next;
            tempNode.next = node;
        }
    }

    // 删除节点
    public void deleteNode(int no) {
        // 判断链表是否为空
        HeroNode tempNode = headNode;
        boolean flag = false;// 用于判断是否找到
        while (true) {
            if (tempNode.next == null) {
                System.out.println("链表为空");
                return;
            }
            // 找到节点
            if (tempNode.next.getNo() == no) {
                flag = true;
                break;
            }
            tempNode = tempNode.next;
        }
        if (flag) {
            tempNode.next = tempNode.next.next;
        } else {
            System.out.printf("要删除的节点 %d 不存在", no);
        }
    }

    // 修改节点
    public void updateNode(HeroNode newHeroNode) {
        if (headNode.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode tempNode = headNode.next;
        boolean flag = false;
        while (true) {
            if (tempNode == null) {
                break;
            }
            if (tempNode.getNo() == newHeroNode.getNo()) {
                flag = true;
                break;
            }
            tempNode = tempNode.next;
        }
        if (flag) {
            // 找到了
            tempNode.setName(newHeroNode.getName());
            tempNode.setNickName(newHeroNode.getNickName());
        } else {
            System.out.printf("没有找到编号 %d 的节点,不能修\n", newHeroNode.getNo());
        }
    }

    // 遍历节点
    public void showList() {
        //判断是否空链表
        if (headNode.next == null) {
            System.out.println("空链表");
            return;
        }
        HeroNode tempNode = headNode.next;
        while (tempNode != null) {
            System.out.println(tempNode);
            tempNode = tempNode.next;
        }
    }

    public HeroNode getHeadNode() {
        return headNode;
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

    public void setNo(int no) {
        this.no = no;
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
