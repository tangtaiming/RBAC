/**
 * 对话框 插件
 **/
(function( $ ) {

    var methods = {
        /**
         * ajax 加载
         */
        load: function () {
            return layer.load(2);
        },
        /**
         * 关闭加载
         * @param index
         */
        close: function (index) {
            layer.close(index);
        },
        /**
         * 错误提示
         */
        error: function () {
            layer.msg('ajax error', {icon: 5});
        },
        /**
         * 请求成功
         */
        success: function (response) {
            layer.msg(response, {icon: 1});
        }
    };

    $.fn.dialog = function( method ) {
        // Method calling logic
        if ( methods[method] ) {
            return methods[ method ].apply( this, Array.prototype.slice.call( arguments, 1 ));
        } else if ( typeof method === 'object' || ! method ) {
            return methods.init.apply( this, arguments );
        } else {
            $.error( 'Method ' +  method + ' does not exist on jQuery.dialog' );
        }
    }

})( jQuery );