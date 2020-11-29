/***
 *
 * @author jianglinChen
 * @Date 2020/11/29 14:26
 * @since 1.0.0
 */
public class ParamTest {
    public static void main(String[] args) {
        //验证Java总是采用按值调用,还是引用调用 首先方法参数分两类：基本数据类型和对象引用
        //-----------------------------验证基本数据类型参数
        //按值调用：表示方法接收的是调用者提供的值;引用调用：表示方法接收的是调用者提供的变量地址
//        验证基本数据类型 属于的是按值调用
        double percent = 10;//这个是ParamTest类里main方法中的一个double基本数据类型的percent变量的值
        System.out.println(percent);//打印变量percent  按值调用方式
        tripleValue(percent);//传入的是percent变量的值
        System.out.println(percent);//打印变量percent
        //           变量percent  变量x 变量x  变量percent
        //若输出的结果是 10          10    30      30       ,可以修改按值传递的变量的值
        //若输出的结果是 10          10    30      10       ,不可以修改按值传递的变量的值

        //最终输出结果为
//        10.0
//        10.0
//        30.0
//        10.0
        //-----------------------------验证对象引用参数
        Employee harry = new Employee("chen",6500,1997,07,28);//harry为对象引用变量
        System.out.println("salary="+harry.getSalary());//打印对象引用变量harry提供的salary字段的值
        tripleValue(harry);
        System.out.println("salary="+harry.getSalary());//打印对象引用变量harry提供的salary字段的值
        //            变量harry  变量e     变量e   变量harry
        //若输出的结果是 6500     6500    19500     6500     不可以修改按引用传递变量的值
        //若输出的结果是 6500     6500    19500     19500    可以修改按引用传递变量的值

        //输出结果
//        salary=6500.0
//        salary=6500.0
//        salary=19500.0
//        salary=19500.0
//
        /**
         * 结论：不可以修改按值传递的变量的值
         * 结论：可以修改按引用传递变量的值
         * 汇总：方法不可以修改按值传递的变量的值，可以修改按引用传递变量的值。 方法总是接收调用者提供的值。
         */

        //重点：当方法参数类型是对象时，采用的是按引用调用，还是按值调用。
        //验证 按引用调用的话，e1与e2的两变量的地址交换，反之不是。
        Employee e1 = new Employee("e1", 100, 1999, 8, 1);
        Employee e2 = new Employee("e2", 100, 2000, 8, 1);
        System.out.println("e1地址="+e1);
        System.out.println("e2地址="+e2);
        System.out.println("e1:"+"name="+e1.getName()+",salary="+e1.getSalary()+",birthDay="+e1.getBirthDay());
        System.out.println("e2:"+"name="+e2.getName()+",salary="+e2.getSalary()+",birthDay="+e2.getBirthDay());
        System.out.println("------------------------------------------------------------------");
        swap(e1,e2);
        System.out.println("e1地址="+e1);
        System.out.println("e2地址="+e2);
        System.out.println("e1:"+"name="+e1.getName()+",salary="+e1.getSalary()+",birthDay="+e1.getBirthDay());
        System.out.println("e2:"+"name="+e2.getName()+",salary="+e2.getSalary()+",birthDay="+e2.getBirthDay());

        //结果
//        e1地址=Employee@4554617c
//        e2地址=Employee@74a14482
//        e1:name=e1,salary=100.0,birthDay=1999-08-01
//        e2:name=e2,salary=100.0,birthDay=2000-08-01
//        ------------------------------------------------------------------
//        e1地址=Employee@74a14482
//        e2地址=Employee@4554617c
//        e1:name=e2,salary=100.0,birthDay=2000-08-01
//        e2:name=e1,salary=100.0,birthDay=1999-08-01
//        ------------------------------------------------------------------
//        e1地址=Employee@4554617c
//        e2地址=Employee@74a14482
//        e1:name=e1,salary=100.0,birthDay=1999-08-01
//        e2:name=e2,salary=100.0,birthDay=2000-08-01
//

        /**
         * 结论：当方法参数类型是对象时，采用的是按按值调用。
         * 总结：方法：1.不能修改基本数据类型的参数;2.可以修改对象参数的状态(堆内的值);3.方法不能让一个对象参数应用一个新的对象
         */
    }
    //扩大三倍
    public static void tripleValue(double x){
        System.out.println(x);//打印调用者提供的变量x
        x = 3 * x;
        System.out.println(x);//打印调用者提供的变量x
    }

    //扩大三倍
    public static void tripleValue(Employee e){
        System.out.println("salary="+e.getSalary());//打印对象引用变量employee提供的salary字段的值
        e.raiseSalary(200);
        System.out.println("salary="+e.getSalary());//打印对象引用变量employee提供的salary字段的值
    }
    //交互
    public static void swap(Employee e1, Employee e2){
        Employee tmp = e1;
        e1 = e2;
        e2 = tmp;
        System.out.println("e1地址="+e1);
        System.out.println("e2地址="+e2);
        System.out.println("e1:"+"name="+e1.getName()+",salary="+e1.getSalary()+",birthDay="+e1.getBirthDay());
        System.out.println("e2:"+"name="+e2.getName()+",salary="+e2.getSalary()+",birthDay="+e2.getBirthDay());
        System.out.println("------------------------------------------------------------------");
    }
}
