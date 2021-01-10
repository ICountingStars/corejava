package strategypattern;

/**
 * TODO
 *
 * @author jianglinChen
 * @date 2021/1/10
 * @since 1.0.0
 */
public class MuteQuack implements QuackBehavior{

    @Override
    public void quack() {
        System.out.println("<< Silence >>");
    }
}
