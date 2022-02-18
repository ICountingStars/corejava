package lambda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.util.function.Consumer;

/**
 * TODO
 *
 * @author chenjianglin
 * @date 2021/6/27
 * @since 1.0.0
 **/
public class Greeter {
    public static void main(String[] args) {
//        ActionListener listener = event->new RepeatedGreeter().greet(event);

    }
    public void greet(ActionEvent event) {
        System.out.println("Hello, the time is " + Instant.ofEpochMilli(event.getWhen()));
    }



}

class RepeatedGreeter extends Greeter {
    public void greet(ActionEvent event) {
        Timer timer = new Timer(1000, super::greet);
        timer.start();
    }
}
