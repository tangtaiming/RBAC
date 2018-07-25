<html>
<head>
    <title>用户管理页面</title>
</head>
<body>
<h2>用户管理页面</h2>
<a href="/admin/createUser" target="_blank">添加用户</a>
<table>
    <tr>
        <th>名称</th>
        <th>邮箱</th>
        <th>操作</th>
    </tr>
    <#if userList??>
        <#list userList as user>
        <tr>
            <td>${user["name"]}</td>
            <td>${user["email"]}</td>
            <td>
                <a href="/admin/editUser?id=${user["id"]}">编辑</a>
                <a href="#">设置权限</a>
            </td>
        </tr>
        </#list>
    </#if>
</table>
</body>
</html>
