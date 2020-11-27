package service;

import dao.LoginDaoImpl;
import pojo.User;

public class LoginServiceImpl {
    LoginDaoImpl daoImpl=new LoginDaoImpl();

    public User login(String username,String password) {
        return daoImpl.selectUser(username, password);
    }
}
