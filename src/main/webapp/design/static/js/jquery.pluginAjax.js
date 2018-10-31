(function( $ ) {
    var methods = {
        /**
         * 请求显示数据
         * @param uri
         * @param params
         */
        list: function (uri, params) {
            //显示加载
            var loadIndex = $.fn.dialog('load');
            $.ajax({
                url: uri,
                type: 'POST',
                data: params,
                success: function ( response ) {
                    //隐藏加载
                    $.fn.dialog('close', loadIndex);
                    $('#adminlte-tbody-data-list').html(response);
                },
                error: function () {
                    //隐藏加载
                    $.fn.dialog('close', loadIndex);
                    $.fn.dialog('error');
                }
            });
        }
    }

    $.fn.myAjax = function( method ) {
        // Method calling logic
        if ( methods[method] ) {
            return methods[ method ].apply( this, Array.prototype.slice.call( arguments, 1 ));
        } else if ( typeof method === 'object' || ! method ) {
            return methods.init.apply( this, arguments );
        } else {
            $.error( 'Method ' +  method + ' does not exist on jQuery.myAjax' );
        }
    }
})( jQuery )