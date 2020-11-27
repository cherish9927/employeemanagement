package dao;

import java.util.LinkedHashMap;
import java.util.List;

import pojo.User;
import util.SqlHelper;

/**
 * 登录数据库访问对象
 * @author WZW
 *
 */
public class LoginDaoImpl {

    public User selectUser(String userName,String passWord) {
        User user=null;
        String sql="select * from User where user_name=? and user_password=?";
        List<LinkedHashMap<String,Object>> list= SqlHelper.select3(sql, userName,passWord);
        if(list!=null&&list.size()>0) {
            LinkedHashMap<String,Object> map =list.get(0);
            user=new User();
            user.setId(map.get("id")==null?null:map.get("id").toString());
            user.setUserName(map.get("user_name")==null?null:map.get("user_name").toString());
            user.setUserPassword(map.get("user_password")==null?null:map.get("user_password").toString());
            user.setUserRealName(map.get("user_real_name")==null?null:map.get("user_real_name").toString());
            user.setUserSex(map.get("user_sex")==null?null:map.get("user_sex").toString());
            user.setUserAge(map.get("user_age")==null?null:map.get("user_age").toString());
        }
        return user;
    }
}
