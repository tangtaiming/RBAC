$(document).ready(function() {
    $("input[id='saveMenuReVo_type']").click(function() {
        $.fn.menu('cutoverType');
    });

    //加载树
    $.fn.menu('initTree');
    //默认勾选
    $('input[id="saveMenuReVo_type"]').get(1).checked = true;

    // var treeObj = $("#ztreeMenu");
    // var treeValue = $('#tree_json').val();
    // console.log(treeValue);
    // var ztreeObj = $.fn.zTree.init(treeObj, zrteeSetting, JSON.parse(treeValue));
});

function choseMenu(event, treeId, treeNode) {
    // alert(treeNode ? treeNode.id + ", " + treeNode.name : "isRoot");
    if (!(treeId.id == 0)) {
        $('#saveMenuReVo_parentId').val(treeNode.id)
    } else {
        $('#saveMenuReVo_parentId').val('')
    }
}
