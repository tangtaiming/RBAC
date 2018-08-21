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
                                        <th>名称</th>
                                        <th class="layui-edit-last">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <#if roleRsVo??>
                                        <#list roleRsVo as role>
                                        <tr>
                                            <td class="layui-table-td">
                                                <div class="layui-table-cell">${role["name"]}</div>
                                            </td>
                                            <td class="layui-table-td">
                                                <div class="layui-edit-last">
                                                    <a class="layui-btn layui-btn-xs" href="/admin/editRole?id=${role["id"]}">
                                                        <i class="layui-icon">&#xe642;</i>编辑
                                                    </a>
                                                    <a class="layui-btn layui-btn-xs" href="/admin/siteAccess?id=${role["id"]}">
                                                        <i class="layui-icon">&#xe642;</i>设置权限
                                                    </a>
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
                        <#include "core/adminlte-page.ftl"/>
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