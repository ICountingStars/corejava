package equals;

import java.util.Objects;

/***
 *
 * @author jianglinChen
 * @Date 2020/11/30 16:47
 * @since 1.0.0
 */
public class Manager extends Employee{

    private double bonus;
    public Manager(String name, double salary, int year, int month, int day) {
        super(name, salary, year, month, day);
        bonus=0;
    }
    public double getSalary(){
        double salary = super.getSalary();
        return this.bonus+salary;
    }
    public void setBonus(double bonus){
        this.bonus=bonus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Manager manager = (Manager) o;
        return Double.compare(manager.bonus, bonus) == 0;
    }

    @Override
    public int hashCode() {
        return super.hashCode()+17*new Double(bonus).hashCode();
    }

    @Override
    public String toString() {
        return super.toString()+"[bonus="+bonus+"]";
    }
}
