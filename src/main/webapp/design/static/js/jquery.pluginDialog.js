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
        },
        /**
         * 请求成功
         * @param response
         * @param callback
         */
        success: function (response, callback) {
            layer.msg(response, {icon: 1}, callback);
        },
        /**
         * 请求失败
         * @param response
         */
        fail: function (response) {
            layer.msg(response, {icon: 2});
        },
        /**
         * 删除
         * @param callBack
         */
        delete: function (callBack) {
            var index = layer.confirm('确定删除!', {icon: 3, title:'警告'}, callBack);
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