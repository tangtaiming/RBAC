<ul id="ztreeMenu" style="margin-left:10px" class="ztree"></ul>
<script type="text/javascript">
    <#if entity?? && (entity['menuJson']??)>
        var resultJson = '${entity["menuJson"]}';
        $.fn.menu('initzTree', '#ztreeMenu', resultJson);
    </#if>
</script>