package strategypattern.game;

/**
 * TODO
 *
 * @author jianglinChen
 * @date 2021/1/10
 * @since 1.0.0
 */
public abstract class Character {
    WeaponBehavior weapon;
    public Character(){

    }

    public void performWeapon(){
        weapon.userWeapon();
    }
    public abstract void fight();

    public void setWeapon(WeaponBehavior weapon) {
        this.weapon = weapon;
    }
}
