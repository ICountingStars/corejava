package strategypattern;

/**
 * TODO
 *
 * @author jianglinChen
 * @date 2021/1/10
 * @since 1.0.0
 */
public class MiniDuckSimulator {
    public static void main(String[] args) {
        final Duck mallardDuck = new MallardDuck();
        mallardDuck.performFly();
        mallardDuck.performQuack();

        Duck model = new ModelDuck();
        model.performFly();
        model.setFlyBehavior(new FlyRocketPowered());
        model.performFly();


    }
}
