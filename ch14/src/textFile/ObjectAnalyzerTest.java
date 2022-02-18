package textFile;

import java.util.ArrayList;

/**
 * TODO
 *
 * @author chenjianglin
 * @date 2021/6/25
 * @since 1.0.0
 **/
public class ObjectAnalyzerTest {
    public static void main(String[] args) throws IllegalAccessException {
        ArrayList<Integer> squares = new ArrayList<Integer>();
        for (int i = 1; i <= 5; i++) {
            squares.add(i * i);
        }
        System.out.println(new ObjectAnalyzer().toString(squares));
    }
}
