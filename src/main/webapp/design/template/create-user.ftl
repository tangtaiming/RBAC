<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | Starter</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="/design/static/plugins/admin-lte/bower_components/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/design/static/plugins/admin-lte/bower_components/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="/design/static/plugins/admin-lte/bower_components/Ionicons/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/design/static/plugins/admin-lte/dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
          page. However, you can choose any other skin. Make sure you
          apply the skin class to the body tag so the changes take effect. -->
    <link rel="stylesheet" href="/design/static/plugins/admin-lte/dist/css/skins/skin-blue.min.css">

    <!-- Google Font -->
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<!--
BODY TAG OPTIONS:
=================
Apply one or more of the following classes to get the
desired effect
|---------------------------------------------------------|
| SKINS         | skin-blue                               |
|               | skin-black                              |
|               | skin-purple                             |
|               | skin-yellow                             |
|               | skin-red                                |
|               | skin-green                              |
|---------------------------------------------------------|
|LAYOUT OPTIONS | fixed                                   |
|               | layout-boxed                            |
|               | layout-top-nav                          |
|               | sidebar-collapse                        |
|               | sidebar-mini                            |
|---------------------------------------------------------|
-->
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <!-- Main Header -->
    <header class="main-header">

        <!-- Logo -->
        <a href="index2.html" class="logo">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini"><b>A</b>LT</span>
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg"><b>Admin</b>LTE</span>
        </a>

        <!-- Header Navbar -->
        <nav class="navbar navbar-static-top" role="navigation">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
                <span class="sr-only">Toggle navigation</span>
            </a>
            <!-- Navbar Right Menu -->
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <!-- Messages: style can be found in dropdown.less-->
                    <li class="dropdown messages-menu">
                        <!-- Menu toggle button -->
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="fa fa-envelope-o"></i>
                            <span class="label label-success">4</span>
                        </a>
                        <ul class="dropdown-menu">
                            <li class="header">You have 4 messages</li>
                            <li>
                                <!-- inner menu: contains the messages -->
                                <ul class="menu">
                                    <li><!-- start message -->
                                        <a href="#">
                                            <div class="pull-left">
                                                <!-- User Image -->
                                                <img src="/design/static/images/avatar/user2-160x160.jpg" class="img-circle" alt="User Image">
                                            </div>
                                            <!-- Message title and timestamp -->
                                            <h4>
                                                Support Team
                                                <small><i class="fa fa-clock-o"></i> 5 mins</small>
                                            </h4>
                                            <!-- The message -->
                                            <p>Why not buy a new awesome theme?</p>
                                        </a>
                                    </li>
                                    <!-- end message -->
                                </ul>
                                <!-- /.menu -->
                            </li>
                            <li class="footer"><a href="#">See All Messages</a></li>
                        </ul>
                    </li>
                    <!-- /.messages-menu -->

                    <!-- Notifications Menu -->
                    <li class="dropdown notifications-menu">
                        <!-- Menu toggle button -->
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="fa fa-bell-o"></i>
                            <span class="label label-warning">10</span>
                        </a>
                        <ul class="dropdown-menu">
                            <li class="header">You have 10 notifications</li>
                            <li>
                                <!-- Inner Menu: contains the notifications -->
                                <ul class="menu">
                                    <li><!-- start notification -->
                                        <a href="#">
                                            <i class="fa fa-users text-aqua"></i> 5 new members joined today
                                        </a>
                                    </li>
                                    <!-- end notification -->
                                </ul>
                            </li>
                            <li class="footer"><a href="#">View all</a></li>
                        </ul>
                    </li>
                    <!-- Tasks Menu -->
                    <li class="dropdown tasks-menu">
                        <!-- Menu Toggle Button -->
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="fa fa-flag-o"></i>
                            <span class="label label-danger">9</span>
                        </a>
                        <ul class="dropdown-menu">
                            <li class="header">You have 9 tasks</li>
                            <li>
                                <!-- Inner menu: contains the tasks -->
                                <ul class="menu">
                                    <li><!-- Task item -->
                                        <a href="#">
                                            <!-- Task title and progress text -->
                                            <h3>
                                                Design some buttons
                                                <small class="pull-right">20%</small>
                                            </h3>
                                            <!-- The progress bar -->
                                            <div class="progress xs">
                                                <!-- Change the css width attribute to simulate progress -->
                                                <div class="progress-bar progress-bar-aqua" style="width: 20%" role="progressbar"
                                                     aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                                    <span class="sr-only">20% Complete</span>
                                                </div>
                                            </div>
                                        </a>
                                    </li>
                                    <!-- end task item -->
                                </ul>
                            </li>
                            <li class="footer">
                                <a href="#">View all tasks</a>
                            </li>
                        </ul>
                    </li>
                    <!-- User Account Menu -->
                    <li class="dropdown user user-menu">
                        <!-- Menu Toggle Button -->
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <!-- The user image in the navbar-->
                            <img src="/design/static/images/avatar/user2-160x160.jpg" class="user-image" alt="User Image">
                            <!-- hidden-xs hides the username on small devices so only the image appears. -->
                            <span class="hidden-xs">Alexander Pierce</span>
                        </a>
                        <ul class="dropdown-menu">
                            <!-- The user image in the menu -->
                            <li class="user-header">
                                <img src="/design/static/images/avatar/user2-160x160.jpg" class="img-circle" alt="User Image">

                                <p>
                                    Alexander Pierce - Web Developer
                                    <small>Member since Nov. 2012</small>
                                </p>
                            </li>
                            <!-- Menu Body -->
                            <li class="user-body">
                                <div class="row">
                                    <div class="col-xs-4 text-center">
                                        <a href="#">Followers</a>
                                    </div>
                                    <div class="col-xs-4 text-center">
                                        <a href="#">Sales</a>
                                    </div>
                                    <div class="col-xs-4 text-center">
                                        <a href="#">Friends</a>
                                    </div>
                                </div>
                                <!-- /.row -->
                            </li>
                            <!-- Menu Footer-->
                            <li class="user-footer">
                                <div class="pull-left">
                                    <a href="#" class="btn btn-default btn-flat">Profile</a>
                                </div>
                                <div class="pull-right">
                                    <a href="#" class="btn btn-default btn-flat">Sign out</a>
                                </div>
                            </li>
                        </ul>
                    </li>
                    <!-- Control Sidebar Toggle Button -->
                    <li>
                        <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="main-sidebar">

        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">

            <!-- Sidebar user panel (optional) -->
            <div class="user-panel">
                <div class="pull-left image">
                    <img src="/design/static/images/avatar/user2-160x160.jpg" class="img-circle" alt="User Image">
                </div>
                <div class="pull-left info">
                    <p>Alexander Pierce</p>
                    <!-- Status -->
                    <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                </div>
            </div>

            <!-- search form (Optional) -->
            <form action="#" method="get" class="sidebar-form">
                <div class="input-group">
                    <input type="text" name="q" class="form-control" placeholder="Search...">
                    <span class="input-group-btn">
              <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
              </button>
            </span>
                </div>
            </form>
            <!-- /.search form -->

            <!-- Sidebar Menu -->
            <ul class="sidebar-menu" data-widget="tree">
                <li class="header">HEADER</li>
                <!-- Optionally, you can add icons to the links -->
                <li class="active"><a href="#"><i class="fa fa-link"></i> <span>Link</span></a></li>
                <li><a href="#"><i class="fa fa-link"></i> <span>Another Link</span></a></li>
                <li class="treeview">
                    <a href="#"><i class="fa fa-link"></i> <span>Multilevel</span>
                        <span class="pull-right-container">
                <i class="fa fa-angle-left pull-right"></i>
              </span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="#">Link in level 2</a></li>
                        <li><a href="#">Link in level 2</a></li>
                    </ul>
                </li>
            </ul>
            <!-- /.sidebar-menu -->
        </section>
        <!-- /.sidebar -->
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <style>
            .content-header {
                position: relative;
                background: #ffffff;
                overflow: hidden;
                padding: 15px 10px 9px;
                margin-bottom: 25px;
                margin-left: 0px;
                margin-right: 0px;
            }
            .content-header .breadcrumb {
                background: none;
                margin-bottom: 0px;
                float: right;
                padding: 0;
                margin-top: 8px;
            }
            .content-header h4 {
                text-transform: uppercase;
                font-size: 20px;
                font-weight: bold;
                margin-top: 6px;
            }
            h4 {
                line-height: 22px;
                font-size: 18px;
            }
            h1, h2, h3, h4, h5, h6 {
                color: #313131;
                font-family: 'Rubik', sans-serif;
                margin: 10px 0;
                font-weight: 300;
            }

            .white-box {
                background: #ffffff;
                padding: 25px;
                margin-bottom: 30px;
            }

            .content {
                padding:0px 15px 15px 15px;
            }

            .table-responsive {
                overflow-y: hidden;
                overflow-x: hidden;
            }



            .layui-table-page {
                position: relative;
                width: 100%;
                padding: 7px 7px 0;
                border-width: 1px 0 0;
                height: 41px;
                font-size: 12px
            }

            .layui-table-page>div {
                height: 26px
            }

            .layui-table-page .layui-laypage {
                margin: 0
            }

            .layui-table-page .layui-laypage a,.layui-table-page .layui-laypage span {
                height: 26px;
                line-height: 26px;
                margin-bottom: 10px;
                border: none;
                background: 0 0
            }

            .layui-table-page .layui-laypage a,.layui-table-page .layui-laypage span.layui-laypage-curr {
                padding: 0 12px
            }

            .layui-table-page .layui-laypage span {
                margin-left: 0;
                padding: 0
            }

            .layui-table-page .layui-laypage .layui-laypage-prev {
                margin-left: -7px!important
            }

            .layui-table-page .layui-laypage .layui-laypage-curr .layui-laypage-em {
                left: 0;
                top: 0;
                padding: 0
            }

            .layui-table-page .layui-laypage button,.layui-table-page .layui-laypage input {
                height: 26px;
                line-height: 26px
            }

            .layui-table-page .layui-laypage input {
                width: 40px
            }

            .layui-table-page .layui-laypage button {
                padding: 0 10px
            }

            .layui-table-page select {
                height: 18px
            }




            .layui-laypage {
                display: inline-block;
                *display: inline;
                *zoom: 1;
                vertical-align: middle;
                margin: 10px 0;
                font-size: 0
            }

            .layui-laypage>a:first-child,.layui-laypage>a:first-child em {
                border-radius: 2px 0 0 2px
            }

            .layui-laypage>a:last-child,.layui-laypage>a:last-child em {
                border-radius: 0 2px 2px 0
            }

            .layui-laypage>:first-child {
                margin-left: 0!important
            }

            .layui-laypage>:last-child {
                margin-right: 0!important
            }

            .layui-laypage a,.layui-laypage button,.layui-laypage input,.layui-laypage select,.layui-laypage span {
                border: 1px solid #e2e2e2
            }

            .layui-laypage a,.layui-laypage span {
                display: inline-block;
                *display: inline;
                *zoom: 1;
                vertical-align: middle;
                padding: 0 15px;
                height: 28px;
                line-height: 28px;
                margin: 0 -1px 5px 0;
                background-color: #fff;
                color: #333;
                font-size: 13px
            }

            .layui-laypage a:hover {
                color: #3c8dbc;
            }

            .layui-laypage em {
                font-style: normal
            }

            .layui-laypage .layui-laypage-spr {
                color: #999;
                font-weight: 700
            }

            .layui-laypage a {
                text-decoration: none
            }

            .layui-laypage .layui-laypage-curr {
                position: relative
            }

            .layui-laypage .layui-laypage-curr em {
                position: relative;
                color: #fff
            }

            .layui-laypage .layui-laypage-curr .layui-laypage-em {
                position: absolute;
                left: -1px;
                top: -1px;
                padding: 1px;
                width: 100%;
                height: 100%;
                background-color: #3c8dbc;
            }

            .layui-laypage-em {
                border-radius: 2px
            }

            .layui-laypage-next em,.layui-laypage-prev em {
                font-family: Sim sun;
                font-size: 16px
            }

            .layui-laypage .layui-laypage-count,.layui-laypage .layui-laypage-limits,.layui-laypage .layui-laypage-refresh,.layui-laypage .layui-laypage-skip {
                margin-left: 10px;
                margin-right: 10px;
                padding: 0;
                border: none
            }

            .layui-laypage .layui-laypage-limits,.layui-laypage .layui-laypage-refresh {
                vertical-align: top
            }

            .layui-laypage .layui-laypage-refresh i {
                font-size: 18px;
                cursor: pointer
            }

            .layui-laypage select {
                height: 26px;
                padding: 3px;
                border-radius: 2px;
                cursor: pointer
            }

            .layui-laypage .layui-laypage-skip {
                height: 30px;
                line-height: 30px;
                color: #999
            }

            .layui-laypage button,.layui-laypage input {
                height: 30px;
                line-height: 30px;
                border-radius: 2px;
                vertical-align: top;
                background-color: #fff;
                box-sizing: border-box
            }

            .layui-laypage input {
                display: inline-block;
                width: 40px;
                margin: 0 10px;
                padding: 0 3px;
                text-align: center
            }

            .layui-laypage input:focus,.layui-laypage select:focus {
                border-color: #009688!important
            }

            .layui-laypage button {
                margin-left: 10px;
                padding: 0 10px;
                cursor: pointer
            }

            .layui-disabled,.layui-disabled:hover {
                color: #d2d2d2!important;
                cursor: not-allowed!important
            }


            .layui-table {
                width: 100%;
                background-color: #fff;
                color: #666
            }

            .layui-table tr {
                transition: all .3s;
                -webkit-transition: all .3s
            }

            .layui-table th {
                text-align: left;
                font-weight: 400
            }

            .layui-table tbody tr:hover,.layui-table thead tr,.layui-table-click,.layui-table-header,.layui-table-hover,.layui-table-mend,.layui-table-patch,.layui-table-tool,.layui-table[lay-even] tr:nth-child(even) {
                background-color: #f2f2f2
            }

            .layui-table td,.layui-table th,.layui-table-fixed-r,.layui-table-header,.layui-table-page,.layui-table-tips-main,.layui-table-tool,.layui-table-view,.layui-table[lay-skin=line],.layui-table[lay-skin=row] {
                border-width: 1px;
                border-style: solid;
                border-color: #e6e6e6
            }

            .layui-table td,.layui-table th {
                position: relative;
                padding: 9px 15px;
                min-height: 20px;
                line-height: 20px;
                font-size: 14px
            }

            .layui-table[lay-skin=line] td,.layui-table[lay-skin=line] th {
                border-width: 0 0 1px
            }

            .layui-table[lay-skin=row] td,.layui-table[lay-skin=row] th {
                border-width: 0 1px 0 0
            }

            .layui-table[lay-skin=nob] td,.layui-table[lay-skin=nob] th {
                border: none
            }

            .layui-table img {
                max-width: 100px
            }

            .layui-table[lay-size=lg] td,.layui-table[lay-size=lg] th {
                padding: 15px 30px
            }

            .layui-table-view .layui-table[lay-size=lg] .layui-table-cell {
                height: 40px;
                line-height: 40px
            }

            .layui-table[lay-size=sm] td,.layui-table[lay-size=sm] th {
                font-size: 12px;
                padding: 5px 10px
            }

            .layui-table-view .layui-table[lay-size=sm] .layui-table-cell {
                height: 20px;
                line-height: 20px
            }

            .layui-table[lay-data] {
                display: none
            }

            .layui-table-box,.layui-table-view {
                position: relative;
                overflow: hidden
            }

            .layui-table-view .layui-table {
                position: relative;
                width: auto;
                margin: 0
            }

            .layui-table-view .layui-table[lay-skin=line] {
                border-width: 0 1px 0 0
            }

            .layui-table-view .layui-table[lay-skin=row] {
                border-width: 0 0 1px
            }

            .layui-table-view .layui-table td,.layui-table-view .layui-table th {
                padding: 5px 0;
                border-top: none;
                border-left: none
            }

            .layui-table-view .layui-table td {
                cursor: default
            }

            .m-b-0 {
                margin-bottom:0px;
            }
        </style>
        <!-- Content Header (Page header) -->
        <div class="row content-header" style="background: #ffffff;">
            <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                <h4 class="page-title"><i class="fa fa-plus-square-o"></i> 新增用户</h4>
            </div>
            <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                <ol class="breadcrumb">
                    <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                    <li class="active">新增用户</li>
                </ol>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- Main content -->
        <div class="content container-fluid">
            <!-- white-box -->
            <div class="white-box">
                <form id="form-user" class="form-horizontal">
                    <div class="form-group">
                        <div class="col-xs-6">
                            <label for="loginName" class="col-sm-2 control-label">账号</label>
                            <div class="col-sm-10">
                                <input name="userReVo.name" type="text" class="form-control" id="loginName" placeholder="请输入账号">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-6">
                            <label for="password" class="col-sm-2 control-label">密码</label>
                            <div class="col-sm-10">
                                <input name="userReVo.password" type="text" class="form-control" id="password" placeholder="请输入密码">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-6">
                            <label for="email" class="col-sm-2 control-label">邮箱</label>
                            <div class="col-sm-10">
                                <input name="userReVo.email" type="text" class="form-control" id="email" placeholder="请输入邮箱">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-6">
                            <label for="email" class="col-sm-2 control-label">角色</label>
                            <div class="col-sm-10">
                                <div class="checkbox">
                                    <#if createUserRsVo??>
                                        <#list createUserRsVo['roleList'] as role>
                                           <label><input type="checkbox" name="userReVo.roles" value="${role['id']!''}"/>${role['name']!''}</label>
                                        </#list>
                                    </#if>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group m-b-0">
                        <div class="col-xs-6">
                            <div class="col-sm-10 col-md-offset-2">
                                <input id ="save-user" type="button" class="btn btn-primary btn-sm btn-flat" value="保 存" />
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
    <footer class="main-footer">
        <!-- To the right -->
        <div class="pull-right hidden-xs">
            Anything you want
        </div>
        <!-- Default to the left -->
        <strong>Copyright &copy; 2016 <a href="#">Company</a>.</strong> All rights reserved.
    </footer>

    <!-- Control Sidebar -->
    <aside class="control-sidebar control-sidebar-dark">
        <!-- Create the tabs -->
        <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
            <li class="active"><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a></li>
            <li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
        </ul>
        <!-- Tab panes -->
        <div class="tab-content">
            <!-- Home tab content -->
            <div class="tab-pane active" id="control-sidebar-home-tab">
                <h3 class="control-sidebar-heading">Recent Activity</h3>
                <ul class="control-sidebar-menu">
                    <li>
                        <a href="javascript:;">
                            <i class="menu-icon fa fa-birthday-cake bg-red"></i>

                            <div class="menu-info">
                                <h4 class="control-sidebar-subheading">Langdon's Birthday</h4>

                                <p>Will be 23 on April 24th</p>
                            </div>
                        </a>
                    </li>
                </ul>
                <!-- /.control-sidebar-menu -->

                <h3 class="control-sidebar-heading">Tasks Progress</h3>
                <ul class="control-sidebar-menu">
                    <li>
                        <a href="javascript:;">
                            <h4 class="control-sidebar-subheading">
                                Custom Template Design
                                <span class="pull-right-container">
                    <span class="label label-danger pull-right">70%</span>
                  </span>
                            </h4>

                            <div class="progress progress-xxs">
                                <div class="progress-bar progress-bar-danger" style="width: 70%"></div>
                            </div>
                        </a>
                    </li>
                </ul>
                <!-- /.control-sidebar-menu -->

            </div>
            <!-- /.tab-pane -->
            <!-- Stats tab content -->
            <div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab Content</div>
            <!-- /.tab-pane -->
            <!-- Settings tab content -->
            <div class="tab-pane" id="control-sidebar-settings-tab">
                <form method="post">
                    <h3 class="control-sidebar-heading">General Settings</h3>

                    <div class="form-group">
                        <label class="control-sidebar-subheading">
                            Report panel usage
                            <input type="checkbox" class="pull-right" checked>
                        </label>

                        <p>
                            Some information about this general settings option
                        </p>
                    </div>
                    <!-- /.form-group -->
                </form>
            </div>
            <!-- /.tab-pane -->
        </div>
    </aside>
    <!-- /.control-sidebar -->
    <!-- Add the sidebar's background. This div must be placed
    immediately after the control sidebar -->
    <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<!-- REQUIRED JS SCRIPTS -->

<!-- jQuery 3 -->
<script src="/design/static/js/jQuery-2.2.0.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="/design/static/plugins/admin-lte/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="/design/static/plugins/admin-lte/dist/js/adminlte.min.js"></script>
<script src="/design/static/js/save-form.js"></script>
<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. -->
</body>
</html>