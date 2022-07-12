import javax.swing.*;
import java.awt.*;

public class MoneyManager implements GlobalDB{
    public static void main(String[] args) {
        db.init();
        LoginFrame lf = new LoginFrame();
        lf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lf.setTitle("欢迎使用个人理财账本!");
        // 界面显示居中
        Dimension screen = lf.getToolkit().getScreenSize();
        lf.setLocation((screen.width - lf.getSize().width) / 2, (screen.height - lf.getSize().height) / 2);
        // 界面大小不可调整
        lf.setResizable(false);
        lf.setSize(400, 140);
        lf.setVisible(true);
    }
}
