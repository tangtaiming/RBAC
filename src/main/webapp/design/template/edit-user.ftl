<html>
<head>
    <title>编辑用户</title>
    <script src="/design/static/js/jQuery-2.2.0.min.js" type="text/javascript"></script>
    <script src="/design/static/js/save-form.js"></script>
</head>
<body>
<h2>编辑用户</h2>
<form id="form-user" action="/role/saveUser" method="post">
    <#assign userId = user['id']/>
    <#if userId??>
        <input type="hidden" name="userRs.id" value="${userId}" />
    </#if>
    <div>名称: <input type="text" name="userRs.name" value="${user['name']!''}"/></div>
    <div>邮箱: <input type="text" name="userRs.email" value="${user['email']!''}"/></div>
    <div>角色:
        <#list user['roles'] as role>
            <input type="checkbox" name="userRs.roles" value="${role['id']!''}"/>${role['name']!''}&nbsp;&nbsp;
        </#list>
    </div>
    <div><input id="save-user" type="button" value="提交"/></div>
</form>
</body>
</html>
