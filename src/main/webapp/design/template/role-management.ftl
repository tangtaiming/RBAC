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
        <#list roleList as role>
        <tr>
            <td>${role["name"]}</td>
            <td>
                <a href="/role/editRole?id=${role["id"]}">编辑</a>
                <a href="#">设置权限</a>
            </td>
        </tr>
        </#list>
    </#if>
</table>
</body>
</html>
