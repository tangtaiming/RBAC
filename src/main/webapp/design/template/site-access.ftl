<html>
<head>
    <title>设置${role['name']}权限</title>
    <script src="/design/static/js/jQuery-2.2.0.min.js" type="text/javascript"></script>
    <script src="/design/static/js/save-form.js"></script>
</head>
<body>
<h2>设置${role['name']}权限</h2>
<form id="site-access-form" action="/admin/saveRole" method="post">
    <div>
        权限:
        <#list accessList as access>
            <#assign chonsen = ''/>
            <#list roleAccessList as chonsenAccess>
                <#if access['id']==chonsenAccess['accessId']>
                    <#assign chonsen='checked="checked"' />
                    <#break>
                </#if>
            </#list>
            <input type="checkbox" ${chonsen} name="siteAccessRqDto.accessId" value="${access['id']}" />${access['title']}
        </#list>
    </div>
    <div>
        <input type="hidden" name="siteAccessRqDto.roleId" value="${role['id']}"/>
        <input id="site-access-save" type="button" value="提交"/>
    </div>
</form>
</body>
</html>
