package pair3;

import java.time.LocalDate;
import java.util.Objects;

public class Employee {
    private String name;
    private double salary;
    private LocalDate hireDay;

    public Employee(String name, double salary, int year, int month,int day) {
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

    @Override
    public boolean equals(Object o) {
       if(this == o) return true;
       if (o == null || getClass()!= o.getClass()) return false;
       Employee employee = (Employee) o;
       return Double.compare(this.salary,employee.salary)==0 &&
               Objects.equals(this.name,employee.name) &&
               Objects.equals(this.hireDay,employee.hireDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name,this.salary,this.hireDay);
    }

    @Override
    public String toString() {
        return getClass().getName()+"[name='" + name + '\'' +
                ", salary=" + salary +
                ", hireDay=" + hireDay +
                ']';
    }
}