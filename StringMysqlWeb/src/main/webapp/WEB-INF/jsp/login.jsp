<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--引用核心标签-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>login</title>
    <script>
        function checkUser(){
            var result = document.getElementById("userid").value;
            var password = document.getElementById("userpassid").value;
            if(result == ""  ){
                alert("用户名不能为空");
                return false;
            }
            if(password == ""  ){
                alert("密码不能为空");
                return false;
            }
            // document.getElementById("check").onsubmit();
        }
    </script>
</head>
<body>
<%--<c:if test="${!empty error}">--%>
<%--<font color="red"><c:out value="%{error}" /></font>--%>
<%--</c:if>--%>
<form action="<c:url value="/loginCheck.html" />" method="post" onsubmit="return checkUser()">
    <table align="center" >
        <tr>
            <td>用户名：</td><td><input type="text" name="userName" id="userid" /></td>
        </tr>
        <tr>
            <td>密码：</td><td><input type="password" name="password" id="userpassid" /></td>
        <tr/>
        <tr>
            <td colspan="2",align="center">
                <input type="submit" value="登录" id="check" />
                <input type="reset" value="重置" />
            </td>
        </tr>
    </table>
</form>
</body>
</html>
