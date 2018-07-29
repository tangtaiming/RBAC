<html>
<body>
<h2>角色管理页面</h2>
<a href="/admin/createRole">添加角色</a>
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
                <a href="/admin/editRole?id=${role["id"]}">编辑</a>
                <a href="/admin/siteAccess?id=${role["id"]}">设置权限</a>
            </td>
        </tr>
        </#list>
    </#if>
</table>
</body>
</html>
