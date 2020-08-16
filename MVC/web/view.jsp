<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html>
<script src="https://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
<link href="https://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="https://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
$(function(){
    $("a").addClass("btn btn-default btn-xs");

});

<%
class Book {
    public int id;
    public String name;
    public Book(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
List<Book> strs = new ArrayList<>();
strs.add(new Book(2, "简爱"));
strs.add(new Book(3, "三体"));
strs.add(new Book(4, "挪威的森林"));
request.setAttribute("strs", strs);
%>

</script>
<table style="width:500px; margin:44px auto" class="table table-striped table-bordered table-hover  table-condensed" align='center' border='1' cellspacing='0'>
    <tr>
        <td>id</td>
        <td>name</td>
        <td>price</td>
        <td>edit</td>
        <td>delete</td>
    </tr>
    <tr>
        <td>1</td>
        <td>战争与和平</td>
        <td>45</td>
        <td><a href="#">编辑</a></td>
        <td><a href="#">删除</a></td>
    </tr>
    <c:forEach items="${books}" var="book" varStatus="st">
        <tr>
            <td>${book}</td>
            <td>${book}</td>
            <td>${book}</td>
            <td><a href="#">编辑</a></td>
            <td><a href="#">删除</a></td>
        </tr>
    </c:forEach>
    <c:forEach items="${strs}" var="str" varStatus="st">
        <tr>
            <td>2</td>
            <td>${str}</td>
            <td>45</td>
            <td><a href="#">编辑</a></td>
            <td><a href="#">删除</a></td>
        </tr>
    </c:forEach>

</table>
        <nav>
          <ul class="pager">

            <li><a href="?start=0">首  页</a></li>
            <li><a href="?start=${pre}">上一页</a></li>
            <li><a href="?start=${next}">下一页</a></li>
            <li><a href="?start=${last}">末  页</a></li>
          </ul>
        </nav>