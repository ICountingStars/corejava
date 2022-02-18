package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * TODO
 *
 * @author chenjianglin
 * @date 2021/7/3
 * @since 1.0.0
 **/
public class CountLongNumbers {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("gently", "down", "the", "stream");
        Stream<String> stringStream = strings.stream().flatMap(CountLongNumbers::codePoints);
    }

    public static Stream<String> codePoints(String s) {
        List<String> result = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            int j = s.offsetByCodePoints(i, 1);
            result.add(s.substring(i, j));
            i = j;
        }
        return result.stream();
    }
}
