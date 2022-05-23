import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// 收支编辑界面
class BalEditFrame extends JFrame implements ActionListener, GlobalDB {
    private final JLabel l_id, l_date, l_bal, l_type, l_item;
    private JTextField t_id, t_date, t_bal;
    private JComboBox c_type, c_item;
    private JButton b_update, b_delete, b_select, b_new, b_clear;
    private JPanel p1, p2, p3;
    private JScrollPane scrollPane;
    private JTable table;
    private String[] column = {"编号", "日期", "类型", "内容", "金额",};
    private DefaultTableModel defaultTableModel;
    private Object[][] tableData;

    public BalEditFrame() {
        super("收支编辑");
        l_id = new JLabel("编号：");
        l_date = new JLabel("日期：");
        l_bal = new JLabel("金额：");
        l_type = new JLabel("类型：");
        l_item = new JLabel("内容：");
        t_id = new JTextField(8);
        t_date = new JTextField(8);
        t_bal = new JTextField(8);

        String s1[] = {"全部", "收入", "支出"};
        String s2[] = {"购物", "餐饮", "居家", "交通", "娱乐", "人情", "工资", "奖金", "其他"};
        c_type = new JComboBox(s1);
        c_item = new JComboBox(s2);

        b_select = new JButton("查询");
        b_update = new JButton("修改");
        b_delete = new JButton("删除");
        b_new = new JButton("录入");
        b_clear = new JButton("清空");

        Container c = this.getContentPane();
        c.setLayout(new BorderLayout());

        p1 = new JPanel();
        p1.setLayout(new GridLayout(5, 2, 10, 10));
        p1.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("编辑收支信息"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        p1.add(l_id);
        p1.add(t_id);
        p1.add(l_date);
        p1.add(t_date);
        p1.add(l_type);
        p1.add(c_type);
        p1.add(l_item);
        p1.add(c_item);
        p1.add(l_bal);
        p1.add(t_bal);
        add(p1, BorderLayout.WEST);

        p2 = new JPanel();
        p2.setLayout(new GridLayout(5, 1, 10, 10));
        p2.add(b_new);
        p2.add(b_update);
        p2.add(b_delete);
        p2.add(b_select);
        p2.add(b_clear);

        add(p2, BorderLayout.CENTER);

        p3 = new JPanel();
        p3.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("显示收支信息"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        table = new JTable();
//        tableData = db.findByType("全部");
//        this.makeTable();

        scrollPane = new JScrollPane(table);
        scrollPane.setViewportView(table);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        p3.add(scrollPane);
        add(p3, BorderLayout.EAST);

        b_update.addActionListener(this);
        b_delete.addActionListener(this);
        b_select.addActionListener(this);
        b_new.addActionListener(this);
        b_clear.addActionListener(this);

        // 添加代码，为table添加鼠标点击事件监听addMouseListener
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowSelected = table.rowAtPoint(e.getPoint());
                t_id.setText(tableData[rowSelected][0].toString());
                t_id.setEnabled(false);
                t_date.setText(tableData[rowSelected][1] + "");
                c_type.setSelectedItem(tableData[rowSelected][2]);
                c_item.setSelectedItem(tableData[rowSelected][3]);
                t_bal.setText(tableData[rowSelected][4].toString());

            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        if (b_select == e.getSource()) { // 查询所有收支信息
            tableData = db.findByType(c_type.getSelectedItem().toString());
            this.makeTable();
        } else if (b_update == e.getSource()) { // 修改某条收支信息
            String id = t_id.getText();
            String rdate = t_date.getText();
            String rtype = c_type.getSelectedItem().toString();
            String ritem = c_item.getSelectedItem().toString();
            String bal = t_bal.getText();
            if (rdate.length() * bal.length() == 0) {
                JOptionPane.showMessageDialog(this, "数据填入错误", "警告", JOptionPane.ERROR_MESSAGE);
            } else {
                db.updateData(id, rdate, rtype, ritem, Integer.parseInt(bal));
                tableData = db.findByType(c_type.getSelectedItem().toString());
                this.makeTable();
            }
        } else if (b_delete == e.getSource()) { // 删除某条收支信息
            String id = t_id.getText();
            if (id.length() == 0) {
                JOptionPane.showMessageDialog(this, "请先选中数据", "提示", JOptionPane.ERROR_MESSAGE);
            } else {
                int res = JOptionPane.showConfirmDialog(this, "确认删除吗？", "提示", JOptionPane.YES_NO_OPTION);
                if (res == 0) {
                    db.deleteData(id);
                    tableData = db.findByType("全部");
                    this.makeTable();
                }
            }
        } else if (b_new == e.getSource()) { // 新增某条收支信息
            String id = t_id.getText();
            String rdate = t_date.getText();
            String rtype = c_type.getSelectedItem().toString();
            String ritem = c_item.getSelectedItem().toString();
            String bal = t_bal.getText();
            if (rdate.length() * bal.length() == 0) {
                JOptionPane.showMessageDialog(this, "数据填入错误", "警告", JOptionPane.ERROR_MESSAGE);
            } else {
                db.insertData(id, rdate, rtype, ritem, Integer.parseInt(bal));
                tableData = db.findByType("全部");
                this.makeTable();
            }
        } else if (b_clear == e.getSource()) { // 清空输入框
            t_id.setText("");
            t_id.setEnabled(true);
            t_date.setText("");
            c_type.setSelectedIndex(0);
            c_item.setSelectedIndex(0);
            t_bal.setText("");
        }

    }

    private void makeTable() {
        defaultTableModel = new DefaultTableModel(tableData, column) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.table.setModel(defaultTableModel);
    }
}
