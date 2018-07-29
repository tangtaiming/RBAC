<html>
<body>
<h2>权限管理页面</h2>
<a href="/admin/createAccess">权限角色</a>
<table>
    <tr>
        <th>名称</th>
        <th>URLS</th>
        <th>操作</th>
    </tr>
    <#if accessList??>
        <#list accessList as access>
        <tr>
            <td>${access["title"]}</td>
            <td>${access["urls"]}</td>
            <td>
                <a href="/admin/editAccess?id=${access["id"]}">编辑</a>
            </td>
        </tr>
        </#list>
    </#if>
</table>
</body>
</html>
