package strategypattern.game;

/**
 * TODO
 *
 * @author jianglinChen
 * @date 2021/1/10
 * @since 1.0.0
 */
public class King extends Character{
    public King() {
        weapon = new SwordBehavior();
    }

    @Override
    public void fight() {
        System.out.println("King fight");
    }
}
