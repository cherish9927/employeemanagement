package view;
/**
 * 注册页面
 */
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.LoginDaoImpl;
import pojo.Employee;
import service.UserServiceImpl;
//import service.UserServiceImpl;

import javax.swing.JRadioButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class RegisterView extends JFrame{


    private JTextField textField_3;
    private JTextField textField_5;
    private JTextField textField_6;
    public RegisterView() {
        setTitle("职员注册界面");
        getContentPane().setLayout(null);

        //姓名
        JLabel label = new JLabel("姓名：");
        label.setBounds(43, 56, 79, 15);
        getContentPane().add(label);

        //年龄
        JLabel label_1 = new JLabel("生日：");
        label_1.setBounds(43, 87, 79, 15);
        getContentPane().add(label_1);

        //性别
        JLabel label_2 = new JLabel("性别：");
        label_2.setBounds(43, 190, 54, 15);
        getContentPane().add(label_2);

        //电话
        JLabel label_5 = new JLabel("电话：");
        label_5.setBounds(43, 123, 79, 15);
        getContentPane().add(label_5);

        JRadioButton rdbtnNewRadioButton = new JRadioButton("男");
        rdbtnNewRadioButton.setBounds(126, 186, 61, 23);
        getContentPane().add(rdbtnNewRadioButton);
        JRadioButton radioButton = new JRadioButton("女");
        radioButton.setBounds(207, 186, 61, 23);
        getContentPane().add(radioButton);

        //逻辑组件，添加 男、女；
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(rdbtnNewRadioButton);
        buttonGroup.add(radioButton);

        JButton button = new JButton("确认注册");
        button.setBounds(359, 407, 93, 23);
        getContentPane().add(button);

        textField_3 = new JTextField();
        textField_3.setBounds(160, 120, 140, 21);
        getContentPane().add(textField_3);
        textField_3.setColumns(10);

        textField_5 = new JTextField();
        textField_5.setBounds(160, 53, 140, 21);
        getContentPane().add(textField_5);
        textField_5.setColumns(10);

        textField_6 = new JTextField();
        textField_6.setBounds(160, 84, 140, 21);
        getContentPane().add(textField_6);
        textField_6.setColumns(10);
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                String userrealname = textField_5.getText();
                String usertelephone = textField_3.getText();
                String userbirthday = textField_6.getText();
                String sex = "男";
                if (radioButton.isSelected()) {
                    sex = "女";
                }
                Employee emp = new Employee();
                emp.setEmployee_real_name(userrealname);
                emp.setEmployee_birthday(userbirthday);
                emp.setEmployee_sex(sex);
                emp.setEmployee_telephone(usertelephone);
                UserServiceImpl usi = new UserServiceImpl();
                try {
                    usi.register(emp);
                    JOptionPane.showMessageDialog(RegisterView.this, "注册成功");
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(RegisterView.this, "注册失败,用户重名");
                }
            }
        });


        this.setSize(508, 479);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //可见
        this.setVisible(true);
        //居中
        this.setLocationRelativeTo(null);
    }
}





