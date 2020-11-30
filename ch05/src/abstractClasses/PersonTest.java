package abstractClasses;

/***
 *
 * @author jianglinChen
 * @Date 2020/11/30 14:51
 * @since 1.0.0
 */
public class PersonTest {
    public static void main(String[] args) {
        Person[] people = new Person[2];

        people[0] = new Employee("Harry Hacker",50000,1989,10,1);
        people[1] = new Student("Maria Morris","Computer since");

        for (Person person :
                people) {
            System.out.println(person.getName()+","+person.getDescription());
        }
    }
}
