<html>
<head>
    <title>编辑权限</title>
    <script src="/design/static/js/jQuery-2.2.0.min.js" type="text/javascript"></script>
    <script src="/design/static/js/save-form.js"></script>
</head>
<body>
<h2>编辑权限</h2>
<form id="access-form" action="/admin/saveAccess" method="post">
    <input type="hidden" name="accessRsDto.id" value="${accessRsDto['id']!''}"/>
    <div>名称: <input type="text" name="accessRsDto.title" value="${accessRsDto['title']!''}"/></div>
    <div>
        URLS:<textarea name="accessRsDto.urls">${accessRsDto['urls']!''}</textarea>
    </div>
    <div><input id="access-save" type="button" value="提交"/></div>
</form>
</body>
</html>
