package abstractClasses;

/***
 * 访问控制符总结：
 * 1 ) 仅对本类可见private。
 * 2 ) 对所有类可见public：
 * 3 ) 对本包和所有子类可见protected。
 * 4 ) 对本包可见—默认（很遗憾)，不需要修饰符。
 * @author jianglinChen
 * @Date 2020/11/30 14:42
 * @since 1.0.0
 */
public abstract class Person {
    public abstract String getDescription();
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
