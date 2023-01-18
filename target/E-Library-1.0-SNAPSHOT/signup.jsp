<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="bootstrap.css">
    <link rel="stylesheet" href="style.css">
    <script src="https://code.jquery.com/jquery-3.5.0.js"></script>

    <title>E-Library</title>
</head>
<body>
    <h2><i>Sign up</i></h2>
    <form method="POST" action="${pageContext.request.contextPath}/signup" class="form text-center" onsubmit="return submitData()">
        <div class="form-group">
          <label for="username">Username:</label>
          <input type="text" class="form-control" id="username" name="username" required>
        </div>
        <div class="form-group">
          <label for="password">Password:</label>
          <input type="password" class="form-control" id="password" name="password" required>
        </div>
        <div class="form-group">
          <label for="password">Confirm password:</label>
          <input type="password" class="form-control" id="passwordConfirm" required>
        </div>
        <div class="form-group">
            <input type="radio" id="user" name="type" value="user" checked>
            <label for="user">User</label>
            <input type="radio" id="admin" name="type" value="admin">
            <label for="admin">Admin</label>
        </div>
        <input type="submit" class="btn btn-dark" value="Sign Up"/>
        <a class="btn btn-outline-dark" href="index.jsp">Log In</a>
      </form>
</body>
</html>