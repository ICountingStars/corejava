package logging;

import javax.swing.*;
import java.util.logging.Logger;

/***
 *
 * @author jianglinChen
 * @Date 2020/12/25 16:22
 * @since 1.0.0
 */
public class LoggingImageViewer {
    public static void main(String[] args) {

    }

}
class ImageViewerFrame extends JFrame{
    public static final int DEFAULT_WIDTH=300;
    public static final int DEFAULT_HEIGHT=400;
    private JLabel label;
    private static Logger logger = Logger.getLogger("D:\\github-xiaochen-cmd\\corejava\\ch07");
}
