(function( $ ) {

    var methods = {
        /**
         * 切换类型
         */
        cutoverType: function() {
            var typeElement = $('input[id="saveMenuReVo_type"]');
            var choseTypeValue;
            typeElement.each(function() {
                var typeElementThis = $(this);
                var typeValue = typeElementThis.val();
                if (typeElementThis.get(0).checked) {
                    choseTypeValue = typeValue;
                    return false;
                }
            });
            $('#group_saveMenuReVo_parentId').show();
            $('#group_saveMenuReVo_url').show();
            if (choseTypeValue == "0") {
                //目录
                $('#group_saveMenuReVo_parentId').hide();
                $('#group_saveMenuReVo_url').hide();
            }
        },
        /**
         * 请求加载tree 树
         */
        initTree: function () {
            var params = {};
            params['id'] = -1;
            $.ajax({
                url: '/admin/fetchMenuByParentId',
                type: 'POST',
                data: params,
                success: function ( response ) {
                    if (!("" == response)) {
                        var treeObj = $("#ztreeMenu");
                        console.log(response);
                        var ztree = $.fn.zTree.init(treeObj, zrteeSetting, JSON.parse(response));
                    }
                },
                error: function() {
                    $.fn.dialog('error');
                }
            });
        },
        // /**
        //  * 选中菜单
        //  * @param event
        //  * @param treeId
        //  * @param treeNode
        //  */
        // choseMenu: function (event, treeId, treeNode) {
        //     alert(treeNode ? treeNode.tId + ", " + treeNode.name : "isRoot");
        //
        // }
    }

    $.fn.menu = function( method ) {
        // Method calling logic
        if ( methods[method] ) {
            return methods[ method ].apply( this, Array.prototype.slice.call( arguments, 1 ));
        } else if ( typeof method === 'object' || ! method ) {
            return methods.init.apply( this, arguments );
        } else {
            $.error( 'Method ' +  method + ' does not exist on jQuery.menu' );
        }
    }
})( jQuery )