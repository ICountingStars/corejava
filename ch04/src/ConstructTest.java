import java.util.Random;

/***
 * API
 * · Random() 构造一个新的随机数生成器
 * · int nextInt(int n) 返回一个0~n-1之间的随机数
 * @author jianglinChen
 * @Date 2020/11/29 16:15
 * @since 1.0.0
 */
public class ConstructTest {
    public static void main(String[] args) {
        Employees[] employees = new Employees[3];

        employees[0] = new Employees("harry",40000);
        employees[1] = new Employees(60000);
        employees[2] = new Employees();
        for (Employees employee :
                employees) {
            System.out.println("name="+employee.getName()+",id="+employee.getId()+",salary="+employee.getSalary());
        }
    }
}
class Employees{
    private static int nextId;

    private int id;
    private String name = "";
    private double salary;

    static {
        Random random = new Random();
        nextId = random.nextInt(10000);
    }
    {
        id = nextId;
        nextId++;
    }
    public Employees(String n, double s){
        name = n;
        salary = s;
    }
    public Employees(double s){
        this("Employees#"+nextId,s);
    }
    public Employees(){

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }
}
