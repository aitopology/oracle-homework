<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.entity.User" %>
<%@ page import="java.util.List" %>

<html>
<head>
    <title>用户管理</title>
    <style>
        .container { width: 800px; margin: 20px auto; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        .pagination { margin-top: 20px; }
        .pagination a { padding: 8px; margin: 0 4px; text-decoration: none; }

    </style>
</head>
<body>
<div class="container">
    <h2>用户列表</h2>
    <a href="<%= request.getContextPath() %>/add.jsp">添加用户</a>
    
    <table>
        <tr>
            <th>用户名</th>
            <th>密码</th>
            <th>操作</th>
        </tr>
        <% 
List<User> users = (List<User>)request.getAttribute("users");// 从request中获取用户列表
if(users != null && !users.isEmpty()) { 
for(User user : users) { %>
            <tr>
                <td><%= user.getUsername() %></td>
                <td><%= user.getPassword() %></td>
                <td>
                    <a href="<%= request.getContextPath() %>/delete?username=<%= user.getUsername() %>" 
                       onclick="return confirm('确定要删除吗？')">删除</a>
                    <a href="<%= request.getContextPath() %>/edit.jsp?username=<%= user.getUsername() %>&password=<%= user.getPassword() %>">编辑</a>
                </td>
            </tr>
        <% } %>
<% } else { %>
    <tr><td colspan="3">暂无用户数据</td></tr>
<% } %>
    </table>

    <div class="pagination">
        <%
Integer totalPagesObj = (Integer)request.getAttribute("totalPages");
int totalPages = totalPagesObj != null ? totalPagesObj : 0;
for(int i=1; i<=totalPages; i++) { %>
            <a href="<%= request.getContextPath() %>/list?page=<%= i %>"><%= i %></a>
        <% } %>
    </div>
</div>
</body>
</html>