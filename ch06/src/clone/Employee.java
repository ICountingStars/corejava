package clone;

import java.util.Date;
import java.util.GregorianCalendar;

/***
 *
 * @author jianglinChen
 * @Date 2020/12/5 22:18
 * @since 1.0.0
 */
public class Employee implements Cloneable{
    private String name;
    private double salary;
    private Date hireDay;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
        this.hireDay = new Date();
    }

    public Employee clone() throws CloneNotSupportedException{
        Employee clone = (Employee) super.clone();

        clone.hireDay = (Date) hireDay.clone();
        return clone;
    }


    public void setHireDay(int year, int month, int day){
        Date newHireDay = new GregorianCalendar(year, month-1, day).getTime();
        hireDay.setTime(newHireDay.getTime());

    }

    public void raiseSalary(double byPercent){
        this.salary += this.salary*byPercent/100;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", hireDay=" + hireDay +
                '}';
    }
}
