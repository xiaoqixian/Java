package util;

/**
 * 数据库连接及执行增删改查
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.List;
import java.lang.reflect.Field;

public class DBUtil {
    static Vector<Connection> connectionPool = new Vector<>();
    static {
        for (int i = 0; i < 5; i++) {
            try {
                //mysql8.0版本以上的配置
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/practice?useSSL=false&" +
                                "allowPublicKeyRetrieval=true&serverTimezone=UTC",
                        "root","lunar");
                connectionPool.add(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection() {
        Connection con = connectionPool.get(0);
        connectionPool.remove(0);
        return con;
    }

    public static void releaseConnection(Connection con) {
        connectionPool.add(con);
    }

    //sql示例：select id,name,price from books limit ? offset ?
    public static List query(Class<?> c, String sql, int start, int limit, Object... objs) {
        Connection con = getConnection();
        List<Object> list = new ArrayList<>(limit);
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            //setObject的索引从1开始
            pre.setObject(1, limit);
            pre.setObject(2, start);
            for (int i = 0; i < objs.length; i++) {
                pre.setObject(i+2, objs[i]);
            }
            ResultSet res = pre.executeQuery();
            ResultSetMetaData metaData = res.getMetaData();
            //利用反射将结果注入到Java类中
            while (res.next()) {
                Object obj = c.newInstance();
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    String fieldName = metaData.getColumnLabel(i);//label即as后面的东西
                    System.out.println("fieldName: " + fieldName + ", value: " + res.getObject(i));
                    Field field = c.getDeclaredField(fieldName);
                    field.setAccessible(true);
                    field.set(obj, res.getObject(i));
                }
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            releaseConnection(con);
        }
        System.out.println("list length: " + list.size());
        return list;
    }

    //返回数据库所有记录的数量
    public static int count(String sql) {
        /*Connection con = getConnection();
        int count = 0;
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            ResultSet res = pre.executeQuery();
            res.next();
            count = (int)res.getObject(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            releaseConnection(con);
        }
        return count;*/
        return 8;
    }

    //delete SQL: delete from books where id=?
    public static void delete(String sql, int id) {
        Connection con = getConnection();
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setObject(1, id);
            pre.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            releaseConnection(con);
        }
    }

    //insert SQL: insert into book (name, price) values (?,?);
    //id是自增主键
    public static void insert(String sql, String name, int price) {
        Connection con = getConnection();
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setObject(1, name);
            pre.setObject(2, price);
            pre.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            releaseConnection(con);
        }
    }

    public static void update(String sql, String id) {
        //由于编辑需要一个页面，暂时未定
    }
}
