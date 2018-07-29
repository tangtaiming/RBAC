<html>
<head>
    <title>创建用户</title>
    <script src="/design/static/js/jQuery-2.2.0.min.js" type="text/javascript"></script>
    <script src="/design/static/js/save-form.js"></script>
</head>
<body>
<h2>创建用户</h2>
<form id="form-user" action="/admin/saveUser" method="post">
    <div>名称: <input type="text" name="userRs.name"/></div>
    <div>邮箱: <input type="text" name="userRs.email"/></div>
    <div>角色:
        <#list user['roles'] as role>
            <input type="checkbox" name="userRs.roles" value="${role['id']!''}"/>${role['name']!''}&nbsp;&nbsp;
        </#list>
    </div>
    <div><input id="save-user" type="button" value="提交"/></div>
</form>
</body>
</html>
