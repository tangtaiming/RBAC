<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>RBAC - 后台管理系统 - 编辑用户</title>
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
				  <a href="">首页</a>
                  <a href="">用户管理</a>
				  <a><cite>编辑用户</cite></a>
				</span>
            </div>
            <div class="layui-fluid">
                <div class="layui-col-md8">
                    <form id="form-user" class="layui-form" action="">
                        <div class="layui-form-item">
                            <label class="layui-form-label">名称:</label>
                            <div class="layui-input-block">
                                <div class="layui-col-md7">
                                    <input type="text" name="userRs.name" required value="${editUserRsDto['user']['name']!''}"   lay-verify="required" placeholder="请输入名称" autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-col-md5">
                                    <div style="margin-left:10px;" class="layui-form-mid layui-word-aux"></div>
                                </div>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">邮箱:</label>
                            <div class="layui-input-block">
                                <div class="layui-col-md7">
                                    <input type="text" name="userRs.email" required value="${editUserRsDto['user']['email']!''}" lay-verify="required" placeholder="请输入邮箱" autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-col-md5">
                                    <div style="margin-left:10px;" class="layui-form-mid layui-word-aux"></div>
                                </div>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">角色:</label>
                            <div class="layui-input-block">
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
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <#assign userId = editUserRsDto['user']['id']/>
                                <#if userId??>
                                    <input type="hidden" name="userRs.id" value="${userId}" />
                                </#if>
                                <input type="button" id="save-user" class="layui-btn" value="立即提交"/>
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