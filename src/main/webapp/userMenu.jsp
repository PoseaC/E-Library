<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="bootstrap.css">
    <link rel="stylesheet" href="style.css">
    <title>E-Library</title>
</head>
<body>
    <%@include file="navbar.jsp"%>
    <div class="text-center" style="margin-top: 25px">
        <a href="userBooks.jsp" class="btn btn-outline-dark">My Books</a>
    </div>
    <div class="text-center" style="margin-top: 25px">
        <a href="${pageContext.request.contextPath}/recommend" class="btn btn-outline-dark">Recommendations</a>
    </div>
</body>
</html>