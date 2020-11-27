package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import pojo.User;
import service.LoginServiceImpl;
import view.MainView;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

/**
 * swing 人力资源管理系统
 * 需求分析：
 * 1、登录、注册
 *
 */
public class LoginView extends JFrame{
    JButton loginBtn=new JButton("登录");
    JButton exitBtn=new JButton("退出");
    JTextField loginText=new JTextField();
    JPasswordField passWordText =new JPasswordField(20);
    JLabel jLabel1=new JLabel("用户名：");
    JLabel jLabel2=new JLabel("密码：");



    public LoginView() {

        //设置布局  绝对布局
        getContentPane().setLayout(null);


        loginBtn.setBounds(300, 200, 70, 35);
        getContentPane().add(loginBtn);

        exitBtn.setBounds(300, 250, 70, 35);
        getContentPane().add(exitBtn);

        jLabel1.setBounds(20, 200, 70, 35);
        getContentPane().add(jLabel1);

        jLabel2.setBounds(20, 250, 70, 35);
        getContentPane().add(jLabel2);

        loginText.setBounds(90, 200, 180, 35);
        getContentPane().add(loginText);

        passWordText.setBounds(90, 250, 180, 35);
        getContentPane().add(passWordText);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(LoginView.class.getResource("/base/background.jpg")));
        lblNewLabel.setBounds(0, 0, 384, 190);
        getContentPane().add(lblNewLabel);

        //添加事件
        //退出事件
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });

        //登录事件
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                //获取用户名
                String username= loginText.getText();
                //获得密码
                String password= new String(passWordText.getPassword());

                LoginServiceImpl lsi=new LoginServiceImpl();
//				SuperManageService ms=new SuperManageService();
                try {
                    User user=lsi.login(username, password);
                    if(user==null) {
                        //弹框
                        JOptionPane.showMessageDialog(LoginView.this, "用户名或密码错误");
                    }else {
                        //跳转进入下个页面
                        MainView mainView=new MainView();
                        //跳转之后，把登录框 隐藏掉
                        LoginView.this.dispose();
                    }
                }catch(Exception e) {
                    JOptionPane.showMessageDialog(LoginView.this, "登录异常");
                }
            }
        });


        this.setTitle("人力资源管理系统");
        this.setSize(400, 350);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //可见
        this.setVisible(true);
        //居中
        this.setLocationRelativeTo(null);




    }
}







