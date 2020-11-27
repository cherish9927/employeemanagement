package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import pojo.Employee;
import service.UserServiceImpl;

public class UpdateView extends JFrame{
    UserServiceImpl usi=new UserServiceImpl();
    Employee emp;
    int row;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;

    public UpdateView(Employee emp,int row) {
        this.emp=emp;
        this.row=row;
        this.setTitle("修改页面");
        this.setSize(300, 344);
        //可见
        this.setVisible(true);
        //居中
        this.setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("真实姓名：");
        lblNewLabel_1.setBounds(10, 35, 75, 15);
        getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("性别：");
        lblNewLabel_2.setBounds(10, 60, 54, 15);
        getContentPane().add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("生日：");
        lblNewLabel_3.setBounds(10, 85, 54, 15);
        getContentPane().add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("电话：");
        lblNewLabel_4.setBounds(10, 110, 54, 15);
        getContentPane().add(lblNewLabel_4);


        JButton btnNewButton = new JButton("确认修改");
        btnNewButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String employee_real_name=textField_1.getText();
                String employee_sex=textField_2.getText();
                String employee_birthday=textField_3.getText();
                String employee_telephone=textField_4.getText();

                try {
                    usi.updateEmployee(employee_real_name,employee_sex,employee_birthday,employee_telephone,row);
                    JOptionPane.showMessageDialog(UpdateView.this, "修改完成");
                }catch(Exception ex) {
                    JOptionPane.showMessageDialog(UpdateView.this, "修改失败，用户名重复");
                    ex.printStackTrace();
                }

            }
        });
        btnNewButton.setBounds(181, 272, 93, 23);
        getContentPane().add(btnNewButton);

        textField_1 = new JTextField();
        textField_1.setBounds(95, 32, 141, 21);
        getContentPane().add(textField_1);
        textField_1.setColumns(10);

        textField_2 = new JTextField();
        textField_2.setBounds(95, 57, 141, 21);
        getContentPane().add(textField_2);
        textField_2.setColumns(10);

        textField_3 = new JTextField();
        textField_3.setBounds(95, 82, 141, 21);
        getContentPane().add(textField_3);
        textField_3.setColumns(10);

        textField_4 = new JTextField();
        textField_4.setBounds(95, 107, 141, 21);
        getContentPane().add(textField_4);
        textField_4.setColumns(10);

        textField_1.setText(emp.getEmployee_real_name());
        textField_2.setText(emp.getEmployee_sex());
        textField_3.setText(emp.getEmployee_birthday());
        textField_4.setText(emp.getEmployee_telephone());

    }
}
