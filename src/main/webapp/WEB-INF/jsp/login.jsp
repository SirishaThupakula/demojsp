<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>

<meta charset="UTF-8">
<title>Coss Student Application</title>
<style>
td, th {
  font-family: arial, sans-serif;
}
</style>
</head>
<script type="text/javascript">
        function validate() {
        if (document.forms["cossStudent"]["cname"].value == "") {
              alert("Please enter name");
              document.forms["cossStudent"]["cname"].focus();
              return false;
         }
         if (document.forms["cossStudent"]["cEmail"].value == "") {
              alert("Please enter EmailId");
              document.forms["cossStudent"]["cEmail"].focus();
              return false;
         }
         if (document.forms["cossStudent"]["cMobile"].value == "") {
              alert("Please enter user mobile");
              document.forms["cossStudent"]["cMobile"].focus();
              return false;
         }
         }
</script>

<body>
     <font color="red">${errorMessage}</font>
     <form:form method="POST" modelAttribute="cossStudentModel" action="/home" name="cossStudent">
     <table style="vertical-align: center; margin-left:20%;">
     <tr>
                 <td><form:hidden path="id"/></td>
     </tr>
     <tr>
                 <td>Coss Name :</td>
                 <td><form:input path="cossName" id="cname" /></td>
     </tr>
     <tr>
                 <td>Coss Email :</td>
                 <td><form:input path="cossEmail" id="cEmail"/></td>
     </tr>
     <tr>
                 <td>Coss Mobile :</td>
                 <td><form:input path="cossMobile" id="cMobile"/></td>
      </tr>

        <tr>
                 <td colspan="2"><input type="submit" value="<c:out value="${cossStudentModel.id != null ? 'Update' : 'Register' }" />"
                     onclick="return validate();">&nbsp;&nbsp;
                       <c:if test="${cossStudentModel.id ne null}"><b>|</b>&nbsp;<a href="/cosslist">Coss Student Details</a></c:if>&nbsp;
                    <c:if test="${cossStudentModel.id ne null}"><b>|</b>&nbsp;<a href="/login">Register</a></c:if>
                 </td>
        </tr>
</table>
    </form:form>
</body>

</html>