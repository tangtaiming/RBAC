/**
 * 查询条件 插件
 */
(function( $ ) {
    var methods = {
        /**
         * 构建查询条件
         */
        build: function(uri) {
            var params = {};
            //获取input表单数据
            var inputFilterElement = $('.filter');
            inputFilterElement.each(function() {
                var filterName = $(this).attr("name");
                var filterValue = $(this).val();
                // console.info("filterName: " + filterName + " filterValue: " + filterValue);
                // params[filterName] = filterValue;
                if (!("" == filterValue)) {
                    params[filterName] = filterValue;
                }
            });
            //page 分页条件查询
            var pageNumber = $.fn.page('fetchPageNumber');
            var pageSize = $.fn.page('fetchPageSize');
            params['pageNumber'] = Number(pageNumber);
            params['pageSize'] = Number(pageSize);

            $.fn.myAjax('list', uri, params);
            // console.info("show params: " + JSON.stringify(params));
        }
    };

    $.fn.myfilter = function( method ) {
        // Method calling logic
        if ( methods[method] ) {
            return methods[ method ].apply( this, Array.prototype.slice.call( arguments, 1 ));
        } else if ( typeof method === 'object' || ! method ) {
            return methods.init.apply( this, arguments );
        } else {
            $.error( 'Method ' +  method + ' does not exist on jQuery.myfilter' );
        }
    }
})( jQuery )