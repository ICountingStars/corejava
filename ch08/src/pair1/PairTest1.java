package pair1;



/***
 *
 * @author jianglinChen
 * @Date 2020/12/25 16:49
 * @since 1.0.0
 */
public class PairTest1 {
    public static void main(String[] args) {
        String[] words = {"Marry","had","a","little","lamb"};
        Pair<String> minmax = ArrayAlg.minmax(words);
        System.out.println("min="+minmax.getFirst());
        System.out.println("max="+minmax.getSecond());

    }
}
class ArrayAlg{

    public static Pair<String> minmax(String[] words) {

        if (words == null || words.length==0) return null;
        String min = words[0];
        String max = words[0];
        for (int i = 1; i < words.length; i++) {
            if (min.compareTo(words[i])>0) min = words[i];
            if (max.compareTo(words[i])<0) max = words[i];
        }
        return new Pair<>(min,max);
    }
}
