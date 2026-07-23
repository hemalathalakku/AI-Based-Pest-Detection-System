<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1">
<title>Farmer Dashboard</title><link rel="stylesheet" href="${ctx}/css/style.css"></head>
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
  <div class="container">
    <h2 style="color:#2E7D32">Welcome, ${sessionScope.farmerName} &#127793;</h2>
    <div class="grid">
      <a class="card" href="${ctx}/farmer/upload.jsp"><div class="icon">&#128247;</div>
        <h3>Upload &amp; Detect</h3><p>Scan a crop leaf for pests.</p></a>
      <a class="card" href="${ctx}/farmer/history"><div class="icon">&#128202;</div>
        <h3>My History</h3><p>Review your past detections.</p></a>
      <a class="card" href="${ctx}/farmer/profile"><div class="icon">&#128100;</div>
        <h3>My Profile</h3><p>Update your details.</p></a>
    </div>
  </div>
  <footer>&copy; 2027 PestGuard AI</footer>
</body>
</html>
