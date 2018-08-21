<div class="row">
    <div class="col-sm-12">
        <div class="layui-table-page">
            <#assign linkPages=page.linkPages />
            <#assign pageNumber=page.pageNumber />
            <#assign pageSize=page.pageSize />
            <#assign totalRows=page.totalRows />
            <#assign upRow=page.upRow />
            <#assign downRow=page.downRow />
            <div class="layui-table-page5">
                <div class="layui-box layui-laypage layui-laypage-default">
                    <#assign upDisabledClass='layui-disabled' />
                    <#assign upHref='' />
                    <#if upRow?string("true", "false")=="true">
                        <#assign upDisabledClass='' />
                        <#assign upHref='href="/xxxx/yyyy"' />
                    </#if>
                    <a ${upHref} class="layui-laypage-prev ${upDisabledClass}">
                        <i class="fa fa-angle-left"></i>
                    </a>
                    <#list linkPages as link>
                        <#if link==pageNumber>
                            <span class="layui-laypage-curr">
                                <em class="layui-laypage-em"></em>
                                <em>${link}</em>
                            </span>
                        <#else>
                            <a href="${link}">${link}</a>
                        </#if>
                    </#list>
                    <#assign downDisabledClass='layui-disabled' />
                    <#assign downHref='' />
                    <#if downRow?string("true", "false")=="true">
                        <#assign downDisabledClass='' />
                        <#assign downHref='href="/xxxx/yyyy"' />
                    </#if>
                    <a ${downHref} class="layui-laypage-next ${downDisabledClass}">
                        <i class="fa fa-angle-right"></i>
                    </a>
                    <span class="layui-laypage-skip">
                        到第
                        <input type="text" min="2" value="${pageNumber}" class="layui-input">
                        页
                        <button type="button" class="layui-laypage-btn">确定</button>
                    </span>
                    <span class="layui-laypage-count">共 ${totalRows} 条</span>
                    <span class="layui-laypage-limits">
                        <select lay-ignore="">
                            <#list [10, 20, 30, 40, 50, 60, 70, 80, 90] as pz>
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
        </div>
    </div>
</div><!-- 分页 END -->