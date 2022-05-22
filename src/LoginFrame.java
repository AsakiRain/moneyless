import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 登录界面
class LoginFrame extends JFrame implements ActionListener {
    private JLabel l_user, l_pwd; // 用户名标签，密码标签
    private JTextField t_user;// 用户名文本框
    private JPasswordField t_pwd; // 密码文本框
    private JButton b_ok, b_cancel; // 登录按钮，退出按钮

    public LoginFrame() {
        l_user = new JLabel("用户名：", JLabel.RIGHT);
        l_pwd = new JLabel(" 密码：", JLabel.RIGHT);
        t_user = new JTextField(31);
        t_pwd = new JPasswordField(31);
        b_ok = new JButton("登录");
        b_cancel = new JButton("退出");
        // 布局方式FlowLayout，一行排满排下一行
        setLayout(new FlowLayout());
        add(l_user);
        add(t_user);
        add(l_pwd);
        add(t_pwd);
        add(b_ok);
        add(b_cancel);
        // 为按钮添加监听事件
        b_ok.addActionListener(this);
        b_cancel.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (b_cancel == e.getSource()) {
            System.exit(0);
        } else if (b_ok == e.getSource()) {
            UseDB db = new UseDB();
            db.init();
            MainFrame mf = new MainFrame(t_user.getText().trim());
            mf.setResizable(false);
            mf.setSize(600, 580);
            Dimension screen = mf.getToolkit().getScreenSize();
            mf.setLocation((screen.width - mf.getSize().width) / 2, (screen.height - mf.getSize().height) / 2);
            mf.setVisible(true);
        }
    }
}