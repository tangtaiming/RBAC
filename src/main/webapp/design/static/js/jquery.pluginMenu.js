(function( $ ) {
    var param = {
        rootId:0,
        rootName:'一级菜单'
    }

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
            // $('#group_saveMenuReVo_parentId').show();
            $('#group_saveMenuReVo_url').show();
            // $('#saveMenuReVo_parentId').val(param.rootId);
            // $('#saveMenuReVo_parentName').val(param.rootName);
            if (choseTypeValue == "0") {
                //目录
                // $('#group_saveMenuReVo_parentId').hide();
                $('#group_saveMenuReVo_url').hide();
                // $('#saveMenuReVo_parentId').val('');
                // $('#saveMenuReVo_parentName').val('');
            }
        },
        /**
         * 请求加载tree 树
         */
        initTree: function (index) {
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
                        var ztreeObj = $.fn.zTree.init(treeObj, zrteeSetting, JSON.parse(response));
                        //确定按钮绑定一个事件
                        $("#ztreeButton").bind("click", ztreeObj, function(){
                            //确定类别之后 关闭页面
                            layer.close(index);
                        });
                    }
                },
                error: function() {
                    $.fn.dialog('error');
                }
            });
        },
        tree: function () {
            var ztreeHtml = '<ul id="ztreeMenu" style="margin-left:10px" class="ztree"></ul>';
            ztreeHtml += '<div class="layui-layer-btn layui-layer-btn-"><input id="ztreeButton" type="button" class="btn btn-primary btn-sm btn-flat" value="确定" /></div>'
            //页面层
            layer.open({
                type: 1,
                skin: 'layui-layer-rim', //加上边框
                area: ['200px', '340px'], //宽高
                content: ztreeHtml,
                success: function(layero, index){
                    $.fn.menu('initTree', index);
                }
            });
        },
        initzTree: function (zTreeId , menuJson) {
            $.fn.zTree.init($(zTreeId), zrteeSetting, JSON.parse(menuJson));
        }
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