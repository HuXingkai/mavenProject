<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>你好</title>
</head>
<%@ page isELIgnored="false" %>
<body>
${user.userName}，当前积分为${user.credits};
</body>
</html>