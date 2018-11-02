<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<#include "adminlte-head.ftl"/>
<#include "adminlte-create-customjs.ftl" />
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <!-- Main Header -->
    <#include "adminlte-header.ftl"/>
    <!-- Left side column. contains the logo and sidebar -->
    <#include "adminlte-side.ftl"/>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <div class="row content-header" style="background: #ffffff;">
            <div class="col-lg-6 col-md-4 col-sm-4 col-xs-12">
                <h4 class="page-title"><i class="fa fa-plus-square-o"></i> 新增用户</h4>
            </div>
            <div class="col-lg-6 col-sm-8 col-md-8 col-xs-12">
                <ol class="breadcrumb">
                    <li><a href="/"><i class="fa fa-dashboard"></i> 首页</a></li>
                    <li><a href="/admin/roleManagement"> 用户管理</a></li>
                    <li class="active">新增用户</li>
                </ol>
            </div>
            <!-- /.col-lg-12 -->
            </div>
            <!-- Main content -->
            <div class="content container-fluid">
                <div class="col-lg-12">
                    <div class="text-right">
                        <div class="btn-group">
                            <a class="btn btn-primary btn-sm btn-flat"><i class="fa"></i> 返回</a>
                            <a class="btn btn-primary btn-sm btn-flat"><i class="fa fa-plus-square-o"></i> 删除</a>
                            <a class="btn btn-primary btn-sm btn-flat"><i class="fa fa-mail-reply"></i> 重置</a>
                            <a class="btn btn-primary btn-sm btn-flat"><i class="fa fa-search"></i> 保存</a>
                            <a class="btn btn-primary btn-sm btn-flat"><i class="fa fa-search"></i> 保存并返回</a>
                        </div>
                    </div>
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#settings" data-toggle="tab">用户基本信息</a></li>
                        </ul>
                        <form class="tab-content">
                            <div class="tab-pane active" id="settings">
                                <div class="form-horizontal">
                                    <div class="form-group">
                                        <label for="inputName" class="col-sm-2 control-label">Name</label>

                                        <div class="col-sm-6">
                                            <input type="email" class="form-control" id="inputName" placeholder="Name">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputEmail" class="col-sm-2 control-label">Email</label>

                                        <div class="col-sm-6">
                                            <input type="email" class="form-control" id="inputEmail" placeholder="Email">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputName" class="col-sm-2 control-label">Name</label>

                                        <div class="col-sm-6">
                                            <input type="text" class="form-control" id="inputName" placeholder="Name">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputExperience" class="col-sm-2 control-label">Experience</label>

                                        <div class="col-sm-6">
                                            <textarea class="form-control" id="inputExperience" placeholder="Experience"></textarea>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputSkills" class="col-sm-2 control-label">Skills</label>

                                        <div class="col-sm-6">
                                            <input type="text" class="form-control" id="inputSkills" placeholder="Skills">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- /.tab-pane -->
                        </form>
                        <!-- /.tab-content -->
                    </div>
                    <!-- /.nav-tabs-custom -->
                </div>
                <!-- /.col -->
            </div>
            <!-- /.row -->
        </div>
    <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- Main Footer -->
    <#include "adminlte-footer.ftl"/>

    <!-- Control Sidebar -->
    <#include "adminlte-control-sidebar.ftl" />
</div>
<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. -->
</body>
</html>