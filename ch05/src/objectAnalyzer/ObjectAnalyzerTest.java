package objectAnalyzer;

import java.util.ArrayList;

/***
 *
 * @author jianglinChen
 * @Date 2020/12/3 21:59
 * @since 1.0.0
 */
public class ObjectAnalyzerTest {
    public static void main(String[] args) {
        ArrayList<Integer> squares = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            squares.add(i*i);
            System.out.println(new ObjectAnalyzer().toString(squares));
        }
    }
}
