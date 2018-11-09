<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<#include "adminlte-head.ftl"/>
<#include "adminlte-create-customjs.ftl" />
<script type="text/javascript" src="/design/static/plugins/layer/layer.js"></script>
<script type="text/javascript" src="/design/static/js/jquery.pluginPage.js?time=${time}"></script>
<script type="text/javascript" src="/design/static/js/jquery.pluginMyFilter.js?time=${time}"></script>
<script type="text/javascript" src="/design/static/js/jquery.pluginDialog.js?time=${time}"></script>
<script type="text/javascript" src="/design/static/js/jquery.pluginAjax.js?time=${time}"></script>
<script type="text/javascript" src="/design/static/plugins/select2/dist/js/select2.min.js"></script>
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
                <h4 class="page-title"><i class="fa fa-plus-square-o"></i> <@s.text name="${title!''}"/></h4>
            </div>
            <div class="col-lg-6 col-sm-8 col-md-8 col-xs-12">
                <ol class="breadcrumb">
                    <li><a href="/"><i class="fa fa-dashboard"></i> <@s.text name="home"/></a></li>
                    <li><a href="${edit.mlink!''}"> <@s.text name="${edit.mtitle!''}"/></a></li>
                    <li class="active"><@s.text name="${title!''}"/></li>
                </ol>
            </div>
            <!-- /.col-lg-12 -->
            </div>
            <!-- Main content -->
            <div class="content container-fluid">
                <div class="col-lg-12">
                    <div class="text-right">
                        <div class="btn-group">
                            <#-- 返回 -->
                            <#assign back = ''/>
                            <#if edit.headList.backLink??>
                                <#assign back = 'href="' + edit.headList.backLink + '"'/>
                                <a ${back} class="btn btn-primary btn-sm btn-flat"><i class="fa"></i> <@s.text name="back"/></a>
                            </#if>

                            <#-- 删除 -->
                            <#assign delete = '' />
                            <#if edit.headList.deleteLink?? && entity?? && entity.id??>
                                <a onclick='$.fn.myAjax("deleteById", "${edit.headList.deleteLink}", "${edit.mlink!''}", ${entity.id})' class="btn btn-primary btn-sm btn-flat"><i class="fa fa-plus-square-o"></i> <@s.text name="delete"/></a>
                            </#if>

                            <#-- 重置 -->
                            <a onclick="window.location.reload();" class="btn btn-primary btn-sm btn-flat"><i class="fa fa-mail-reply"></i> <@s.text name="reset"/></a>
                            <a onclick='$.fn.myAjax("saveFrom", "${edit.headList.saveLink}")' class="btn btn-primary btn-sm btn-flat"><i class="fa fa-search"></i> <@s.text name="save.entity"/></a>
                            <a onclick='$.fn.myAjax("saveFromAndBack", "${edit.headList.saveLink}", "${edit.mlink!''}")' class="btn btn-primary btn-sm btn-flat"><i class="fa fa-search"></i> <@s.text name="save.entity.back"/></a>
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
                        <form id="entity-form" class="tab-content">
                            <#if entity??>
                                <input type="hidden" name="${edit.classesName}.id" class="form-control" id="${edit.classesName}_id" value="${entity['id']!''}">
                            </#if>
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
                                            <label id="field_${columnName}" for="${edit.classesName}_${columnName}" class="col-sm-2 control-label required-field"><@s.text name="${columnTitle}"/></label>
                                            <div class="col-sm-6">
                                            <#-- 文本域 -->
                                            <#if columnType=="text">
                                                <#if entity??>
                                                    <input type="text" name="${edit.classesName}.${columnName}" class="form-control" id="${edit.classesName}_${columnName}" value="${entity[columnName]!''}" placeholder="<@s.text name="${columnTitle}"/>">
                                                    <#else>
                                                    <input type="text" name="${edit.classesName}.${columnName}" class="form-control" id="${edit.classesName}_${columnName}" value="" placeholder="<@s.text name="${columnTitle}"/>">
                                                </#if>

                                                <#-- 复选框 -->
                                                <#elseif columnType=="checkbox">
                                                <div class="checkbox checkbox-${columnName}">
                                                    <#assign columnOption=column["option"] />
                                                    <#list columnOption?keys as optionKey>
                                                        <#-- 选中数据勾选 -->
                                                        <#assign choseStyle="" />
                                                        <#if entity??>
                                                            <#list entity[columnName] as entityRow>
                                                                <#if entityRow?string==optionKey>
                                                                    <#assign choseStyle='checked="checked"' />
                                                                    <#break >
                                                                </#if>
                                                            </#list>
                                                        </#if>
                                                        <label>
                                                            <input type="checkbox" ${choseStyle} name="${edit.classesName}.${columnName}" id="${edit.classesName}_${columnName}" value="${optionKey}">${columnOption[optionKey]!' '}
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