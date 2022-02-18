package textFile;

import java.time.LocalDate;
import java.util.Objects;

public class Employee {
    private String name;
    private double salary;
    private LocalDate birthDay;

    public Employee(String name, double salary, int year, int month, int day) {
        //拒绝null参数
        Objects.requireNonNull(name, "the name cannot be null");
        Objects.requireNonNull(salary, "the salary cannot be null");
        this.name = name;
        this.salary = salary;
        this.birthDay = LocalDate.of(year, month, day);
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
}