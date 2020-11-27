package dao;


import java.util.LinkedHashMap;
import java.util.List;

import pojo.Employee;
import util.SqlHelper;

/**
 * 用户管理DAO
 */
public class UserDaoImpl {
    /**
     * 插入
     * @param emp
     */
    public void insert(Employee emp) {
        String sql="insert into employee (employee_real_name,employee_sex,employee_birthday,employee_telephone) values (?,?,?,?)";

        String real_name=emp.getEmployee_real_name()==null?null:emp.getEmployee_real_name().toString();
        String sex=emp.getEmployee_sex()==null?null:emp.getEmployee_sex().toString();
        String birthday=emp.getEmployee_birthday()==null?null:emp.getEmployee_birthday().toString();
        String telephone=emp.getEmployee_telephone()==null?null:emp.getEmployee_telephone().toString();

        try{
            SqlHelper.update(sql, real_name,sex,birthday,telephone);
        }catch(Exception e) {
            throw new RuntimeException("插入数据失败",e);
        }

    }
    /**
     * 查询雇员表;
     * @return
     */
    public List<LinkedHashMap<String,Object>>  selectEmployee() {
        String sql="select * from employee";
        List<LinkedHashMap<String,Object>> list=null;
        try {
            list=SqlHelper.select3(sql);
        }catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查数据异常",e);
        }

        return list;
    }

    /**
     * 查询雇员表,id
     * @return
     */
    public List<LinkedHashMap<String,Object>>  selectEmployeeId(Object...params) {
        String sql="select * from employee where employee_id = ?";
        List<LinkedHashMap<String,Object>> list=null;
        try {
            list=SqlHelper.select3(sql,params);
        }catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查数据异常",e);
        }

        return list;
    }

    /**
     * 查询雇员表,realname
     * @return
     */
    public List<LinkedHashMap<String,Object>>  selectEmployeeRealName(Object...params) {
        String sql="select * from employee where employee_real_name like ? ";
        List<LinkedHashMap<String,Object>> list=null;
        try {
            list=SqlHelper.select3(sql,params);
        }catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查数据异常",e);
        }

        return list;
    }
    /**
     * 删除员工
     */
    public int deleteEmployee(Object...params) {
        String sql="delete from employee where employee_id= ?";
        int row=SqlHelper.update(sql, params);
        return row;
    }

    /**
     * 修改雇员表的信息
     * @param params
     * @return
     */
    public int updateEmployee(Object...params) {
        String sql="update employee set employee_real_name=?,employee_sex=?,employee_birthday=?,employee_telephone=? where employee_id=?";
        int row=0;
        try {
            row=SqlHelper.update(sql, params);
        }catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("",e);
        }

        return row;
    }

}
