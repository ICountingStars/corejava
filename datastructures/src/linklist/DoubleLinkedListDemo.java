package linklist;

/**
 * TODO
 *
 * @author chenjianglin
 * @date 2021/7/13
 * @since 1.0.0
 **/
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        HeroNodeByDouble songJiang = new HeroNodeByDouble(1, "宋江", "及时雨");
        HeroNodeByDouble luJunYi = new HeroNodeByDouble(2, "卢俊义", "玉麒麟");
        HeroNodeByDouble wuYong = new HeroNodeByDouble(3, "吴用", "智多星");
        HeroNodeByDouble linCong = new HeroNodeByDouble(4, "林冲", "豹子头");
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
//        doubleLinkedList.addDoubleLinkedList(songJiang);
//        doubleLinkedList.addDoubleLinkedList(luJunYi);
//        doubleLinkedList.addDoubleLinkedList(wuYong);
//        doubleLinkedList.addDoubleLinkedList(linCong);

        // 顺序添加
        doubleLinkedList.orderAddDoubleLinkedList(linCong);
        doubleLinkedList.orderAddDoubleLinkedList(wuYong);
        doubleLinkedList.orderAddDoubleLinkedList(songJiang);
        doubleLinkedList.orderAddDoubleLinkedList(luJunYi);


        doubleLinkedList.showDoubleLinkedList();
        System.out.println("修改节点");

        doubleLinkedList.updateDoubleLinkedList(new HeroNodeByDouble(4, "公孙胜", "入云龙"));
        doubleLinkedList.showDoubleLinkedList();

        doubleLinkedList.deleteDoubleLinkedList(4);
        System.out.println("删除过后的链表情况");
        doubleLinkedList.deleteDoubleLinkedList(1);
        doubleLinkedList.deleteDoubleLinkedList(2);
        doubleLinkedList.deleteDoubleLinkedList(3);
        doubleLinkedList.showDoubleLinkedList();

    }
}

class DoubleLinkedList {
    // 初始化头节点
    private static HeroNodeByDouble head = new HeroNodeByDouble(0, "", "");

    // 获取头节点
    public HeroNodeByDouble getHead() {
        return head;
    }

    // 遍历双向链表的
    public void showDoubleLinkedList() {
        if (head.next == null) {
            System.out.println("空链表");
            return;
        }
        HeroNodeByDouble temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    // 添加双向链表的节点(无序)
    public void addDoubleLinkedList(HeroNodeByDouble newNode) {
        HeroNodeByDouble temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        // 加入新节点 形成双向链表
        temp.next = newNode;
        newNode.pre = temp;
    }

    // 添加双向链表的节点(有序)
    public void orderAddDoubleLinkedList(HeroNodeByDouble newNode) {
        HeroNodeByDouble temp = head;
        boolean flag = false;// 是否找到添加的位置
        while (true) {
            if (temp.next == null) {
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
            System.out.printf("节点 %d 已存在", newNode.getNo());
        } else {
            if (temp.next != null) { // 当前节点的下一个节点存在
                // 先把新增节点的pre next指针指向确定 再去把当前节点的下一个指向与下一个节点的前一个指向确定
                newNode.next = temp.next;
                newNode.pre = temp;
                temp.next.pre = newNode;
                temp.next = newNode;
            } else {//遍历到最后直接加入
                temp.next = newNode;
                newNode.pre = temp;
            }
        }
    }

    // 修改双向链表的节点
    public void updateDoubleLinkedList(HeroNodeByDouble newNode) {
        if (head.next == null) {
            System.out.println("空链表");
            return;
        }
        HeroNodeByDouble temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.getNo() == newNode.getNo()) {
                flag = true;
                break;//找到跳出循环
            }
            temp = temp.next;//没找到，指针往下移动一个元素
        }
        if (flag) {
            temp.setName(newNode.getName());
            temp.setNickName(newNode.getNickName());
        } else {// 没找到节点
            System.out.printf("节点 %d 不存\n", newNode.getNo());
        }
    }

    // 删除双向链表的节点
    public void deleteDoubleLinkedList(int no) {
        if (head.next == null) {
            System.out.println("空链表");
            return;
        }
        HeroNodeByDouble temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {// 遍历到链表的最后面
                break;
            }
            if (temp.getNo() == no) {
                flag = true;// 找到待删除节点的前一个节点
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            // 找到节点,删除节点
            temp.pre.next = temp.next;
            if (temp.next != null) {
                // 这段代码在最后一个节点时，会包空指针异常，得有个前提temp.next!=null
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("节点 %d 不存在", no);
        }
    }
}

class HeroNodeByDouble {
    HeroNodeByDouble pre; // 默认为null
    HeroNodeByDouble next; // 默认为null
    private int no;
    private String name;
    private String nickName;

    public HeroNodeByDouble(int no, String name, String nickName) {
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
        return "HeroNodeByDouble{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}

