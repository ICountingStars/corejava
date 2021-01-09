package set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/***
 * 如何在idea的terminal上使用java、javac 命令
 * 如何找到磁盘上的文件
 * @author jianglinChen
 * @Date 2020/12/26 17:49
 * @since 1.0.0
 */
public class SetTest {

    public static void main(String[] args) {
        HashSet<String> words = new HashSet<>();
        long totalTime = 0;
        try(Scanner in = new Scanner(System.in)) {
            while (in.hasNext()){
                String word = in.next();
                long callTime = System.currentTimeMillis();
                words.add(word);
                callTime = System.currentTimeMillis()-callTime;
                totalTime += callTime;
            }
        }

        final Iterator<String> iter = words.iterator();
        for (int i = 1; i <= 20 && iter.hasNext(); i++) {
            System.out.println(iter.next());
            System.out.println(". . .");
            System.out.println(words.size()+" distinct words. " + totalTime + " milliseconds ");
        }
    }
}
