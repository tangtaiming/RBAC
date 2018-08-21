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
        <div class="layui-management-container">
            <div class="layui-card layadmin-header">
				<span class="layui-breadcrumb">
				  <a href="">首页</a>
				  <a><cite>用户管理</cite></a>
				</span>
            </div>
            <div class="layui-row">
                <div class="layui-btn-group layui-padding-px">
                    <a class="layui-btn layui-btn-sm" href="/admin/createUser">
                        <i class="layui-icon">&#xe654;</i>新增用户
                    </a>
                    <a class="layui-btn layui-btn-sm">
                        <i class="layui-icon">&#xe615;</i>搜索
                    </a>
                    <a class="layui-btn layui-btn-sm">
                        <i class="layui-icon">&#xe735;</i>重置
                    </a>
                </div>
            </div>
            <div class="layui-padding-px">
                <div class="layui-form layui-border-box layui-table-view">
                <div class="layui-table-body layui-table-main">
                    <table cellspacing="0" cellpadding="0" border="0" class="layui-table" lay-size="sm">
                        <thead>
                        <tr>
                            <th class="layui-col-bottom">
                                <div class="layui-table-cell"><b>名称</b></div></th>
                            <th class="layui-col-bottom">
                                <div class="layui-table-cell"><b>邮箱</b><div></th>
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
                        <#if userRsVo??>
                            <#list userRsVo as user>
                                <tr>
                                    <td class="layui-table-td">
                                        <div class="layui-table-cell">${user["name"]}</div></td>
                                    <td class="layui-table-td">
                                        <div class="layui-table-cell">${user["email"]}</div></td>
                                    <td class="layui-table-td">
                                        <a class="layui-btn layui-btn-xs" href="/admin/editUser?id=${user["id"]}">
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
                        <#-- 当前页 -->
                        <#assign pageNumber=page.pageNumber />
                        <#-- 下一页 -->
                        <#assign downRow=page.downRow />
                        <#-- 上一页 -->
                        <#assign upRow=page.upRow />
                        <div class="layui-box layui-laypage layui-laypage-default" id="layui-laypage-1">
                            <a href="javascript:;" class="layui-laypage-prev layui-disabled" data-page="0"><i class="layui-icon">&#xe603;</i></a>
                            <#list page.linkPages as link>
                                <#if pageNumber==link>
                                    <span class="layui-laypage-curr">
                                        <em class="layui-laypage-em"></em>
                                        <em>${link}</em>
                                    </span>
                                    <#else>
                                    <a href="javascript:;" data-page="${link}">${link}</a>
                                </#if>
                            </#list>
                            <a href="javascript:;" class="layui-laypage-next" data-page="2"><i class="layui-icon">&#xe602;</i></a>
                            <span class="layui-laypage-skip">到第<input type="text" min="${page.pageNumber}" value="${page.pageNumber}" class="layui-input">页
								<button type="button" class="layui-laypage-btn">确定</button>
							</span>
                            <span class="layui-laypage-count">共 ${page.totalRows} 条</span>
                            <span class="layui-laypage-limits">
								<select lay-ignore="">
									<option value="10">10 条/页</option>
									<option value="20" selected="">20 条/页</option>
									<option value="30">30 条/页</option>
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
    </div>
    <#include "core/layui-footer.ftl"/>
</div>
<script src="/design/static/plugins/layui/layui.all.js"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;
        var laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#test1',
            type: 'datetime'
        });
        laydate.render({
            elem: '#test2',
            type: 'datetime'
        });
    });
</script>
</body>
</html>