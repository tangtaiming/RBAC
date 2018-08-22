<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<#include "core/adminlte-head.ftl" />
<#include "core/adminlte-edit-customjs.ftl" />
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
                <h4 class="page-title"><i class="fa fa-pencil-square-o"></i> 设置( ${siteAccessRsVo['name']} )权限</h4>
            </div>
            <div class="col-lg-6 col-sm-8 col-md-8 col-xs-12">
                <ol class="breadcrumb">
                    <li><a href="/"><i class="fa fa-dashboard"></i> 首页</a></li>
                    <li><a href="/admin/accessManagement"> 权限管理</a></li>
                    <li class="active">设置${siteAccessRsVo['name']}权限</li>
                </ol>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- Main content -->
        <div class="content container-fluid">
            <!-- white-box -->
            <div class="white-box">
                <form id="form-site-access" class="form-horizontal">
                    <div class="form-group">
                        <div class="col-xs-6">
                            <label for="email" class="col-sm-2 control-label">权限</label>
                            <div class="col-sm-10">
                                <div class="checkbox">
                                    <#list siteAccessRsVo['accessList'] as access>
                                        <#assign chonsen = ''/>
                                        <#list siteAccessRsVo['chosenAccess'] as chonsenAccess>
                                            <#if access['id']==chonsenAccess>
                                                <#assign chonsen='checked="checked"' />
                                                <#break>
                                            </#if>
                                        </#list>
                                        <label>
                                            <input type="checkbox" ${chonsen} name="saveSiteAccessReVo.accessId" value="${access['id']}" />${access['title']}
                                        </label>
                                    </#list>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group m-b-0">
                        <div class="col-xs-6">
                            <div class="col-sm-10 col-md-offset-2">
                                <#assign roleId = siteAccessRsVo['id']/>
                                <#if roleId??>
                                    <input type="hidden" name="saveSiteAccessReVo.roleId" value="${roleId}"/>
                                </#if>
                                <input id ="save-site-access" type="button" class="btn btn-primary btn-sm btn-flat" value="保 存" />
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

