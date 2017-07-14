<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!Doctype html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <h1>你好</h1>
    <c:if test="${not empty message}">
        <h3 style="color: chartreuse">${message}</h3>
    </c:if>
    <c:if test="${not empty name}">
        <h2>${name}</h2>
    </c:if>
        ${user.name}
</body>
        <script>


        </script>
</html>