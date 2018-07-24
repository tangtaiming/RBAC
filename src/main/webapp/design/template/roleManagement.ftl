<html>
<body>
<h2>角色管理页面</h2>
<a href="/role/createRole" target="_blank">添加角色</a>
<table>
    <tr>
        <th>角色名称</th>
        <th>操作</th>
    </tr>
    <#if roleList??>
        <tr>
        <#list roleList as role>
            <td>${role["name"]}</td>
            <td>
                <a href="#">编辑</a>
                <a href="#">设置权限</a>
            </td>
        </#list>
        </tr>
    </#if>
</table>
</body>
</html>
