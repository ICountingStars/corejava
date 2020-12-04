package enums;

import java.util.Scanner;

/***
 * enum 枚举类API总结
 * · static Enum valueOf(Class enumClass,String name); 返回指定名字、给定类的枚举常量。
 * · String toString(); 返回枚举常量名。
 * · int ordinal(); 返回枚举常量在enum 声明中的位置，位置从0 开始计数。
 * · int compareTo(E other);  如果枚举常量出现在Other 之前， 则返回一个负值；如果this=other，则返回0; 否则，
 * 返回正值。枚举常量的出现次序在enum 声明中给出。
 *
 * @author jianglinChen
 * @Date 2020/12/3 9:24
 * @since 1.0.0
 */
public class EnumsTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a size:(SMALL,MEDIUM,LARGE,EXTRA_LARGE)");
        String s = scanner.next().toUpperCase();
        Size size = Enum.valueOf(Size.class, s);
        System.out.println("size="+size);
        System.out.println("abbreviation="+size.getAbbreviation());
        if (size == Size.EXTRA_LARGE)
            System.out.println("Good job you paid attention to the...");

        System.out.println(Size.LARGE.toString());//LARGE
        System.out.println(Size.MEDIUM.ordinal());//1
        System.out.println(Size.MEDIUM.compareTo(Size.SMALL));//>0
    }
}
enum Size{
    SMALL("S"),
    MEDIUM("M"),
    LARGE("L"),
    EXTRA_LARGE("XL");
    private String abbreviation;
    private Size(String abbreviation){
        this.abbreviation = abbreviation;
    }
    public String getAbbreviation(){
        return abbreviation;
    }
}