(function( $ ) {
    var methods = {
        /**
         *	分页ajax请求
         */
        pager : function(url, pageNumber) {
            var params = {};
            var pageSize = $.fn.page('fetchPageSize');
            params['pageNumber'] = pageNumber;
            params['pageSize'] = pageSize;
            alert(JSON.stringify(params));
            $.ajax({
                url: url,
                type: 'POST',
                data: params,
                success: function( response ) {
                    alert(response);
                },
                error: function() {
                    alert('request error!');
                }
            });
        },
        /**
         * 获取每页显示数量
         */
        fetchPageSize : function() {
            var pageSizeElement = $('#adminlte-input-page-size');
            return pageSizeElement.val();
        },
        /**
         * 获取当前页
         */
        fetchPageNumber : function () {
            var pageNumberElement = $('#adminlte-input-page-number');
            return pageNumberElement.val();
        }
    };

    $.fn.page = function( method ) {
        // Method calling logic
        if ( methods[method] ) {
            return methods[ method ].apply( this, Array.prototype.slice.call( arguments, 1 ));
        } else if ( typeof method === 'object' || ! method ) {
            return methods.init.apply( this, arguments );
        } else {
            $.error( 'Method ' +  method + ' does not exist on jQuery.page' );
        }
    }

})( jQuery );