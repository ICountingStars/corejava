package clone;

/***
 *
 * @author jianglinChen
 * @Date 2020/12/5 22:26
 * @since 1.0.0
 */
public class CloneTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Employee employee = new Employee("John Q. Public", 50000);
        employee.setHireDay(2000,1,1);
        Employee clone = employee.clone();
        clone.raiseSalary(10);
        clone.setHireDay(2002,12,31);
        System.out.println("employee="+employee);
        System.out.println("clone="+clone);
    }
}
