function saveRoleEntity(url) {
    var params = $('#entity-form').serialize();
    //获取对应树的功能
    var treeObj = $.fn.zTree.getZTreeObj("ztreeMenu");
    var node = treeObj.getCheckedNodes(true);

    var menuIdList = '';
    $.each(node, function(i, x) {
        console.log('id: ' + x['id'] + " name: " + x['name'] + " check: " + x['checked']);
        menuIdList += '&' + 'saveRoleReVo.menuIdList=' + x['id'];
    });
    params += menuIdList;
    //console.log("param: " + JSON.stringify(params));
    $.fn.myAjax('saveEntityFrom', url, params);
}