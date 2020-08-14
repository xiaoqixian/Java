/**********************************************
  > File Name		: MysqlTest.java
  > Author			: lunar
  > Email			: lunar_ubuntu@qq.com
  > Created Time	: Thu 13 Aug 2020 03:20:05 PM CST
 **********************************************/

import java.sql.*;

public class MysqlTest {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/hello?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    static final String user = "root";
    static final String password = "lunar";

    public static void main(String[] args) {
        Connection con = null;
        Statement statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to the database...");
            con = DriverManager.getConnection(DB_URL, user, password);

            statement = con.createStatement();
            String sql = "select id,name,url from websites";
            ResultSet res = statement.executeQuery(sql);

            while (res.next()) {
                int id = res.getInt("id");
                String name = res.getString("name");
                String url = res.getString("url");

                System.out.println("ID: " + id + ", website: " + name + ", url: " + url);
            }
            res.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
