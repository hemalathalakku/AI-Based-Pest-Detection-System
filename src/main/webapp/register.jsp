<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1">
<title>Farmer Registration</title><link rel="stylesheet" href="css/style.css"></head>
<body>
  <nav class="nav"><div class="brand">Pest<span>Guard</span> AI</div>
    <div class="nav-links"><a href="index.html">Home</a><a href="login.jsp">Login</a></div>
  </nav>
  <div class="formbox">
    <h2>Create Account</h2>
    <% String err = request.getParameter("error");
       if ("exists".equals(err)) { %><div class="msg err">Email already registered.</div><% }
       else if ("invalid".equals(err)) { %><div class="msg err">Please fill all fields correctly (password min 4 chars).</div><% } %>
    <form action="register" method="post">
      <label>Full Name</label><input type="text" name="name" required>
      <label>Email</label><input type="email" name="email" required>
      <label>Password</label><input type="password" name="password" required>
      <label>Phone</label><input type="text" name="phone">
      <label>Address</label><input type="text" name="address">
      <button class="btn" type="submit">Register</button>
    </form>
    <p class="center-link">Already have an account? <a href="login.jsp">Login</a></p>
  </div>
</body>
</html>
