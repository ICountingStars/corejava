package strategypattern;

import strategypattern.Duck;
import strategypattern.FlyNoWay;
import strategypattern.Quack;

/**
 * TODO
 *
 * @author jianglinChen
 * @date 2021/1/10
 * @since 1.0.0
 */
public class ModelDuck extends Duck {

    public ModelDuck() {
        flyBehavior = new FlyNoWay();
        quackBehavior =  new Quack();
    }

    @Override
    public void display() {
        System.out.println("I'm a model duck");
    }
}
