package pair3;

import pair1.Pair;

/***
 *
 * @author jianglinChen
 * @Date 2020/12/25 20:10
 * @since 1.0.0
 */
public class PairTest3 {
    public static void main(String[] args) {
        Manager ceo = new Manager("Gus Greedy", 800000, 2003, 12, 15);
        Manager cfo = new Manager("Sid Sneaky", 600000, 2003, 12, 15);
        Pair<Manager> buddies = new Pair<>(ceo, cfo);
        printBuddies(buddies);

        ceo.setBonus(10000000);
        cfo.setBonus(5000000);
        Manager[] managers = {ceo,cfo};
        Pair<Employee> result = new Pair<>();
//        minmaxBonus(managers,result);

    }

    private static void minmaxBonus(Manager[] a, Pair<? extends Manager> res) {
        if (a.length == 0) {
            return;
        }
        Manager min = a[0];
        Manager max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (min.getSalary() > a[i].getSalary()) min = a[i];
            if (max.getSalary() < a[i].getSalary()) max = a[i];
        }
//        res.setFirst(min);
//        res.setSecond(max);
    }

    private static void printBuddies(Pair<? extends Employee> buddies) {
        Employee first = buddies.getFirst();
        Employee second = buddies.getSecond();
        System.out.println(first.getName()+"and"+second.getName()+"are buddies");
    }
}
class PairAlg{
    public static boolean hasNulls(Pair<?> pair){
        return pair.getFirst()==null || pair.getSecond()==null;
    }
    public static void swap(Pair<?> pair){ swapHelper(pair);}

    private static <T> void swapHelper(Pair<T> pair) {
        T first = pair.getFirst();
        pair.setFirst(pair.getSecond());
        pair.setSecond(first);
    }
}
