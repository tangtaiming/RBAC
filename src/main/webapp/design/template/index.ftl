<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>RBAC - 后台管理系统</title>
    <link rel="stylesheet" href="/design/static/plugins/layui/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <#include "core/layui-header.ftl" />
    <#include "core/layui-side.ftl" />

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">RABC 权限管理系统欢迎您!</div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © RBAC
    </div>
</div>
<script src="/design/static/plugins/layui/layui.js"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;
    });
</script>
</body>
</html>