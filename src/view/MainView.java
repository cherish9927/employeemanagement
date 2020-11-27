package view;

import view.RegisterView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;

public class MainView extends JFrame{

    private final JMenuBar menuBar = new JMenuBar();

    public MainView() {
        this.setTitle("人力资源管理系统");
        this.setSize(668, 431);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //可见
        this.setVisible(true);
        //居中
        this.setLocationRelativeTo(null);


        setJMenuBar(menuBar);

        JMenu menu = new JMenu("权限管理");
        menuBar.add(menu);

        JMenuItem menuItem = new JMenuItem("切换账户");
        menu.add(menuItem);
        //给切换账户添加监听事件，即返回到登录界面
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                //点击之后，重新new 一个登录框
                LoginView loginView=new LoginView();
                //跳转之后，把原先自己 隐藏掉
                MainView.this.dispose();
            }
        });


        JSeparator separator = new JSeparator();
        menu.add(separator);

        JMenuItem menuItem_2 = new JMenuItem("退出");
        menu.add(menuItem_2);
        //给退出添加，监听事件，
        menuItem_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });

        JMenu menu_1 = new JMenu("员工信息管理");
        menuBar.add(menu_1);

        //职员入职，即 注册功能
        JMenuItem mntmNewMenuItem = new JMenuItem("添加员工");
        menu_1.add(mntmNewMenuItem);
        mntmNewMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                RegisterView rv=new RegisterView();
            }
        });

        JMenuItem mntmNewMenuItem_1 = new JMenuItem("修改员工资料");
        menu_1.add(mntmNewMenuItem_1);
        mntmNewMenuItem_1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                new EmployeeExcelView();
            }
        });

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(MainView.class.getResource("/base/4.jpg")));
        getContentPane().add(lblNewLabel, BorderLayout.CENTER);
    }
}
