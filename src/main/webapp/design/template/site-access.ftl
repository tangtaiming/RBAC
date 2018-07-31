<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>RBAC - 后台管理系统 - 设置${role['name']}权限</title>
    <link rel="stylesheet" href="/design/static/plugins/layui/css/layui.css">
    <link rel="stylesheet" href="/design/static/css/admin.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
<#include "core/layui-header.ftl" />
<#include "core/layui-side.ftl" />
    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div class="layadmin-tabsbody-item layui-show">
            <div class="layui-card layadmin-header">
				<span class="layui-breadcrumb">
				  <a href="/rbac/index">首页</a>
                  <a href="/admin/roleManagement">角色管理</a>
				  <a><cite>设置${role['name']}权限</cite></a>
				</span>
            </div>
            <div class="layui-fluid">
                <div class="layui-col-md8">
                    <form id="site-access-form" class="layui-form" action="">
                        <div class="layui-form-item">
                            <label class="layui-form-label">权限:</label>
                            <div class="layui-input-block">
                                <div class="layui-col-md7">
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
                                <div class="layui-col-md5">
                                    <div style="margin-left:10px;" class="layui-form-mid layui-word-aux"></div>
                                </div>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <#assign roleId = role['id']/>
                                <#if roleId??>
                                    <input type="hidden" name="siteAccessRqDto.roleId" value="${role['id']}"/>
                                </#if>
                                <input type="button" id="site-access-save" class="layui-btn" value="立即提交"/>
                                <input type="reset" class="layui-btn layui-btn-primary" value="重置"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
<#include "core/layui-footer.ftl"/>
</div>
<script src="/design/static/plugins/layui/layui.js"></script>
<script src="/design/static/js/jQuery-2.2.0.min.js" type="text/javascript"></script>
<script src="/design/static/js/save-form.js"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;
    });
</script>
</body>
</html>