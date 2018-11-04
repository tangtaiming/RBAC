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
                        <#assign tabsEntity=edit.tabsList />
                        <ul class="nav nav-tabs">
                        <#list tabsEntity as tabEntity>
                            <#assign tabTitle = tabEntity["title"]/>
                            <#assign tabActive = '' />
                            <#if tabEntity_index == 0>
                                <#assign tabActive = 'class="active"' />
                            </#if>

                            <li ${tabActive}><a href="#settings" data-toggle="tab"><@s.text name="${tabTitle}" /></a></li>
                        </#list>
                        </ul>
                        <form class="tab-content">
                            <#assign tabsEntity=edit.tabsList />
                            <#list tabsEntity as tabEntity>
                            <#assign tabValue = tabEntity["tab"]/>
                            <#assign tabActive = '' />
                            <#if tabEntity_index == 0>
                                <#assign tabActive = 'active' />
                            </#if>
                            <div class="tab-pane ${tabActive}" id="settings">
                                <div class="form-horizontal">
                                    <#list tabValue as column>
                                        <#assign columnName=column["name"] />
                                        <#assign columnType=column["type"] />
                                        <#assign columnTitle=column["title"] />
                                        <div class="form-group">
                                            <label for="${edit.classesName}_${columnName}" class="col-sm-2 control-label"><@s.text name="${columnTitle}"/></label>
                                            <div class="col-sm-6">
                                            <#-- 文本域 -->
                                            <#if columnType=="text">
                                                <input type="text" name="${edit.classesName}.${columnName}" class="form-control" id="${edit.classesName}_${columnName}" placeholder="<@s.text name="${columnTitle}"/>">

                                                <#-- 复选框 -->
                                                <#elseif columnType=="checkbox">
                                                <div class="checkbox">
                                                    <#assign columnOption=column["option"] />
                                                    <#list columnOption?keys as optionKey>
                                                        <label>
                                                            <input type="checkbox" name="${edit.classesName}.${columnName}" id="${edit.classesName}_${columnName}">${columnOption[optionKey]!' '}
                                                        </label>
                                                    </#list>
                                                </div>

                                                <#-- 自定义ftl页面 -->
                                                <#elseif columnType=="ftl">
                                                    <#assign columnFtl=column["ftl"] />
                                                    <#include "${columnFtl}"/>

                                            </#if>
                                            </div>
                                        </div>
                                    </#list>
                                </div>
                            </div>
                            </#list>
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