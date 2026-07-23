<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1">
<title>Detection History</title><link rel="stylesheet" href="${ctx}/css/style.css"></head>
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
    <h2 style="color:#2E7D32">My Detection History</h2>
    <c:choose>
      <c:when test="${empty list}">
        <p style="margin-top:16px">No detections yet.
           <a href="${ctx}/farmer/upload.jsp" style="color:#2E7D32;font-weight:600">Scan a leaf</a>.</p>
      </c:when>
      <c:otherwise>
        <table>
          <tr><th>Image</th><th>Pest</th><th>Confidence</th><th>Date</th></tr>
          <c:forEach var="d" items="${list}">
            <tr>
              <td><c:if test="${not empty d.imagePath}">
                    <img class="thumb" src="${ctx}/${d.imagePath}"></c:if></td>
              <td>${d.pestName}</td>
              <td>${d.confidence}%</td>
              <td>${d.detectedAt}</td>
            </tr>
          </c:forEach>
        </table>
      </c:otherwise>
    </c:choose>
  </div>
  <footer>&copy; 2027 PestGuard AI</footer>
</body>
</html>
