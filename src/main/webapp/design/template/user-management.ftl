<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<#include "core/adminlte-head.ftl"/>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <!-- Main Header -->
    <#include "core/adminlte-header.ftl"/>
    <!-- Left side column. contains the logo and sidebar -->
    <#include "core/adminlte-side.ftl"/>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <div class="row content-header" style="background: #ffffff;">
            <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                <h4 class="page-title"><i class="fa fa-list"></i> 用户管理</h4>
            </div>
            <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                <ol class="breadcrumb">
                    <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                    <li class="active">用户管理</li>
                </ol>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- Main content -->
        <div class="content container-fluid">
            <div class="white-box">
                <p class="text-muted m-b-30">
                <div>
                    <a href="/admin/createUser" class="btn btn-primary btn-sm btn-flat"><i class="fa fa-plus-square-o"></i> 新增</a>
                </div>
                </p>
                <div class="table-responsive">
                    <div id="" class="dataTables_wrapper dt-bootstrap4 no-footer">
                        <!-- 表格 -->
                        <div class="row">
                            <div class="col-sm-12">
                                <table class="layui-table">
                                    <thead>
                                    <tr>
                                        <th>登录名</th>
                                        <th>邮箱</th>
                                        <th class="layui-edit-last">操作</th>
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
                                                    <div class="layui-edit-last">
                                                        <a href="/admin/editUser?id=${user["id"]}"
                                                           class="btn btn-primary btn-xs btn-flat"><i class="fa fa-pencil-square-o"></i> 编辑</a>
                                                        <a class="btn btn-danger btn-xs btn-flat"><i class="fa fa-trash-o"></i> 删除</a>
                                                    </div>
                                                </td>
                                            </tr>
                                        </#list>
                                    </#if>
                                    </tbody>
                                </table>
                            </div>
                        </div><!-- 表格 END -->
                        <!-- 分页 -->
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="layui-table-page">
                                    <#assign linkPages=page.linkPages />
                                    <#assign pageNumber=page.pageNumber />
                                    <#assign pageSize=page.pageSize />
                                    <#assign totalRows=page.totalRows />
                                    <#assign upRow=page.upRow />
                                    <#assign downRow=page.downRow />
                                    <div class="layui-table-page5">
                                        <div class="layui-box layui-laypage layui-laypage-default">
                                            <#assign upDisabledClass='layui-disabled' />
                                            <#assign upHref='' />
                                            <#if upRow?string("true", "false")=="true">
                                                <#assign upDisabledClass='' />
                                                <#assign upHref='href="/xxxx/yyyy"' />
                                            </#if>
                                            <a ${upHref} class="layui-laypage-prev ${upDisabledClass}">
                                                <i class="fa fa-angle-left"></i>
                                            </a>
                                            <#list linkPages as link>
                                                <#if link==pageNumber>
                                                    <span class="layui-laypage-curr">
                                                        <em class="layui-laypage-em"></em>
                                                        <em>${link}</em>
                                                    </span>
                                                    <#else>
                                                    <a href="${link}">${link}</a>
                                                </#if>
                                            </#list>
                                            <#assign downDisabledClass='layui-disabled' />
                                            <#assign downHref='' />
                                            <#if downRow?string("true", "false")=="true">
                                                <#assign downDisabledClass='' />
                                                <#assign downHref='href="/xxxx/yyyy"' />
                                            </#if>
                                            <a ${downHref} class="layui-laypage-next ${downDisabledClass}">
                                                <i class="fa fa-angle-right"></i>
                                            </a>
                                            <span class="layui-laypage-skip">
                                                到第
                                                <input type="text" min="2" value="${pageNumber}" class="layui-input">
                                                页
                                                <button type="button" class="layui-laypage-btn">确定</button>
                                            </span>
                                            <span class="layui-laypage-count">共 ${totalRows} 条</span>
                                            <span class="layui-laypage-limits">
                                                <select lay-ignore="">
                                                    <#list [10, 20, 30, 40, 50, 60, 70, 80, 90] as pz>
                                                        <#assign selectAttr='' />
                                                        <#if pageSize==pz>
                                                            <#assign selectAttr='selected="selected"' />
                                                        </#if>
                                                        <option value="${pz}" ${selectAttr}>${pz} 条/页</option>
                                                    </#list>
                                                </select>
										    </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div><!-- 分页 END -->
                    </div>
                </div><!-- table-responsive -->
            </div><!-- white-box -->
        </div>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- Main Footer -->
    <#include "core/adminlte-footer.ftl"/>

    <!-- Control Sidebar -->
    <#include "core/adminlte-control-sidebar.ftl">
</div>
<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. -->
</body>
</html>