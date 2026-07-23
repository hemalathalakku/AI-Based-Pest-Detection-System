<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1">
<title>Upload Image</title><link rel="stylesheet" href="${ctx}/css/style.css">
  <!-- TensorFlow.js + Teachable Machine image library -->
  <script src="https://cdn.jsdelivr.net/npm/@tensorflow/tfjs@1.3.1/dist/tf.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/@teachablemachine/image@0.8/dist/teachablemachine-image.min.js"></script>
</head>
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

  <div class="upload-area">
    <h2 style="color:#2E7D32">Upload a Crop Leaf Image</h2>

    <!-- The file + hidden prediction fields are posted to the servlet -->
    <form action="${ctx}/farmer/detect" method="post" enctype="multipart/form-data">
      <input type="file" name="image" accept="image/*" required
             onchange="onFileChosen(this)">
      <img id="preview" alt="preview">

      <input type="hidden" id="pestName"   name="pestName">
      <input type="hidden" id="confidence" name="confidence">

      <div style="margin-top:14px">
        <button type="button" class="btn" onclick="detectPest()">Detect Pest</button>
        <button type="submit" class="btn light" id="saveBtn" disabled>Save &amp; View Details</button>
      </div>
      <p id="status" style="color:#c62828;margin-top:10px"></p>

      <div class="pred-box" id="predBox">
        <div>Detected pest:</div>
        <div class="pest" id="predLabel"></div>
        <div>Confidence: <b id="predConf"></b></div>
      </div>
    </form>
  </div>

  <div class="note">
    <b>Setup note:</b> Open <code>js/teachable.js</code> and paste your own
    Teachable Machine model URL. Train one class per pest, naming each class
    exactly like a <code>pest_name</code> in the database.
  </div>

  <script src="${ctx}/js/teachable.js"></script>
</body>
</html>
