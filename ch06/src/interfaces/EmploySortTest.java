package interfaces;

import java.util.Arrays;

/***
 * java.lang.Comparable<T>
 * • int compareTo(T other)
 * 用这个对象与other 进行比较。如果这个对象小于other 则返回负值； 如果相等则返回
 * 0； 否则返回正值。
 *
 *
 * java.util.Arrays
 * 參static void sort( Object[] a )
 * 使用mergesort 算法对数组a 中的元素进行排序。要求数组中的元素必须属于实现了
 * Comparable 接口的类， 并且元素之间必须是可比较的。
 *
 * java.lang.lnteger
 * • static int comparednt x , int y) 7
 * 如果x < y 返回一个负整数；如果x 和y 相等，则返回0; 否则返回一个负整数。
 *
 * java.lang.Double 1.0
 * •static int compare(double x , double y) 1.4
 * 如果x < y 返回一个负数；如果x 和y 相等则返回0; 否则返回一个负数
 *
 * @author jianglinChen
 * @Date 2020/12/5 15:57
 * @since 1.0.0
 */
public class EmploySortTest {
    public static void main(String[] args) {
        Employee[] employees = new Employee[3];
        employees[0] = new Employee("Harry Hacker",35000);
        employees[1] = new Employee("Carl Cracker",75000);
        employees[2] = new Employee("Tony Tester",38000);

        Arrays.sort(employees);
        for (Employee employee :
                employees) {
            System.out.println("name=" +employee.getName()+ " , salary=" + employee.getSalary());
        }
    }
}
