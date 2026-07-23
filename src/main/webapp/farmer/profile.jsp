<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1">
<title>My Profile</title><link rel="stylesheet" href="${ctx}/css/style.css"></head>
<body>
  <nav class="nav"><div class="brand">Pest<span>Guard</span> AI</div>
    <div class="nav-links">
      <a href="${ctx}/farmer/dashboard.jsp">Dashboard</a>
      <a href="${ctx}/farmer/upload.jsp">Upload</a>
      <a href="${ctx}/farmer/history">History</a>
      <a href="${ctx}/farmer/profile">Profile</a>
      <a href="${ctx}/logout">Logout</a>
    </div>
  </nav>
  <div class="formbox">
    <h2>My Profile</h2>
    <c:if test="${param.updated == '1'}"><div class="msg ok">Profile updated.</div></c:if>
    <form action="${ctx}/farmer/profile" method="post">
      <label>Name</label><input type="text" name="name" value="${farmer.name}" required>
      <label>Email (cannot change)</label><input type="email" value="${farmer.email}" disabled>
      <label>Phone</label><input type="text" name="phone" value="${farmer.phone}">
      <label>Address</label><input type="text" name="address" value="${farmer.address}">
      <button class="btn" type="submit">Save Changes</button>
    </form>
  </div>
  <footer>&copy; 2027 PestGuard AI</footer>
</body>
</html>
