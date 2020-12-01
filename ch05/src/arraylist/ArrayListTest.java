package arraylist;

import equals.Employee;

import java.util.ArrayList;

/***
 * API 总结
 * · ArrayList<E>(); 构造一个空数组列表
 * · ArrayList<E>(int initialCapacity);用指定容量构造一个空数组列表
 * · boolean add(E obj); 在数组列表的尾端添加一个元素，永远返回true
 * · int size();返回存储在数组列表中的当前元素数量。（这个值将小于或等于数组列表的容量。)
 * · void ensureCapacity(int capacity);确保数组列表在不重新分配存储空间的情况下就能够保存给定数量的元素。
 * · void trimToSize();将数组列表的存储容量削减到当前尺寸。
 * · void set(int index,E obj);设置数组列表指定位置的元素值， 这个操作将覆盖这个位置的原有内容。参数： index 位置（必须介于0 ~ size()-l 之间）;obj 新的值
 * · E get(int index);获得指定位置的元素值。参数： index 获得的元素位置（必须介于0 ~ size()-l 之间）
 * · void add(int index,E obj);向后移动元素， 以便插入元素。参数： index 位置（必须介于0 ~ size()-l 之间）;obj 新的值
 * · E removed (int index);删除一个元素，并将后面的元素向前移动。被删除的元素由返回值返回。参数：index 被删除的元素位置（必须介于0 〜size()-l 之间）
 * ·
 *
 * @author jianglinChen
 * @Date 2020/12/1 8:52
 * @since 1.0.0
 */
public class ArrayListTest {
    public static void main(String[] args) {
        ArrayList<Employee> list = new ArrayList<>();
        list.add(new Employee("Carl Cracker",75000,1987,12,15));
        list.add(new Employee("Harry Hacker",50000,1989,10,1));
        list.add(new Employee("Tony Tester",40000,1990,3,15));

        for (Employee e : list) {
            e.raiseSalary(5);
        }
        for (Employee e : list) {
            System.out.println("name="+e.getName()+",salary="+e.getSalary()+",hireDay="+e.getHireDay());
        }
    }
}
