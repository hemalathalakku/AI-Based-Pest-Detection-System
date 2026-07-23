<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1">
<title>Reports</title><link rel="stylesheet" href="${ctx}/css/style.css"></head>
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
    <h2 style="color:#2E7D32">Reports &amp; Analytics</h2>
    <div class="grid">
      <div class="card"><h3>Total Farmers</h3><div class="num">${totalFarmers}</div></div>
      <div class="card"><h3>Total Detections</h3><div class="num">${totalDetections}</div></div>
      <div class="card"><h3>Today</h3><div class="num">${todayCount}</div></div>
      <div class="card"><h3>This Month</h3><div class="num">${monthCount}</div></div>
    </div>

    <h2 style="color:#2E7D32;margin-top:26px">Most Common Pests</h2>
    <table>
      <tr><th>Pest</th><th>Detections</th></tr>
      <c:forEach var="row" items="${mostCommon}">
        <tr><td>${row[0]}</td><td>${row[1]}</td></tr>
      </c:forEach>
    </table>

    <h2 style="color:#2E7D32;margin-top:26px">All Uploaded Detections</h2>
    <table>
      <tr><th>Image</th><th>Farmer</th><th>Pest</th><th>Confidence</th><th>Date</th></tr>
      <c:forEach var="d" items="${allDetections}">
        <tr>
          <td><c:if test="${not empty d.imagePath}">
                <img class="thumb" src="${ctx}/${d.imagePath}"></c:if></td>
          <td>${d.farmerName}</td><td>${d.pestName}</td>
          <td>${d.confidence}%</td><td>${d.detectedAt}</td>
        </tr>
      </c:forEach>
    </table>
  </div>
  <footer>&copy; 2027 PestGuard AI</footer>
</body>
</html>
