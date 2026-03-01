 <%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <!DOCTYPE html>
 <html>
 <head><meta charset="UTF-8"><title>錯誤</title></head>
 <body>
     <h3>發生錯誤</h3>
     <p>${errorMessage}</p>
     <a href="${pageContext.request.contextPath}/announcement/list">返回列表</a>
 </body>
 </html>