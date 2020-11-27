package util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.management.RuntimeErrorException;

/**
 * 数据库操作的工具类（1、sql语句不同   2、参数不同    3、查询和 dml 不同）
 * @author WZW
 *
 */
public class SqlHelper {

    private static ThreadLocal<Connection> local=new ThreadLocal<>();
    //配置文件
    private static Properties properties =new Properties();

    static {
        try {
            properties.load(SqlHelper.class.getClassLoader().getResourceAsStream("jdbc.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 加载驱动
     */
    static {
        try {
            Class.forName(properties.getProperty("Driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * 建立连接
     * @return
     */
    public static Connection openConnection() {
        Connection conn=local.get();
        try {
            if(conn==null||conn.isClosed()) {
                conn=DriverManager.getConnection(properties.getProperty("URL"),properties.getProperty("USER"),properties.getProperty("PASSWORD"));
                local.set(conn);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("建立连接异常",e);
        }
        return conn;
    }
    /**
     * 查询
     */
    @Deprecated
    public static ResultSet select (String sql,Object ...params) {
        Connection conn=SqlHelper.openConnection();
        ResultSet rs=null;
        PreparedStatement pst=null;
        try {
            pst=conn.prepareStatement(sql);
            if(params!=null) {
                for(int i=0;i<params.length;i++) {
                    pst.setObject(i+1, params[i]);
                }
            }
            rs=pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return rs;
    }

    /**
     * 更新
     */
    public static int  update (String sql,Object ...params) {
        Connection conn=SqlHelper.openConnection();
        int row=0;
        PreparedStatement pst=null;
        try {
            pst=conn.prepareStatement(sql);
            if(params!=null) {
                for(int i=0;i<params.length;i++) {
                    pst.setObject(i+1, params[i]);
                }
            }
            row=pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("更改数据失败",e);
        }finally {
            if(pst!=null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return row;
    }
    /**
     * 查询返回List   ; 使用解析的接口
     */
    public static <T> List<T> select2 (String sql,RowHandlerMapper<T> handlerMapper,Object...params){
        Connection conn=SqlHelper.openConnection();
        ResultSet rs=null;
        PreparedStatement pst=null;
        List<T> rows =null;
        try {
            pst=conn.prepareStatement(sql);
            if(params!=null) {
                for(int i=0;i<params.length;i++) {
                    pst.setObject(i+1, params[i]);
                }
            }
            rs=pst.executeQuery();

            rows=handlerMapper.maping(rs);   //数据解析完毕后，   ====相当于等于   调用了实现子类 Maping 方法
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(rs!=null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(pst!=null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return rows;

    }

    /**
     * 把Map  放入List中 返回
     */
    public static List<LinkedHashMap<String,Object>> select3(String sql,Object...params){

        Connection conn =SqlHelper.openConnection();
        ResultSet rs=SqlHelper.select(sql, params);
        ResultSetMetaData rsmd = null;
        int columnCount = 0;
        try {
            rsmd = rs.getMetaData();
            // 获得结果集列数
            columnCount = rsmd.getColumnCount();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // 创建List
        List<LinkedHashMap<String,Object>> list = new ArrayList<LinkedHashMap<String,Object>>();

        try {
            // 将ResultSet的结果保存到List中
            while (rs.next()) {
                LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    map.put(rsmd.getColumnLabel(i), rs.getObject(i));
//	                   System.out.println(rsmd.getColumnLabel(i)+":"+ rs.getObject(i));
                }
                list.add(map);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }finally {
            if(rs!=null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return list;

    }
    /**
     * 关闭连接
     */
    public static void close() {
        Connection conn=local.get();
        if(conn!=null) {
            try {
                conn.close();
                local.remove();
                conn=null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static interface RowHandlerMapper<T> {
        public  <T> List<T> maping(ResultSet rs);
    }
}


