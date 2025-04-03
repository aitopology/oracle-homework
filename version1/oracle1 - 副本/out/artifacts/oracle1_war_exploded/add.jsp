<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加用户</title>
    <style>
        .container { width: 400px; margin: 50px auto; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; }
        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        .submit-btn {
            background: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>添加新用户</h2>
    <form action="${pageContext.request.contextPath}/add" method="post">
        
        
        <div class="form-group">
            <label>用户名:</label>
            <input type="text" name="username" required>
        </div>
        
        <div class="form-group">
            <label>密码:</label>
            <input type="password" name="password" required>
        </div>
        
        <input type="submit" class="submit-btn" value="提交">
        <a href="<%= request.getContextPath() %>/list.jsp" style="margin-left:20px">返回列表</a>
    </form>
</div>
</body>
</html>