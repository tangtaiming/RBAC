<html>
<head>
    <title>编辑用户</title>
    <script src="/design/static/js/jQuery-2.2.0.min.js" type="text/javascript"></script>
    <script src="/design/static/js/save-form.js"></script>
</head>
<body>
<h2>编辑用户</h2>
<form id="form-user" action="/admin/saveUser" method="post">
    <#assign userId = editUserRsDto['user']['id']/>
    <#if userId??>
        <input type="hidden" name="userRs.id" value="${userId}" />
    </#if>
    <div>名称: <input type="text" name="userRs.name" value="${editUserRsDto['user']['name']!''}"/></div>
    <div>邮箱: <input type="text" name="userRs.email" value="${editUserRsDto['user']['email']!''}"/></div>
    <div>角色:
        <#list editUserRsDto['allRoleDto'] as role>
            <#assign chonsen='' />
            <#list editUserRsDto['chosenRole'] as chosenRole>
                <#if chosenRole==role['id']!''>
                    <#assign chonsen='checked="checked"' />
                    <#break>
                </#if>
            </#list>
            <input type="checkbox" ${chonsen} name="userRs.roles" value="${role['id']!''}"/>${role['name']!''}&nbsp;&nbsp;
        </#list>
    </div>
    <div><input id="save-user" type="button" value="提交"/></div>
</form>
</body>
</html>
