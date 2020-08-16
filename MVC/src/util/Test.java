package util;

import java.sql.*;

public class Test {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/practice?useSSL" +
                    "=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "root", "lunar");
            String sql = "select count(*) from books";
            PreparedStatement pre = con.prepareStatement(sql);
            ResultSet res = pre.executeQuery();
            res.next(); //res.next()是必须要有的
            long s = (long)res.getObject(1);
            System.out.println("总数： " + s);
            res.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
