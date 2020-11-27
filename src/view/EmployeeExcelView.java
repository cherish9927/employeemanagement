package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import pojo.Employee;
import service.UserServiceImpl;
import view.UpdateView;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class EmployeeExcelView extends JFrame{
    UserServiceImpl usi=new UserServiceImpl();
    int row=0;
    private JTextField textField;
    String []header= {"编号","真实姓名","性别","生日","电话"};
    String [][]strings1=new String [100][5];
    private JTable table = new JTable(strings1,header);
    private JTextField textField_1;
    DefaultTableModel dataModel=null;
    private JTextField textField_2;
    public EmployeeExcelView() {
        this.setTitle("员工信息管理");
        //大小
        this.setSize(847, 593);
        //可见
        this.setVisible(true);
        //居中
        this.setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "搜索面板", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBounds(48, 21, 575, 125);
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel label = new JLabel("编号：");
        label.setBounds(28, 24, 54, 15);
        panel.add(label);

        textField = new JTextField();
        textField.setBounds(79, 21, 104, 21);
        panel.add(textField);
        textField.setColumns(10);

        JButton button = new JButton("搜索ID");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String id=textField.getText();
                    Vector<Vector<String>> vectors =usi.selectVectorId(id);
                    //头
                    Vector new_header=new Vector();
                    for(int i=0;i<header.length;i++) {
                        new_header.add(header[i]);
                    }
                    dataModel=new DefaultTableModel(vectors,new_header);
                    table.setModel(dataModel);
                    table.updateUI();
                }catch(Exception ex) {
                    JOptionPane.showMessageDialog(EmployeeExcelView.this, "未知错误");
                }
            }
        });
        button.setBounds(229, 20, 93, 23);
        panel.add(button);



        JLabel lblNewLabel = new JLabel("姓名：");
        lblNewLabel.setBounds(28, 53, 54, 15);
        panel.add(lblNewLabel);

        textField_1 = new JTextField();
        textField_1.setBounds(79, 50, 104, 21);
        panel.add(textField_1);
        textField_1.setColumns(10);

        JButton btnNewButton = new JButton("搜索姓名");
        btnNewButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name=textField_1.getText();
                    String realname="%"+name+"%";
                    Vector<Vector<String>> vectors =usi.selectRealNameVector(realname);
                    //头
                    Vector new_header=new Vector();
                    for(int i=0;i<header.length;i++) {
                        new_header.add(header[i]);
                    }
                    dataModel=new DefaultTableModel(vectors,new_header);
                    table.setModel(dataModel);
                    table.updateUI();
                }catch(Exception ex) {
                    JOptionPane.showMessageDialog(EmployeeExcelView.this, "未知错误");
                }
            }
        });
        btnNewButton.setBounds(229, 49, 93, 23);
        panel.add(btnNewButton);

        JButton btnNewButton_2 = new JButton("删除");
        btnNewButton_2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                usi.deleteEmployee(row);
                Vector<Vector<String>> vectors =usi.selectVector();
                Vector<String> h=new Vector<String>();
                for(int i=0;i<header.length;i++){
                    h.add(header[i]);
                }
                dataModel=new DefaultTableModel(vectors,h);
                table.setModel(dataModel);
                table.updateUI();
                JOptionPane.showMessageDialog(EmployeeExcelView.this, "删除成功");

            }
        });
        btnNewButton_2.setBounds(345, 20, 93, 23);
        panel.add(btnNewButton_2);


        JPanel panel_1 = new JPanel();
        panel_1.setBounds(658, 426, -607, -291);
        getContentPane().add(panel_1);

        JPanel panel_2 = new JPanel();
        panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "员工信息：", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel_2.setBounds(10, 156, 811, 367);
        getContentPane().add(panel_2);
        panel_2.setLayout(new BorderLayout(0, 0));

        JPopupMenu popup = new JPopupMenu();
        JMenuItem jmi1 = new JMenuItem("修改");
        jmi1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                Vector<Vector<String>> vectors =usi.selectVectorId(row);
                Employee emp=new Employee();
                emp.setEmployee_real_name(vectors.get(0).get(1));
                emp.setEmployee_sex(vectors.get(0).get(2));
                emp.setEmployee_birthday(vectors.get(0).get(3));
                emp.setEmployee_telephone(vectors.get(0).get(4));
                new UpdateView(emp,row);
            }
        });


        JMenuItem jmi2 = new JMenuItem("删除");
        jmi2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                usi.deleteEmployee(row);
                Vector<Vector<String>> vectors =usi.selectVector();
                Vector<String> h=new Vector<String>();
                for(int i=0;i<header.length;i++){
                    h.add(header[i]);
                }
                dataModel=new DefaultTableModel(vectors,h);
                table.setModel(dataModel);
                table.updateUI();
                JOptionPane.showMessageDialog(EmployeeExcelView.this, "删除成功");
            }
        });
        popup.add(jmi1);
        popup.add(jmi2);

        JScrollPane scrollPane = new JScrollPane();
        panel_2.add(scrollPane, BorderLayout.CENTER);
        table.setComponentPopupMenu(popup);


        scrollPane.setViewportView(table);

        JButton btnNewButton_1 = new JButton("修改");
        btnNewButton_1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Vector<String>> vectors =usi.selectVectorId(row);
                Employee emp=new Employee();
                emp.setEmployee_real_name(vectors.get(0).get(1));
                emp.setEmployee_sex(vectors.get(0).get(2));
                emp.setEmployee_birthday(vectors.get(0).get(3));
                emp.setEmployee_telephone(vectors.get(0).get(4));
                new UpdateView(emp,row);
            }
        });
        btnNewButton_1.setBounds(728, 17, 93, 23);
        getContentPane().add(btnNewButton_1);

        /**
         * 给表格增加右键菜单
         */
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                // 取得右键点击所在行
                row=evt.getY()/table.getRowHeight()+1;
            }
        });
    }
}
