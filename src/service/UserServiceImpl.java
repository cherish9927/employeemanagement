package service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

import dao.UserDaoImpl;
import dao.UserDaoImpl;
import pojo.Employee;
import pojo.Employee;

public class UserServiceImpl {
    UserDaoImpl userDaoImpl=new UserDaoImpl();
    public void register (Employee emp) {
        try {
            userDaoImpl.insert(emp);
        }catch(Exception e) {
            throw new RuntimeException("插入数据失败",e);
        }
    }

    /**
     * 查询雇员,使用 Vector
     */
    public Vector selectVector(Object...params) {
        //动态数组,双层嵌套,泛型,
        Vector<Vector<String>> vectors=new Vector<Vector<String>>();
        List<LinkedHashMap<String,Object>> list=null;
        try {
            list =userDaoImpl.selectEmployee();

        }catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("读取数据异常",e);
        }
        for(int i=0;i<list.size();i++) {
            Vector<String> _vector=new Vector<>();
            Set <String> sets= list.get(i).keySet();

            for(String s :sets) {
                _vector.add(list.get(i).get(s)==null?null:list.get(i).get(s).toString());
            }
            vectors.add(_vector);
        }

        return vectors;
    }
    /**
     * 查询雇员,使用 Vector  id
     */
    public Vector selectVectorId(Object...params) {
        //动态数组,双层嵌套,泛型,
        Vector<Vector<String>> vectors=new Vector<Vector<String>>();
        List<LinkedHashMap<String,Object>> list=null;
        try {

            list =userDaoImpl.selectEmployeeId(params);


        }catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("读取数据异常",e);
        }
        for(int i=0;i<list.size();i++) {
            Vector<String> _vector=new Vector<>();
            Set <String> sets= list.get(i).keySet();

            for(String s :sets) {
                _vector.add(list.get(i).get(s)==null?null:list.get(i).get(s).toString());
            }
            vectors.add(_vector);
        }

        return vectors;
    }
    /**
     * 查询雇员,使用 Vector,    realname
     */
    public Vector selectRealNameVector(Object...params) {
        //动态数组,双层嵌套,泛型,
        Vector<Vector<String>> vectors=new Vector<Vector<String>>();
        List<LinkedHashMap<String,Object>> list=null;
        try {
            list =userDaoImpl.selectEmployeeRealName(params);

        }catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("读取数据异常",e);
        }
        for(int i=0;i<list.size();i++) {
            Vector<String> _vector=new Vector<>();
            Set <String> sets= list.get(i).keySet();

            for(String s :sets) {
                _vector.add(list.get(i).get(s)==null?null:list.get(i).get(s).toString());
            }
            vectors.add(_vector);
        }

        return vectors;
    }

    /**
     * 业务职员离职
     * @param params
     * @return
     */
    public int deleteEmployee (Object...params) {
        int row =userDaoImpl.deleteEmployee(params);
        return row;
    }
    /**
     * 修改
     * @param params
     * @return
     */
    public int updateEmployee(Object...params) {
        int row =userDaoImpl.updateEmployee(params);
        return row;
    }
}
