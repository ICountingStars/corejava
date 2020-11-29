import java.time.LocalDate;
import java.util.Objects;

/***
 * 如果需要返回一个可变对象的引用，首先应该对它进行克隆。
 *
 * API总结
 * static <T> void requireNonNull(T obj);
 * static <T> void requireNonNull(T obj,String message);
 * static <T> void requireNonNull(T obj,Supplier<String> messageSupplier);
 * 如果obj==null，抛出NullPointerException而没有消息或给定的消息
 * static <T> void requireNonNullElse(T obj,T defaultObj);
 * static <T> void requireNonNullElse(T obj,Supplier<T> defaultSupplier);
 * 如果obj不为null则返回obj,或者如果obj为null则返回默认对象
 *
 *
 * @author jianglinChen
 * @Date 2020/11/29 13:08
 * @since 1.0.0
 */
public class EmployeeTest {
    public static void main(String[] args) {
        Employee[] employees = new Employee[3];
        employees[0] = new Employee("Carl Cracker",75000,1987,12,15);
        employees[1] = new Employee("Harry Hacker",50000,1989,10,1);
        employees[2] = new Employee("Tony Tester",40000,1990,3,15);

        for (Employee employee :
                employees) {
            employee.raiseSalary(5);
        }

        for (Employee employee :
                employees) {
            System.out.println("name="+employee.getName()+",salary="+employee.getSalary()+",birthDay="+employee.getBirthDay());
        }
    }

}
class Employee{
    private String name;
    private double salary;
    private LocalDate birthDay;
    public Employee(String name,double salary, int year,int month, int day){
        //拒绝null参数
        Objects.requireNonNull(name,"the name cannot be null");
        Objects.requireNonNull(salary,"the salary cannot be null");
        this.name = name;
        this.salary = salary;
        this.birthDay = LocalDate.of(year,month,day);
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }
    public void raiseSalary(double byPercent){
        this.salary+=this.salary*byPercent/100;
    }
}