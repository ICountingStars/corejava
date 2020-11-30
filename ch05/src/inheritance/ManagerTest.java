package inheritance;

/***
 *
 * @author jianglinChen
 * @Date 2020/11/30 11:14
 * @since 1.0.0
 */
public class ManagerTest {
    public static void main(String[] args) {
        Manager boss = new Manager("Carl Cracker", 80000, 1987, 12, 15);
        boss.setBonus(5000);

        Employee[] employees = new Employee[3];
        employees[0] = boss;
        employees[1] = new Employee("Harry Hacker",50000,1989,10,1);
        employees[2] = new Employee("Tommy Tester",40000,1990,3,15);

        for (Employee e :
                employees) {
            System.out.println("name="+e.getName()+",salary="+e.getSalary());
        }
    }
}
