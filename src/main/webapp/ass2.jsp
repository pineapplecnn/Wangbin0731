
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h4>i am myjsp.jsp</h4>
<form method="post" action="<%=request.getContextPath()%>/Ass2Servlet">
    name<input type="text" name="name"><br>
    class<input type="text" name="class"><br>
    ID<input type="text" name="id"><br>
    <input type="submit"  value="Send data to the server">
</body>
</html>
