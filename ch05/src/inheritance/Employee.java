package inheritance;

import java.time.LocalDate;

/***
 *
 * @author jianglinChen
 * @Date 2020/11/30 11:15
 * @since 1.0.0
 */
public class Employee {
    private String name;
    private double salary;
    private LocalDate hireDay;

    public Employee(String name, double salary, int year, int month, int day) {
        this.name = name;
        this.salary = salary;
        this.hireDay = LocalDate.of(year,month,day);
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getHireDay() {
        return hireDay;
    }
    public void raiseSalary(double byPercent){
        this.salary += this.salary*byPercent/100;
    }
}
