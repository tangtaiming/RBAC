<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<#include "core/adminlte-head.ftl" />
<#include "core/adminlte-create-customjs.ftl" />
<link href="/design/static/plugins/jstree/dist/themes/default/style.css" type="text/css" rel="stylesheet"/>
<script src="/design/static/plugins/jstree/dist/jstree.js" type="application/javascript"></script>
<script type="application/javascript">
    $(function() {
        $('#jstree_access_div').jstree({
            "plugins" : ["checkbox"],
            'core' : {
                'data' : ${accessRsVo.navAll!'[]'}
            }
        });
    })
</script>

<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <!-- Main Header -->
    <#include "core/adminlte-header.ftl" />
    <!-- Left side column. contains the logo and sidebar -->
    <#include "core/adminlte-side.ftl" />

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <div class="row content-header" style="background: #ffffff;">
            <div class="col-lg-6 col-md-4 col-sm-4 col-xs-12">
                <h4 class="page-title"><i class="fa fa-plus-square-o"></i> 新增权限</h4>
            </div>
            <div class="col-lg-6 col-sm-8 col-md-8 col-xs-12">
                <ol class="breadcrumb">
                    <li><a href="/"><i class="fa fa-dashboard"></i> 首页</a></li>
                    <li><a href="/admin/accessManagement"> 权限管理</a></li>
                    <li class="active">新增权限</li>
                </ol>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- Main content -->
        <div class="content container-fluid">
            <!-- white-box -->
            <div class="white-box">
                <form id="form-access" class="form-horizontal">
                    <div class="form-group">
                        <div class="col-xs-6">
                            <label for="title" class="col-sm-2 control-label">名称</label>
                            <div class="col-sm-10">
                                <input id="title" name="accessRsDto.title" type="text" value="" class="form-control" placeholder="请输入名称">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-6">
                            <label for="urls" class="col-sm-2 control-label">URLS</label>
                            <div class="col-sm-10">
                                <div id="jstree_access_div"></div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group m-b-0">
                        <div class="col-xs-6">
                            <div class="col-sm-10 col-md-offset-2">
                                <input id ="save-access" type="button" class="btn btn-primary btn-sm btn-flat" value="保 存" />
                                <input type="reset" class="btn btn-primary btn-sm btn-flat" value="重 置" />
                            </div>
                        </div>
                    </div>
                </form>
            </div><!-- white-box END -->
        </div>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- Main Footer -->
    <#include "core/adminlte-footer.ftl" />

    <!-- Control Sidebar -->
    <#include "core/adminlte-control-sidebar.ftl" />
</div>
<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. -->
</body>
</html>