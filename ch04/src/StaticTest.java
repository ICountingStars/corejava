import java.util.Objects;

/***
 * 静态字段与静态方法
 * 由static关键字定义的字段叫静态字段，每一个类只有一个这样的字段。
 * 静态常量同理。
 * 静态方法，静态方法是不在对象上执行的方法，也就是通过new出来的对象无法调用
 * 静态工厂方法
 * main方法可用调用静态方法而不需要任何对象，也不对任何对象进行操作。
 * @author jianglinChen
 * @Date 2020/11/29 13:44
 * @since 1.0.0
 */
public class StaticTest {
    public static void main(String[] args) {
        Employee2[] staff = new Employee2[3];
        staff[0] = new Employee2("Tom",40000);
        staff[1] = new Employee2("Dick",60000);
        staff[2] = new Employee2("Harry",65000);

        for (Employee2 e :
                staff) {
            e.setId();
            System.out.println("name="+e.getName()+",id="+e.getId()+",salary="+e.getSalary());
        }
        int nextId = Employee2.getNextId();
        System.out.println("Next available id="+nextId);
    }

}
class Employee2{
    private static int nextId=1;

    private String name;
    private double salary;
    private int id;
    public Employee2(String name,double salary){
        //拒绝null参数
        Objects.requireNonNull(name,"the name cannot be null");
        Objects.requireNonNull(salary,"the salary cannot be null");
        this.name = name;
        this.salary = salary;
        this.id = 0;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public static int getNextId() {
        return nextId;
    }

    public int getId() {
        return id;
    }

    public void setId() {
        this.id = nextId;
        nextId++;
    }

    public static void main(String[] args) {
        Employee2 e = new Employee2("Harry",50000);
        System.out.println(e.getName()+" "+e.getSalary());
    }
}