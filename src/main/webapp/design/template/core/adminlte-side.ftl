<aside class="main-sidebar">

    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

        <!-- Sidebar user panel (optional) -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="/design/static/images/avatar/user2-160x160.jpg" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p>${loginName!'UNKNOWN'}</p>
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
            <li class="header">功能菜单</li>
            <#--<#if nav.navAll??>-->
                <#--<#list nav.navAll as side>-->
                    <#--<#list side?keys as sideKey>-->
                    <#--<li class="active treeview">-->
                        <#--<a href="#">-->
                            <#--<i class="fa fa-link"></i>-->
                            <#--<span><@s.text name="${sideKey!''}" /></span>-->
                            <#--<span class="pull-right-container">-->
                                <#--<i class="fa fa-angle-left pull-right"></i>-->
                            <#--</span>-->
                        <#--</a>-->
                        <#--<ul class="treeview-menu">-->
                            <#--<#assign secondSides = side[sideKey] />-->
                            <#--<#list secondSides?keys as secondSideKey>-->
                                <#--<#assign secondSideValue = secondSides[secondSideKey] />-->
                                <#--<#list secondSideValue as thirdSide>-->
                                <#--<li><a href="${thirdSide['link']}"><i class="fa fa-circle-o"></i> <@s.text name="${thirdSide['title']}" /></a></li>-->
                                <#--</#list>-->
                            <#--</#list>-->
                        <#--</ul>-->
                    <#--</li>-->
                    <#--</#list>-->
                <#--</#list>-->
            <#--</#if>-->

            <#if nav.navAll??>
                <#list nav.navAll as firstNav>
                <li class="active treeview">
                    <a href="javascript:void(0);">
                        <i class="${firstNav['icon']!'fa fa-link'}"></i>
                        <span>${firstNav['name']!''}</span>
                        <span class="pull-right-container">
                            <i class="fa fa-angle-left pull-right"></i>
                        </span>
                    </a>
                    <ul class="treeview-menu">
                        <#assign secondNavAll = firstNav['list'] />
                        <#if secondNavAll?? && (secondNavAll?size > 0)>
                            <#list secondNavAll as secondNav>
                                <li>
                                    <a href="${secondNav['url']!'javascript:void(0);'}">
                                        <i class="${secondNav['icon']!'fa fa-circle-o'}"></i> ${secondNav['name']!''}
                                    </a>
                                </li>
                            </#list>
                        </#if>
                    </ul>
                </li>
                </#list>
            </#if>
        </ul>
        <!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
</aside>