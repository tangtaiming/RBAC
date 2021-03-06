<#if request.getMethod()="GET">
    <!DOCTYPE html>
    <!--
    This is a starter template page. Use this page to start your new project from
    scratch. This page gets rid of all links and provides the needed markup only.
    -->
    <html>
    <#include "adminlte-head.ftl"/>

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
                <#assign managerTitle=main.titleOne!'' />
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title"><i class="fa fa-list"></i> <@s.text name="${managerTitle!''}"/></h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                        <li class="active"><@s.text name="${managerTitle!''}"/></li>
                    </ol>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- Main content -->
            <div class="content container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="text-right">
                            <div class="btn-group">
                                <a href="${main.head.createLink!''}" class="btn btn-primary btn-sm btn-flat"><i class="fa fa-plus-square-o"></i> 新增</a>
                                <a class="btn btn-primary btn-sm btn-flat"><i class="fa fa-mail-reply"></i> 重置</a>
                                <a id="filter-submit" class="btn btn-primary btn-sm btn-flat"><i class="fa fa-search"></i> 搜索</a>
                            </div>
                        </div>

                        <!-- 表格 -->
                        <div class="table-responsive">
                            <table class="layui-table">
                                <thead>
                                    <tr>
                                        <#assign  titleList = main.body.title/>
                                        <#list titleList as titleRow>
                                            <#assign title = titleRow['title'] />
                                            <#assign width = '' />
                                            <#if titleRow['width']??>
                                                <#assign width = 'style="width:' + titleRow['width'] + '"' />
                                            </#if>
                                            <#if (titleRow_index + 1) == titleList?size>
                                                <th class="layui-edit-last">
                                                    <@s.text name="${title}" />
                                                </th>
                                                <#else>
                                                <th ${width}><@s.text name="${title}" /></th>
                                            </#if>
                                        </#list>
                                    </tr>
                                    <tr id="search">
                                        <#assign searchList = main.body.search/>
                                        <#list searchList as searchRow>
                                            <#assign searchType = searchRow['type'] />
                                            <#assign searchName = searchRow['name'] />
                                            <#-- 文本域 -->
                                            <#if searchType=="text">
                                                <th>
                                                    <input type="text" name="${searchName}" class="filter form-control custom-form-control" />
                                                </th>
                                                <#elseif searchType=="select">
                                                <th>
                                                    <#assign optionList=searchRow['option']/>
                                                    <select name="${searchName}" class="filter select2" style="width:100%;">
                                                        <#list optionList?keys as optionKey>
                                                            <#if optionKey=="">
                                                                <option value="">&nbsp;</option>
                                                                <#else>
                                                                <option value="${optionKey!''}">${optionList[optionKey]!''}</option>
                                                            </#if>
                                                        </#list>
                                                    </select>
                                                </th>
                                                <#elseif searchType=="action">
                                                <th class="layui-edit-last"></th>
                                            </#if>
                                        </#list>
                                    </tr>
                                </thead>
                                <tbody id="adminlte-tbody-data-list">
                                    <#assign  bodyDataList = main.body.data />
                                    <#list dataList as row>
                                    <tr>
                                        <#list searchList as dataRow>
                                            <#assign dataType=dataRow.type>
                                            <#if dataType?? && dataType=="action">
                                                <#assign dataStyle=dataRow.style />
                                                <#assign actionLink=dataRow.actionLink />
                                                <td>
                                                    <div class="layui-edit-last">
                                                    <#if dataStyle=="base">
                                                        <#list actionLink as alink>
                                                            <#if alink.editType="edit">
                                                                <a href="${alink.editLink}?id=${row.id}" class="btn btn-primary btn-xs btn-flat"><i class="fa fa-pencil-square-o"></i> 编辑</a>
                                                                <#elseif alink.editType="delete">
                                                                <a class="btn btn-danger btn-xs btn-flat"><i class="fa fa-trash-o"></i> 删除</a>
                                                            </#if>
                                                        </#list>
                                                    </#if>
                                                    </div>
                                                </td>
                                                <#elseif dataType='select'>
                                                <td>
                                                    <#assign optionList=dataRow.option/>
                                                    <#assign dataKey=row[dataRow.name]!''/>
                                                    <#if optionList[dataKey?string]??>
                                                        ${optionList[dataKey?string]}
                                                        <#else>
                                                        ${dataKey?string}
                                                    </#if>
                                                </td>
                                                <#else>
                                                <td>
                                                    ${row[dataRow.name]!''}
                                                </td>
                                            </#if>
                                        </#list>
                                    </tr>
                                    </#list>
                                    <tr>
                                        <td colspan="${bodyDataList?size}" style="padding:0px 0px 0px 0px; height:45px;">
                                            <!-- 分页 -->
                                            <#include "adminlte-page.ftl" />
                                            <!-- 分页 END -->
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div><!-- 表格 END -->
                    </div>
                </div><!--row end-->
            </div>
            <!-- /.content -->
        </div>
        <!-- /.content-wrapper -->

        <!-- Main Footer -->
        <#include "adminlte-footer.ftl"/>

        <!-- Control Sidebar -->
        <#include "adminlte-control-sidebar.ftl">
    </div>
    <!-- Optionally, you can add Slimscroll and FastClick plugins.
         Both of these plugins are recommended to enhance the
         user experience. -->
    </body>
    <script type="text/javascript" src="/design/static/plugins/layer/layer.js"></script>
    <script type="text/javascript" src="/design/static/js/jquery.pluginPage.js?time=${time}"></script>
    <script type="text/javascript" src="/design/static/js/jquery.pluginMyFilter.js?time=${time}"></script>
    <script type="text/javascript" src="/design/static/js/jquery.pluginDialog.js?time=${time}"></script>
    <script type="text/javascript" src="/design/static/js/jquery.pluginAjax.js?time=${time}"></script>
    <script type="text/javascript" src="/design/static/plugins/select2/dist/js/select2.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $('#adminlte-button-page-number').click(function() {
                var pageNumber = $.fn.page('fetchPageNumber');
                $.fn.page('pager', null, pageNumber);
            });

            //点击搜索
            $('#filter-submit').click(function() {
                $.fn.myfilter('build', null);
            });

            $('.select2').select2();
        });
    </script>
    </html>

    <#else>
        <#assign searchList = main.body.search/>
        <#assign bodyDataList = main.body.data />
        <#list dataList as row>
        <tr>
            <#list searchList as dataRow>
                <#assign dataType=dataRow.type>
                <#if dataType?? && dataType=="action">
                    <#assign dataStyle=dataRow.style />
                    <#assign actionLink=dataRow.actionLink />
                    <td>
                        <div class="layui-edit-last">
                        <#if dataStyle=="base">
                            <#list actionLink as alink>
                                <#if alink.editType="edit">
                                    <a href="${alink.editLink}?id=${row.id}" class="btn btn-primary btn-xs btn-flat"><i class="fa fa-pencil-square-o"></i> 编辑</a>
                                <#elseif alink.editType="delete">
                                    <a class="btn btn-danger btn-xs btn-flat"><i class="fa fa-trash-o"></i> 删除</a>
                                </#if>
                            </#list>
                        </#if>
                        </div>
                    </td>
                <#elseif dataType='select'>
                    <td>
                        <#assign optionList=dataRow.option/>
                        <#assign dataKey=row[dataRow.name]!''/>
                        <#if optionList[dataKey?string]??>
                            ${optionList[dataKey?string]}
                        <#else>
                            ${dataKey?string}
                        </#if>
                    </td>
                <#else>
                    <td>
                        ${row[dataRow.name]!''}
                    </td>
                </#if>
            </#list>
        </tr>
        </#list>
        <tr>
            <td colspan="${bodyDataList?size}" style="padding:0px 0px 0px 0px; height:45px;">
                <!-- 分页 -->
                <#include "adminlte-page.ftl" />
                <!-- 分页 END -->
            </td>
        </tr>
        <script>
            $(function () {
                $('#adminlte-button-page-number').click(function() {
                    var pageNumber = $.fn.page('fetchPageNumber');
                    $.fn.page('pager', null, pageNumber);
                });
            })
        </script>
</#if>
