package anonymousInnerClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Date;

/***
 *
 * @author jianglinChen
 * @Date 2020/12/7 10:55
 * @since 1.0.0
 */
public class AnonymousInnerClass {
    public static void main(String[] args) {
        TalkingClock talkingClock = new TalkingClock();
        talkingClock.start(1000,true);
        JOptionPane.showMessageDialog(null,"Quit Program");
        System.exit(0);
    }
}
class TalkingClock{
    public void start(int interval, boolean beep){
        ActionListener listener = e -> {
            System.out.println("At the tone, the time is"+new Date());
            if (beep) Toolkit.getDefaultToolkit().beep();
        };
        Timer timer = new Timer(interval, listener);
        timer.start();
    }
}
