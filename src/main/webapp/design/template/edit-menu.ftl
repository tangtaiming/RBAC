<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html xmlns="http://www.w3.org/1999/html">
<#include "core/adminlte-head.ftl" />
<#include "core/adminlte-create-customjs.ftl" />
<link rel="stylesheet" href="/design/static/plugins/zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css" />
<link rel="stylesheet" href="/design/static/plugins/layer/theme/default/layer.css" type="text/css" />
<script src="/design/static/plugins/zTree_v3/js/jquery.ztree.all.js"></script>
<script src="/design/static/plugins/layer/layer.js" type="application/javascript"></script>
<script src="/design/static/js/ztree.plugin.js" type="application/javascript"></script>

<script>
    $(function() {
        $('#parentName').click(function() {
            var ztreeHtml = '<ul id="ztreeMenu" style="margin-left:10px" class="ztree"></ul>';
            ztreeHtml += '<div class="layui-layer-btn layui-layer-btn-"><input id="ztreeButton" type="button" class="btn btn-primary btn-sm btn-flat" value="确定" /></div>'
            //页面层
            layer.open({
                type: 1,
                skin: 'layui-layer-rim', //加上边框
                area: ['200px', '340px'], //宽高
                content: ztreeHtml,
                success: function(layero, index){
                    var ztreeObj = $.fn.zTree.init($("#ztreeMenu"), zrteeSetting, ${saveMenuRsVo.menuJson![]});
                    //打开获取查询选中值
                    //获取节点并且选中节点
                    var parentId = $('#parentId').val();
                    var selectNode = ztreeObj.getNodeByParam("menuId", parentId, null);
                    ztreeObj.selectNode(selectNode);
                    //确定按钮绑定一个事件
                    $("#ztreeButton").bind("click", ztreeObj, function(){
                        //获取选中 名称 和 id 设置到对应的 text文本中
                        var selectNodes = ztreeObj.getSelectedNodes();
                        if (!(null == selectNodes) && selectNodes.length == 1) {
                            var node = selectNodes[0];
                            // console.log('id: ' + node.menuId);
                            // console.log('name: ' + node.name);
                            $('#parentId').val(node.menuId);
                            $('#parentName').val(node.name);
                            //确定类别之后 关闭页面
                            layer.close(index);
                        } else {
                            alert('不允许选中多个类别!');
                        }
                    });
                }
            });
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
                <h4 class="page-title"><i class="fa fa-plus-square-o"></i> 新增菜单</h4>
            </div>
            <div class="col-lg-6 col-sm-8 col-md-8 col-xs-12">
                <ol class="breadcrumb">
                    <li><a href="/"><i class="fa fa-dashboard"></i> 首页</a></li>
                    <li><a href="/admin/menuManagement"> 菜单管理</a></li>
                    <li class="active">新增菜单</li>
                </ol>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- Main content -->
        <div class="content container-fluid">
            <!-- white-box -->
            <div class="white-box">
                <form id="form-menu" class="form-horizontal">
                    <div class="form-group">
                        <div class="col-xs-6">
                            <label for="loginName" class="col-sm-2 control-label">类型</label>
                            <div class="col-sm-10">
                                <label class="radio-inline">
                                    <#assign typeChecked='' />
                                    <#if saveMenuRsVo.type?? && saveMenuRsVo.type?string=="0">
                                        <#assign typeChecked='checked="checked"' />
                                    </#if>
                                    <input type="radio" name="saveMenuReVo.type" ${typeChecked} value="0"/> 目录
                                </label>
                                <label class="radio-inline">
                                    <#if saveMenuRsVo.type?? && saveMenuRsVo.type?string=="1">
                                        <#assign typeChecked='checked="checked"' />
                                    <#else>
                                        <#assign typeChecked='' />
                                    </#if>
                                    <input type="radio" name="saveMenuReVo.type" ${typeChecked} value="1"/> 菜单
                                </label>
                                <label class="radio-inline">
                                    <#if saveMenuRsVo.type?? && saveMenuRsVo.type?string=="2">
                                        <#assign typeChecked='checked="checked"' />
                                    <#else>
                                        <#assign typeChecked='' />
                                    </#if>
                                    <input type="radio" name="saveMenuReVo.type" ${typeChecked} value="2"/> 按钮
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-6">
                            <label for="name" class="col-sm-2 control-label">菜单名称</label>
                            <div class="col-sm-10">
                                <input type="text" name="saveMenuReVo.name" value="${saveMenuRsVo.name!''}" class="form-control"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-6">
                            <label for="loginName" class="col-sm-2 control-label">上级菜单</label>
                            <div class="col-sm-10">
                                <input id="parentId" name="saveMenuReVo.parentId" type="hidden" class="form-control" value="${saveMenuRsVo['menuList'][0]['menuId']!''}">
                                <input id="parentName" name="saveMenuReVo.parentName" type="text" readonly="readonly" style="cursor:pointer" class="form-control" value="${saveMenuRsVo['menuList'][0]['name']!''}">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-6">
                            <label for="loginName" class="col-sm-2 control-label">菜单URL</label>
                            <div class="col-sm-10">
                                <input type="text" name="saveMenuReVo.url" value="${saveMenuRsVo.url!''}" class="form-control"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-6">
                            <label for="perms" class="col-sm-2 control-label">授权标识</label>
                            <div class="col-sm-10">
                                <input type="text" name="saveMenuReVo.perms" value="${saveMenuRsVo.perms!''}" class="form-control"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-6">
                            <label for="orderNum" class="col-sm-2 control-label">排序号</label>
                            <div class="col-sm-10">
                                <input type="text" name="saveMenuReVo.orderNum" value="${saveMenuRsVo.orderNum!''}" class="form-control"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-6">
                            <label for="icon" class="col-sm-2 control-label">图标</label>
                            <div class="col-sm-10">
                                <input type="text" name="saveMenuReVo.icon" value="${saveMenuRsVo.icon!''}" class="form-control"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group m-b-0">
                        <div class="col-xs-6">
                            <div class="col-sm-10 col-md-offset-2">
                                <input name="saveMenuReVo.menuId" type="hidden" value="${saveMenuRsVo.menuId!''}"/>
                                <input id ="save-menu" type="button" class="btn btn-primary btn-sm btn-flat" value="保 存" />
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