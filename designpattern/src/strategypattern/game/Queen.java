package strategypattern.game;

/**
 * TODO
 *
 * @author jianglinChen
 * @date 2021/1/10
 * @since 1.0.0
 */
public class Queen extends Character{
    public Queen() {
        weapon = new BowAndArrowBehavior();
    }

    @Override
    public void fight() {
        System.out.println("Queen fight");
    }
}
