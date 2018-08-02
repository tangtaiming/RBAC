<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>RBAC - 后台管理系统</title>
    <link rel="stylesheet" href="/design/static/plugins/layui/css/layui.css">
    <link rel="stylesheet" href="/design/static/css/management.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
<#include "core/layui-header.ftl" />
<#include "core/layui-side.ftl" />
    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <div class="layui-row">
				<span class="layui-breadcrumb">
				  <a href="">首页</a>
				  <a><cite>权限管理</cite></a>
				</span>
            </div>
            <hr>
            <div class="layui-row">
                <div class="layui-btn-group">
                    <a class="layui-btn layui-btn-sm" href="/admin/createAccess">
                        <i class="layui-icon">&#xe654;</i>新增权限
                    </a>
                    <a class="layui-btn layui-btn-sm">
                        <i class="layui-icon">&#xe615;</i>搜索
                    </a>
                    <a class="layui-btn layui-btn-sm">
                        <i class="layui-icon">&#xe735;</i>重置
                    </a>
                </div>
            </div>
            <div class="layui-form layui-border-box layui-table-view">
                <div class="layui-table-body layui-table-main">
                    <table cellspacing="0" cellpadding="0" border="0" class="layui-table" lay-size="sm">
                        <thead>
                        <tr>
                            <th class="layui-col-bottom">
                                <div class="layui-table-cell"><b>名称</b></div></th>
                            <th class="layui-col-bottom">
                                <div class="layui-table-cell"><b>URLS</b><div></th>
                            <th class="layui-col-bottom" style="width: 6%;">
                                <div class="layui-table-cell"><b>操作</b></div></th>
                        </tr>
                        <tr id="model-search">
                            <th class="layui-col-top">
                                <div class="layui-row layui-date-row">
                                    <input type="text" name="name" class="layui-input layui-filter">
                                </div>
                            </th>
                            <th class="layui-col-top">
                                <div class="layui-row layui-date-row">
                                    <input type="text" name="text" class="layui-input layui-filter">
                                </div>
                            </th>
                            <th class="layui-col-top"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <#if accessList??>
                            <#list accessList as access>
                            <tr>
                                <td class="layui-table-td">
                                    <div class="layui-table-cell">${access["title"]}</div></td>
                                <td class="layui-table-td">
                                    <div class="layui-table-cell">${access["urls"]}</div></td>
                                <td class="layui-table-td">
                                    <a class="layui-btn layui-btn-xs" href="/admin/editAccess?id=${access["id"]}">
                                        <i class="layui-icon">&#xe642;</i>编辑
                                    </a></td>
                            </tr>
                            </#list>
                        </#if>
                        </tbody>
                    </table>
                </div><!-- layui-table-body layui-table-main -->
                <div class="layui-table-tool">
                    <div class="layui-inline layui-table-page" id="layui-table-page1">
                        <div class="layui-box layui-laypage layui-laypage-default" id="layui-laypage-1">
                            <a href="javascript:;" class="layui-laypage-prev layui-disabled" data-page="0"><i class="layui-icon">&#xe603;</i></a>
                            <span class="layui-laypage-curr">
								<em class="layui-laypage-em"></em>
								<em>1</em>
							</span>
                            <a href="javascript:;" data-page="2">2</a>
                            <a href="javascript:;" data-page="3">3</a>
                            <a href="javascript:;" class="layui-laypage-next" data-page="2"><i class="layui-icon">&#xe602;</i></a>
                            <span class="layui-laypage-skip">到第<input type="text" min="1" value="1" class="layui-input">页
								<button type="button" class="layui-laypage-btn">确定</button>
							</span>
                            <span class="layui-laypage-count">共 1000 条</span>
                            <span class="layui-laypage-limits">
								<select lay-ignore="">
									<option value="10">10 条/页</option>
									<option value="20">20 条/页</option>
									<option value="30" selected="">30 条/页</option>
									<option value="40">40 条/页</option>
									<option value="50">50 条/页</option>
									<option value="60">60 条/页</option>
									<option value="70">70 条/页</option>
									<option value="80">80 条/页</option>
									<option value="90">90 条/页</option>
								</select>
							</span>
                        </div>
                    </div>
                </div><!--  layui-table-tool -->
            </div><!-- layui-form layui-border-box layui-table-view -->
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