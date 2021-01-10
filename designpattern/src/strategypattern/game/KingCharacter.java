package strategypattern.game;

/**
 * TODO
 *
 * @author jianglinChen
 * @date 2021/1/10
 * @since 1.0.0
 */
public class KingCharacter {
    public static void main(String[] args) {
        Character king =  new King();
        king.fight();
        king.performWeapon();
        king.setWeapon(new AxeBehavior());
        king.performWeapon();
    }
}
