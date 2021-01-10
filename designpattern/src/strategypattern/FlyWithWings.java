package strategypattern;

/**
 * TODO
 *
 * @author jianglinChen
 * @date 2021/1/10
 * @since 1.0.0
 */
public class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I'm flying!");
    }
}
