<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1">
<title>Detection Result</title><link rel="stylesheet" href="${ctx}/css/style.css"></head>
<body>
  <nav class="nav"><div class="brand">Pest<span>Guard</span> AI</div>
    <div class="nav-links">
      <a href="${ctx}/farmer/dashboard.jsp">Dashboard</a>
      <a href="${ctx}/farmer/upload.jsp">Upload</a>
      <a href="${ctx}/farmer/history">History</a>
      <a href="${ctx}/logout">Logout</a>
    </div>
  </nav>

  <div class="result">
    <div class="head">
      <div class="pest">${detection.pestName}</div>
      <span class="conf">Confidence: ${detection.confidence}%</span>
    </div>
    <div class="body">
      <div>
        <c:if test="${not empty detection.imagePath}">
          <img src="${ctx}/${detection.imagePath}" alt="uploaded leaf">
        </c:if>
      </div>
      <div>
        <c:choose>
          <c:when test="${pest != null}">
            <div class="info-row"><b>Affected Crop</b>${pest.cropName}</div>
            <div class="info-row"><b>Symptoms</b>${pest.symptoms}</div>
            <div class="info-row"><b>Treatment</b>${pest.treatment}</div>
            <div class="info-row"><b>Prevention</b>${pest.prevention}</div>
          </c:when>
          <c:otherwise>
            <p>No detailed information found for this pest in the knowledge base.
               An admin can add it under <b>Manage Pests</b>.</p>
          </c:otherwise>
        </c:choose>
        <a class="btn" href="${ctx}/farmer/upload.jsp" style="margin-top:14px">Detect Another</a>
      </div>
    </div>
  </div>
  <footer>&copy; 2027 PestGuard AI</footer>
</body>
</html>
