<div  id="adminlte-page" class="layui-table-page" style="background-color:#ffffff; border-width: 0px;">
    <#assign linkPages=page.linkPages />
    <#assign pageNumber=page.pageNumber />
    <#assign pageSize=page.pageSize />
    <#assign totalRows=page.totalRows />
    <#assign totalPage=page.totalPages />
    <#assign upRow=page.upRow />
    <#assign downRow=page.downRow />
    <div class="layui-table-page5">
        <div class="layui-box layui-laypage layui-laypage-default">
            <#assign upDisabledClass='layui-disabled' />
            <#if upRow?string("true", "false")=="true">
                <#assign upDisabledClass='' />
            </#if>
            <a
                <#if upRow?string("true", "false")=="true">
                    onclick="$.fn.page('pager', '/test/page', ${pageNumber - 1});" href="javascript:void(0);"
                </#if>
                class="layui-laypage-prev ${upDisabledClass}">
                <i class="fa fa-angle-left"></i>
            </a>
            <#list linkPages as link>
                <#if link==pageNumber>
                    <span class="layui-laypage-curr">
                        <em class="layui-laypage-em"></em>
                        <em>${link}</em>
                    </span>
                <#else>
                    <a onclick="$.fn.page('pager', '/test/page', ${link})" href="javascript:void(0);">${link}</a>
                </#if>
            </#list>
            <#assign downDisabledClass='layui-disabled' />
            <#if downRow?string("true", "false")=="true">
                <#assign downDisabledClass='' />
            </#if>
            <a
                <#if downRow?string("true", "false")=="true">
                    onclick="$.fn.page('pager', '/test/page', ${pageNumber + 1});" href="javascript:void(0);"
                </#if>
                    class="layui-laypage-next ${downDisabledClass}">
                <i class="fa fa-angle-right"></i>
            </a>
            <span class="layui-laypage-skip">
                到第
                <input id="adminlte-input-page-number" type="text" value="${pageNumber}" class="layui-input">
                页
                <button id="adminlte-button-page-number" type="button" class="layui-laypage-btn">确定</button>
            </span>
            <span class="layui-laypage-count">共 ${totalPage} 页, 总共 ${totalRows} 条, 每页</span>
            <span class="layui-laypage-limits">
                <select id="adminlte-input-page-size" onchange="$.fn.page('pager', '/test/page', ${pageNumber});">
                    <#list [1, 10, 20, 30, 40, 50, 60, 70, 80, 90] as pz>
                        <#assign selectAttr='' />
                        <#if pageSize==pz>
                            <#assign selectAttr='selected="selected"' />
                        </#if>
                        <option value="${pz}" ${selectAttr}>${pz} 条/页</option>
                    </#list>
                </select>
            </span>
        </div>
    </div>
</div><!-- 分页 END -->