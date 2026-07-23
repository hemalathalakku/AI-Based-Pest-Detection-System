<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1">
<title>Farmer Login</title><link rel="stylesheet" href="css/style.css"></head>
<body>
  <nav class="nav"><div class="brand">Pest<span>Guard</span> AI</div>
    <div class="nav-links"><a href="index.html">Home</a><a href="register.jsp">Register</a></div>
  </nav>
  <div class="formbox">
    <h2>Farmer Login</h2>
    <% if ("1".equals(request.getParameter("error"))) { %>
       <div class="msg err">Invalid email or password.</div>
    <% } %>
    <% if ("1".equals(request.getParameter("registered"))) { %>
       <div class="msg ok">Registration successful. Please log in.</div>
    <% } %>
    <form action="login" method="post">
      <label>Email</label><input type="email" name="email" required>
      <label>Password</label><input type="password" name="password" required>
      <button class="btn" type="submit">Login</button>
    </form>
    <p class="center-link">New here? <a href="register.jsp">Create an account</a></p>
    <p class="center-link" style="color:#5c6b5c">Demo: ravi@farm.com / farmer123</p>
  </div>
</body>
</html>
