package strategypattern.game;

/**
 * TODO
 *
 * @author jianglinChen
 * @date 2021/1/10
 * @since 1.0.0
 */
public class Troll extends Character{
    public Troll() {
        weapon = new KnifeBehavior();
    }

    @Override
    public void fight() {
        System.out.println("Troll fight");
    }
}
