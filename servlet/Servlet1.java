/**********************************************
  > File Name		: Servlet1.java
  > Author			: lunar
  > Email			: lunar_ubuntu@qq.com
  > Created Time	: Thu 13 Aug 2020 05:26:50 PM CST
 **********************************************/

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

@WebServlet("/hello")
public class Servlet1 extends HttpServlet{
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            response.getWriter().println("<h1>Hello Servlet</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
