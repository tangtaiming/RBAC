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
        <!-- Main content -->
        <div class="content container-fluid">
            <div class="white-box">
                RBAC系统 欢迎您!
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