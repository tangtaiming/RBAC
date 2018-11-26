<ul id="ztreeMenu" style="margin-left:10px" class="ztree"></ul>
<script type="text/javascript">
    <#if entity?? && (entity['menuJson']??)>
        $.fn.menu('initzTree', '#ztreeMenu', '${entity["menuJson"]}');
    </#if>
</script>