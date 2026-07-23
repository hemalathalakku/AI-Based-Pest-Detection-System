<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1">
<title>Admin Dashboard</title><link rel="stylesheet" href="${ctx}/css/style.css"></head>
<body>
  <nav class="nav"><div class="brand">Pest<span>Guard</span> AI &middot; Admin</div>
    <div class="nav-links">
      <a href="${ctx}/admin/dashboard.jsp">Dashboard</a>
      <a href="${ctx}/admin/farmers">Farmers</a>
      <a href="${ctx}/admin/pests">Pests</a>
      <a href="${ctx}/admin/reports">Reports</a>
      <a href="${ctx}/logout">Logout</a>
    </div>
  </nav>
  <div class="container">
    <h2 style="color:#2E7D32">Welcome, ${sessionScope.adminUser}</h2>
    <div class="grid">
      <a class="card" href="${ctx}/admin/farmers"><div class="icon">&#128101;</div>
        <h3>Manage Farmers</h3><p>View and remove registered farmers.</p></a>
      <a class="card" href="${ctx}/admin/pests"><div class="icon">&#128027;</div>
        <h3>Manage Pests</h3><p>Add or delete pest knowledge entries.</p></a>
      <a class="card" href="${ctx}/admin/reports"><div class="icon">&#128200;</div>
        <h3>Reports</h3><p>Detection stats and uploaded images.</p></a>
    </div>
  </div>
  <footer>&copy; 2027 PestGuard AI</footer>
</body>
</html>
