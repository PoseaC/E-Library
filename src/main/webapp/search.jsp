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
        <sql:setDataSource
                var="database"
                driver="com.mysql.cj.jdbc.Driver"
                url="jdbc:mysql://localhost:3306/library"
                user="root" password="root"
        />
        <sql:query var="books" dataSource="${database}">
            SELECT * FROM books WHERE title LIKE '%${param.keyword}%'
                                OR author LIKE '%${param.keyword}%'
                                OR genre LIKE '%${param.keyword}%'
                                OR description LIKE '%${param.keyword}%';
        </sql:query>
        <%@include file="navbar.jsp"%>
        <table class="table table-striped text-center">
            <thead>
            <tr>
                <th>Title</th>
                <th>Description</th>
                <th>Author</th>
                <th>Pages</th>
                <th>Genre</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="book" items="${books.rows}">
                    <tr>
                        <td><c:out value="${book.title}"/></td>
                        <td><c:out value="${book.description}"/></td>
                        <td><c:out value="${book.author}"/></td>
                        <td><c:out value="${book.pages}"/></td>
                        <td><c:out value="${book.genre}"/></td>
                        <td>
                            <form method="POST" action="${pageContext.request.contextPath}/download" class="text-center">
                                <input type="submit" class="btn btn-dark" value="Download"/>
                                <input type="hidden" name="book_id" value="<c:out value="${book.id}"/>">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
