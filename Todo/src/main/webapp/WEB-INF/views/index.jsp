<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Login</title>
  <link rel="stylesheet" href="styles.css"> <!-- CSSファイルをリンク -->
</head>
<body>
  <h1>Login</h1>
  <form method="POST" action="login" class="login-form"> <!-- フォームにクラスを追加 -->
    <label for="username">Username:</label>
    <input type="text" id="username" name="username"><br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password"><br>
    <input type="submit" value="Login">
  </form>
  <p>or <a href="createuser">Create User</a></p>
</body>
</html>
