<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: liron_d
  Date: 06/05/2016
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>hello</title>
</head>
<body>
  <h3>Message : ${message}</h3>
  <h3>Username : ${username}</h3>

  <a href="<c:url value="/j_spring_security_logout">Logout</c:url>"/>
</body>
</html>