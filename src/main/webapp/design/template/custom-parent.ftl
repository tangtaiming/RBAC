<#assign parentId=""/>
<#assign parentName=""/>
<#if entity?? && entity['parentId']??>
    <#assign parentId=entity['parentId']/>
</#if>
<#if entity?? && entity['parentName']??>
    <#assign parentName=entity['parentName']/>
</#if>
<input id="${edit.classesName}_parentId" name="${edit.classesName}.parentId" type="hidden" class="form-control" value="${parentId}">
<input id="${edit.classesName}_parentName" name="${edit.classesName}.parentName" type="text" readonly="readonly" style="cursor:pointer" class="form-control" value="${parentName}">