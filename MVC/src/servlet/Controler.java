package servlet;

import util.DBUtil;

import javax.servlet.ServletException;
//import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import dao.BooksDao;
import beans.Books;

@WebServlet("/")
public class Controler extends HttpServlet{
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        int start = 0, limit = 5;
        try {
            start = Integer.parseInt(request.getParameter("start"));
        } catch (Exception e) {
            //可能没有start参数
        }
        String sql = "select count(*) from books";
        int total = DBUtil.count(sql);
        int pre = start - limit < 0 ? 0 : start - limit;
        int next = start + limit > total ? 0 : start + limit;
        int last = total / limit;
        if (start/limit == total/limit) {
            limit = total - start;
        }
        request.setAttribute("pre", pre);
        request.setAttribute("next", next);
        request.setAttribute("last", last);
        List<Books> books = BooksDao.select(start, limit);
        System.out.println("Books length: " + books.size());
        //getServletContext().log("books length: " + books.size());
        request.setAttribute("books", books);

        request.getRequestDispatcher("view.jsp").forward(request, response);
    }

    @Override
    public void log(String str) {

    }
}
