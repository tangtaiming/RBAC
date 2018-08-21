<div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
        <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
        <ul class="layui-nav layui-nav-tree"  lay-filter="test">
            <#if nav.navAll??>
                <#list nav.navAll as side>
                    <#list side?keys as sideKey>
                    <li class="layui-nav-item layui-nav-itemed">
                        <a class="" href="javascript:;"><@s.text name="${sideKey!''}" /></a>
                        <dl class="layui-nav-child">
                            <#assign secondSides = side[sideKey] />
                            <#list secondSides?keys as secondSideKey>
                            <dd>
                                <#assign secondSideValue = secondSides[secondSideKey] />
                                <#list secondSideValue as thirdSide>
                                    <a href="${thirdSide['link']}"><@s.text name="${thirdSide['title']}" /></a>
                                </#list>
                            </dd>
                            </#list>
                        </dl>
                    </li>
                    </#list>
                </#list>
            </#if>
        </ul>
    </div>
</div>