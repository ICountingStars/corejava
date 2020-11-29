import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;

/***
 * API总结
 * ·static LocalDate.now();//构造一个表示当前日期的LocalDate对象
 * ·static LocalDate.of(int year, int month, int day);//构造一个根据参数指定的日期的LocalDate对象
 * ·int getYear();int getMonthValue();int getDayOfMonth();获取年、月、日、
 * ·DayOfWeek getDayOfWeek();得到当前日期是星期几，作为DayOfWeek类的一个实例返回；1表示星期一，7表示星期日
 * ·LocalDate plusDays(int n);LocalDate minusDays(int n);生成日期之前或之后前n天的日期  是以纪元日为初始来计算的
 *
 * minusDays(int n);方法里是基于plusDays(int n)来计算的
 *
 * @author jianglinChen
 * @Date 2020/11/29 11:19
 * @since 1.0.0
 */
public class CalendarTest {
    public static void main(String[] args) {
        //构造Data类的实例
        System.out.println(new Date());
        System.out.println(new Date().getClass());
        String s = new Date().toString();
        System.out.println(s);
        //区分：对象与对象变量
        Date birthDay = new Date();//对象变量birthDay  对象new Date()
        Date deadLine;//对象变量deadLine
        //对象变量deadLine可用引用对象，但是它绝对不是一个对象，对象是必须经过初始化过程产生的，也就是构造器构造出来的。
//        deadLine.toString();//error: Variable 'deadLine' might not have been initialized
        /**
         * 在Java中，任何对象变量的值都是对存储在另外一个地方的某个对象的引用
         */

        System.out.println(LocalDate.now());
        System.out.println(LocalDate.now().getClass());
        System.out.println(LocalDate.of(2020, 11, 29));
        LocalDate date = LocalDate.of(2020, 11, 29);
        //date之后的100天，是几月几号？请计算
        LocalDate plusDays = date.plusDays(100);
        LocalDate localDate1 = LocalDate.of(plusDays.getYear(), plusDays.getMonthValue(), plusDays.getDayOfMonth());
        System.out.println("100天之后的日期："+localDate1);
        System.out.println();

        /**
         * x显示当月的日历，当天的日期用一个*标记
         */
        getCalender();

    }

    private static void getCalender(){
        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();
        int today = date.getDayOfMonth();
        date = date.minusDays(today - 1);
        DayOfWeek weekDay = date.getDayOfWeek();
        int value = weekDay.getValue();
        System.out.println("Mon Tue Wed Thu Fri Sat Sun");
        for (int i = 1; i < value; i++) {
            System.out.print("    ");
        }
        while (date.getMonthValue() == month){
            System.out.printf("%3d",date.getDayOfMonth());
            if (date.getDayOfMonth() == today){
                System.out.print("*");
            }else {
                System.out.print(" ");
            }
            date = date.plusDays(1);
            if (date.getDayOfWeek().getValue()==1) System.out.println();
        }
        if (date.getDayOfWeek().getValue() !=1) System.out.println();
    }
}
