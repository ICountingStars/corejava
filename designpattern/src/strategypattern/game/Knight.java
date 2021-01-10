package strategypattern.game;

/**
 * TODO
 *
 * @author jianglinChen
 * @date 2021/1/10
 * @since 1.0.0
 */
public class Knight extends Character{
    public Knight() {
        weapon = new AxeBehavior();
    }

    @Override
    public void fight() {
        System.out.println("Knight fight");
    }
}
