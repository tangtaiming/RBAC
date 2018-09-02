(function( $ ) {

    var methods = {
        /**
         * 删除数据
         * @param uri
         * @param delId
         */
        del : function (uri, delId) {
            var datas = {};
            datas['id'] = delId;
            $.get(uri, datas, function (data, status) {
                if ('success' == status) {
                    alert('delete ' + delId + " success");
                }
            }, 'json');
        }
    }

    $.fn.list = function( method ) {
        //Method calling logic
        if ( methods[method]) {
            return methods[ method ].apply(this, Array.prototype.slice.call( arguments, 1 ))
        } else if ( typeof method === 'object' || ! method ) {
            return methods.init.apply( this, arguments);
        } else {
            $.error( 'Method' + method + ' does not exist on jQuery.loginPlugin');
        }
    };
})( jQuery );