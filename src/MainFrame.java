import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 主界面
class MainFrame extends JFrame implements ActionListener {
    private JMenuBar mb = new JMenuBar();
    private JMenu m_system = new JMenu("系统管理");
    private JMenu m_fm = new JMenu("收支管理");
    private JMenuItem mI[] = {new JMenuItem("密码重置"), new JMenuItem("退出系统")};
    private JMenuItem m_FMEdit = new JMenuItem("收支编辑");
    private JLabel l_type, l_fromDate, l_toDate, l_bal, l_ps;
    private JTextField t_fromDate, t_toDate;
    private JButton b_select1, b_select2;
    private JComboBox c_type;
    private JPanel p_condition, p_detail;
    private String s1[] = {"收入", "支出"};
    private double bal1, bal2;
    private JTable table;
    private String username;

    public MainFrame(String username) {
        super(username + ",欢迎使用个人理财账本!");
        this.username = username;
        setLayout(new BorderLayout());
        add(mb, BorderLayout.NORTH);
        mb.add(m_system);
        mb.add(m_fm);
        m_system.add(mI[0]);
        m_system.add(mI[1]);
        m_fm.add(m_FMEdit);
        m_FMEdit.addActionListener(this);
        mI[0].addActionListener(this);
        mI[1].addActionListener(this);

        l_type = new JLabel("收支类型：");
        c_type = new JComboBox(s1);
        b_select1 = new JButton("查询");
        l_fromDate = new JLabel("起始时间");
        t_fromDate = new JTextField(8);
        l_toDate = new JLabel("终止时间");
        t_toDate = new JTextField(8);
        b_select2 = new JButton("查询");
        l_ps = new JLabel("注意：时间格式为YYYYMMDD，例如：20150901");
        p_condition = new JPanel();
        p_condition.setLayout(new GridLayout(3, 1));
        p_condition.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("输入查询条件"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        p1.add(l_type);
        p1.add(c_type);
        p1.add(b_select1);
        p2.add(l_fromDate);
        p2.add(t_fromDate);
        p2.add(l_toDate);
        p2.add(t_toDate);
        p2.add(b_select2);
        p3.add(l_ps);
        p_condition.add(p1);
        p_condition.add(p2);
        p_condition.add(p3);
        add(p_condition, BorderLayout.CENTER);

        b_select1.addActionListener(this);
        b_select2.addActionListener(this);

        p_detail = new JPanel();
        p_detail.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("收支明细信息"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        l_bal = new JLabel();
        String[] column = {"编号", "日期", "类型", "内容", "金额",};
        Object[][] row = new Object[50][5];
        table = new JTable(row, column);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(580, 350));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setViewportView(table);
        p_detail.add(l_bal);
        p_detail.add(scrollPane);
        add(p_detail, BorderLayout.SOUTH);

        // 添加代码

        if (bal1 < 0)
            l_bal.setText("个人总收支余额为" + bal1 + "元。您已超支，请适度消费！");
        else
            l_bal.setText("个人总收支余额为" + bal1 + "元。");
    }

    public void actionPerformed(ActionEvent e) {
        Object temp = e.getSource();
        if (temp == mI[0]) {
            ModifyPwdFrame mpf = new ModifyPwdFrame(username);
            mpf.setResizable(false);
            mpf.setSize(280, 160);
            Dimension screen = mpf.getToolkit().getScreenSize();
            mpf.setLocation((screen.width - mpf.getSize().width) / 2, (screen.height - mpf.getSize().height) / 2);
            mpf.setVisible(true);
        } else if (temp == mI[1]) {
            // 添加代码
        } else if (temp == m_FMEdit) {
            BalEditFrame bf = new BalEditFrame();
            bf.setResizable(false);
            bf.setSize(800, 300);
            Dimension screen = bf.getToolkit().getScreenSize();
            bf.setLocation((screen.width - bf.getSize().width) / 2, (screen.height - bf.getSize().height) / 2);
            bf.setVisible(true);
        } else if (temp == b_select1) { // 根据收支类型查询
            // 添加代码
        } else if (temp == b_select2) { // 根据时间范围查询
            // 添加代码
        }
    }
}
