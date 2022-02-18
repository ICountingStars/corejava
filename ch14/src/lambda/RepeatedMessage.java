package lambda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * TODO
 *
 * @author chenjianglin
 * @date 2021/6/27
 * @since 1.0.0
 **/
public class RepeatedMessage {
    public static void main(String[] args) {
        repeatMessage("Hello", 1000);
    }

    public static void repeatMessage(String text, int delay) {
        ActionListener listener = event -> {
            System.out.println(text);
            Toolkit.getDefaultToolkit().beep();
        };
        new Timer(delay, listener).start();
    }
}
