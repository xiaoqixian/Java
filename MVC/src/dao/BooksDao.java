package dao;

/**
 * Data Access Object
 *
 * 负责操作数据库中的数据
 */

import beans.Books;
import util.DBUtil;
import java.util.List;

public class BooksDao {
    public static void addBook(String name, int price) {
        String sql = "insert into books (name, price) values (?,?)";
        try {
            DBUtil.insert(sql, name, price);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delBook(int id) {
        String sql = "delete from books where id=?";
        DBUtil.delete(sql, id);
    }

    public static List<Books> select(int start, int limit) {
        String sql = "select * from books limit ? offset ?";
        List<Books> res = DBUtil.query(Books.class, sql, start, limit);
        return res;
    }
}
