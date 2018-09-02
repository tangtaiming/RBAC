(function( $ ) {

    var methods = {
        /**
         * 删除数据
         * @param uri
         * @param delId
         */
        del : function (uri, delId) {
            if (!confirm("[警告] 确认删除ID: " + delId + " 数据")) {
                return;
            }
            var datas = {};
            datas['id'] = delId;
            $.get(uri, datas, function (data, status) {
                if ('success' == status) {
                    var parseData = JSON.parse(data);
                    if (200 == parseData.code){
                        alert("ID: " + delId + " " + parseData.message);
                        location.reload(true);
                    } else {
                        alert("ID: " + delId + " " + parseData.message);
                    }
                }
            });
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