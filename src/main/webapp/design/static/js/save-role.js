function saveRoleEntity(url) {
    var params = $('#entity-form').serialize();
    var treeObj = $.fn.zTree.getZTreeObj("ztreeMenu");
    var chose = treeObj.getCheckedNodes(true);
    console.log('chose: ' + JSON.stringify(chose));
    // $.fn.myAjax('saveEntityFrom', url, params);
}