package optional;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * TODO
 *
 * @author chenjianglin
 * @date 2021/6/27
 * @since 1.0.0
 **/
public class OptionalTest {
    public static void main(String[] args) throws IOException {
        Optional<Object> objectOptional = Optional.ofNullable(null); // Optional.empty
        System.out.println(objectOptional);
        Optional<Object> empty = Optional.empty(); // Optional.empty
        System.out.println(empty);
        Optional<String> tom = Optional.of("Tom");
        System.out.println(tom.get());
        System.out.println(tom); // Optional[Tom]
        Optional<Object> o = Optional.of(null); // throw NPE
    }
}
