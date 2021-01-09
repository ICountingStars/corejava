package pair2;

import pair1.Pair;

import java.time.LocalDate;

/***
 *
 * @author jianglinChen
 * @Date 2020/12/25 17:26
 * @since 1.0.0
 */
public class PairTest2 {
    public static void main(String[] args) {
        LocalDate[] birthdays = {
                LocalDate.of(1906,12,9),
                LocalDate.of(1815,12,10),
                LocalDate.of(1903,12,10),
                LocalDate.of(1910,6,22),
        };
        Pair<LocalDate> minmax = ArrayAlg.minmax(birthdays);
        System.out.println("min="+minmax.getFirst());
        System.out.println("max="+minmax.getSecond());
    }
}
class ArrayAlg{
    public static <T extends Comparable> Pair<T> minmax(T[] a){
        if (a == null || a.length==0) return null;
        T min = a[0];
        T max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (min.compareTo(a[i])> 0) min = a[i];
            if (max.compareTo(a[i])<0) max = a[i];
        }
        return new Pair<>(min,max);
    }
}