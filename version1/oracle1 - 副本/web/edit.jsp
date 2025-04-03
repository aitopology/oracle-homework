<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑用户</title>
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
            background: #2196F3;
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
    <h2>编辑用户</h2>
    <form action="<%= request.getContextPath() %>/update" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="originalUsername" value="<%= request.getParameter("username") %>">
        
        <div class="form-group">
            <label>用户名:</label>
            <input type="text" name="username" value="<%= request.getParameter("username") %>" required>
        </div>
        
        <div class="form-group">
            <label>密码:</label>
            <input type="password" name="password" value="<%= request.getParameter("password") %>" required>
        </div>
        
        <input type="submit" class="submit-btn" value="更新">
        <a href="<%= request.getContextPath() %>/list" style="margin-left:20px">返回列表</a>
    </form>
</div>
</body>
</html>