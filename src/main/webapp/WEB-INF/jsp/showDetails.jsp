<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>

<meta charset="UTF-8">
<title>Coss Student Details</title>
<style>
td, th {
  font-family: arial, sans-serif;
}
</style>
</head>

<body>

     <table style="vertical-align: left; margin-left:20%;">
       <tr>
                  <th>Name</th>
                  <th>Mobile</th>
                  <th>EmailId</th>
                  <th>Coss Student Id</th>
                  <th></th>
                  <th></th>
              </tr>
     <c:forEach items="${cossStudentList}" var="student">
            <tr>
                <td>${student.cossName}</td>
                <td>${student.cossMobile}</td>
                <td>${student.cossEmail}</td>
                <td align="center">coss${student.id}</td>


                <td align="right">
                   <a href="/edit?id=${student.id}" >Edit</a>
                </td>
                <td align="right">
                   <form action="/delete?id=${student.id}" method="post">
                     <input type="submit" value="Delete" style="background:none;border:0px;cursor: pointer;"/>
                   </form>
                </td>
            </tr>
            </c:forEach>
<tr align="center" colspan="6">
  <td>
                   <a href="/login?" >Login</a>
                </td>
</tr>


</table>
</body>

</html>