import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 修改密码界面
class ModifyPwdFrame extends JFrame implements ActionListener {
    private JLabel l_oldPWD, l_newPWD, l_newPWDAgain;
    private JPasswordField t_oldPWD, t_newPWD, t_newPWDAgain;
    private JButton b_ok, b_cancel;
    private String username;

    public ModifyPwdFrame(String username) {
        super("修改密码");
        this.username = username;
        l_oldPWD = new JLabel("旧密码");
        l_newPWD = new JLabel("新密码：");
        l_newPWDAgain = new JLabel("确认新密码：");
        t_oldPWD = new JPasswordField(15);
        t_newPWD = new JPasswordField(15);
        t_newPWDAgain = new JPasswordField(15);
        b_ok = new JButton("确定");
        b_cancel = new JButton("取消");
        setLayout(new FlowLayout());
        add(l_oldPWD);
        add(t_oldPWD);
        add(l_newPWD);
        add(t_newPWD);
        add(l_newPWDAgain);
        add(t_newPWDAgain);
        add(b_ok);
        add(b_cancel);
        b_ok.addActionListener(this);
        b_cancel.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (b_cancel == e.getSource()) {
            // 添加代码
        } else if (b_ok == e.getSource()) { // 修改密码
            // 添加代码
        }
    }
}

// JOptionPane.showMessageDialog(null,"用户名密码出错", "警告",
// //JOptionPane.ERROR_MESSAGE);