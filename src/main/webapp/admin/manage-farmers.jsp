<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1">
<title>Manage Farmers</title><link rel="stylesheet" href="${ctx}/css/style.css"></head>
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
    <h2 style="color:#2E7D32">Registered Farmers</h2>
    <table>
      <tr><th>ID</th><th>Name</th><th>Email</th><th>Phone</th><th>Address</th><th>Action</th></tr>
      <c:forEach var="f" items="${farmers}">
        <tr>
          <td>${f.id}</td><td>${f.name}</td><td>${f.email}</td>
          <td>${f.phone}</td><td>${f.address}</td>
          <td>
            <form action="${ctx}/admin/farmers" method="post"
                  onsubmit="return confirm('Delete this farmer?')">
              <input type="hidden" name="id" value="${f.id}">
              <button class="btn danger" type="submit">Delete</button>
            </form>
          </td>
        </tr>
      </c:forEach>
    </table>
  </div>
  <footer>&copy; 2027 PestGuard AI</footer>
</body>
</html>
