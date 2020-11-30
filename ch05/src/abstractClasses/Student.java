package abstractClasses;

/***
 *
 * @author jianglinChen
 * @Date 2020/11/30 14:49
 * @since 1.0.0
 */
public class Student extends Person{

    private String major;

    public Student(String name,String major) {
        super(name);
        this.major = major;
    }

    @Override
    public String getDescription() {
        return "a student majoring in" + major;
    }
}
