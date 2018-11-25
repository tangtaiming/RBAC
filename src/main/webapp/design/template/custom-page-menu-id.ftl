<ul id="ztreeMenu" style="margin-left:10px" class="ztree"></ul>
<script type="text/javascript">
    <#if entity?? && (entity['menuOrmList']?size > 0)>
        initzTree(${entity['menuOrmList']})
    </#if>
</script>