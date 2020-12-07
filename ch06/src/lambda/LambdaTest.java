package lambda;


import javax.swing.*;
import java.util.Arrays;
import java.util.Date;

/***
 *
 * @author jianglinChen
 * @Date 2020/12/6 10:32
 * @since 1.0.0
 */
public class LambdaTest {
    public static void main(String[] args) {
        String[] planets = {"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune"};
        System.out.println(Arrays.toString(planets));
        System.out.println("Sorted in dictionary order:");
        Arrays.sort(planets);
        System.out.println(Arrays.toString(planets));
        System.out.println("Sorted by length");
        Arrays.sort(planets,(first,second)->first.length()-second.length());
        System.out.println(Arrays.toString(planets));
        Timer timer = new Timer(1000, e ->
                System.out.println("The time is" + new Date()));
        timer.start();
        JOptionPane.showMessageDialog(null,"Quit Program>");
        System.exit(0);
    }
}
