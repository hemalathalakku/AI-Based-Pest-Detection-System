<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1">
<title>Admin Login</title><link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></head>
<body>
  <nav class="nav"><div class="brand">Pest<span>Guard</span> AI &middot; Admin</div>
    <div class="nav-links"><a href="${pageContext.request.contextPath}/index.html">Home</a></div>
  </nav>
  <div class="formbox">
    <h2>Admin Login</h2>
    <% if ("1".equals(request.getParameter("error"))) { %>
       <div class="msg err">Invalid admin credentials.</div><% } %>
    <form action="${pageContext.request.contextPath}/adminlogin" method="post">
      <label>Username</label><input type="text" name="username" required>
      <label>Password</label><input type="password" name="password" required>
      <button class="btn" type="submit">Login</button>
    </form>
    <p class="center-link" style="color:#5c6b5c">Demo: admin / admin123</p>
  </div>
</body>
</html>
