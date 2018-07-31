<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>RBAC - 后台管理系统</title>
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
				  <a><cite>角色管理</cite></a>
				</span>
            </div>
            <div class="layui-fluid">
                <div class="layui-card">
                    <div class="layui-card-header layuiadmin-card-header-auto">
                        <a href="/admin/createRole" class="layui-btn layuiadmin-btn-tags">添加角色</a>
                    </div>
                    <div class="layui-card-body">
                        <table class="layui-table" lay-size="sm">
                            <thead>
                            <tr>
                                <th>角色名称</th>
                                <th style="width: 12%;">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#if roleList??>
                                <#list roleList as role>
                                <tr>
                                    <td>${role["name"]}</td>
                                    <td>
                                        <div class="layui-table-cell laytable-cell-37-3">
                                            <a href="/admin/editRole?id=${role["id"]}" class="layui-btn layui-btn-normal layui-btn-xs">
                                                <i class="layui-icon layui-icon-edit"></i>
                                                编辑
                                            </a>
                                            <a href="/admin/siteAccess?id=${role["id"]}" class="layui-btn layui-btn-normal layui-btn-xs">
                                                <i class="layui-icon layui-icon-edit"></i>
                                                设置权限
                                            </a>
                                        </div>
                                    </td>
                                </tr>
                                </#list>
                            </#if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
<#include "core/layui-footer.ftl"/>
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