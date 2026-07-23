<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1">
<title>Manage Pests</title><link rel="stylesheet" href="${ctx}/css/style.css"></head>
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
    <h2 style="color:#2E7D32">Add Pest Information</h2>
    <div class="formbox wide" style="margin-top:12px">
      <form action="${ctx}/admin/pests" method="post">
        <input type="hidden" name="action" value="add">
        <label>Pest Name (must match a Teachable Machine class)</label>
        <input type="text" name="pestName" required>
        <label>Crop Name</label><input type="text" name="cropName">
        <label>Symptoms</label><textarea name="symptoms" rows="2"></textarea>
        <label>Causes</label><textarea name="causes" rows="2"></textarea>
        <label>Treatment</label><textarea name="treatment" rows="2"></textarea>
        <label>Prevention</label><textarea name="prevention" rows="2"></textarea>
        <button class="btn" type="submit">Add Pest</button>
      </form>
    </div>

    <h2 style="color:#2E7D32;margin-top:26px">Pest Knowledge Base</h2>
    <table>
      <tr><th>ID</th><th>Pest</th><th>Crop</th><th>Treatment</th><th>Action</th></tr>
      <c:forEach var="p" items="${pests}">
        <tr>
          <td>${p.id}</td><td>${p.pestName}</td><td>${p.cropName}</td><td>${p.treatment}</td>
          <td>
            <form action="${ctx}/admin/pests" method="post"
                  onsubmit="return confirm('Delete this pest?')">
              <input type="hidden" name="action" value="delete">
              <input type="hidden" name="id" value="${p.id}">
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
