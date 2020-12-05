package timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/***
 *
 * @author jianglinChen
 * @Date 2020/12/5 16:32
 * @since 1.0.0
 */
public class TimerTest {
    public static void main(String[] args) {
        ActionListener listener = new TimerPrinter();
        Timer timer = new Timer(10000, listener);
        timer.start();
        JOptionPane.showMessageDialog(null,"Quit program?");
        System.exit(0);

    }
}
class TimerPrinter implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("At the tone, the time is " + new Date());
        Toolkit.getDefaultToolkit().beep();
    }
}
