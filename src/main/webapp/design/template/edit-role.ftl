<html>
<head>
    <title>编辑角色</title>
    <script src="/design/static/js/jQuery-2.2.0.min.js" type="text/javascript"></script>
    <script src="/design/static/js/save-form.js"></script>
</head>
<body>
<h2>编辑角色</h2>
<form id="role-form" action="/admin/saveRole" method="post">
    <#assign roleId = role['id']/>
    <#if roleId??>
        <input type="hidden" name="role.id" value="${roleId}"/>
    </#if>
    <div>角色名称: <input type="text" name="role.name" value="${role['name']}"/></div>
    <div><input id="save-role" type="button" value="提交"/></div>
</form>
</body>
</html>
