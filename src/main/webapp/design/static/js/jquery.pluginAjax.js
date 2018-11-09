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
        },
        /**
         * 提交表单
         * @param uri
         */
        saveFrom: function(uri) {
            //显示加载
            var loadIndex = $.fn.dialog('load');
            //获取数据
            var params = $('#entity-form').serialize();
            $.ajax({
                url: uri,
                type: 'POST',
                data: params,
                success: function ( response ) {
                    $.fn.dialog('close', loadIndex);
                    if (!("" == response)) {
                        var result = JSON.parse(response);
                        var pcode = result.code;
                        var pmsg = result.msg;
                        if (200 == pcode) {
                            $.fn.dialog('success', pmsg);
                        } else {
                            $.fn.dialog('fail', pmsg);
                        }
                    }

                },
                error: function() {
                    $.fn.dialog('close', loadIndex);
                    $.fn.dialog('error');
                }
            });
        },
        /**
         * 保存并且返回
         * @param uri
         * @param backUri
         */
        saveFromAndBack: function (uri, backUri) {
            //显示加载
            var loadIndex = $.fn.dialog('load');
            //获取数据
            var params = $('#entity-form').serialize();
            $.ajax({
                url: uri,
                type: 'POST',
                data: params,
                success: function ( response ) {
                    $.fn.dialog('close', loadIndex);
                    if (!("" == response)) {
                        var result = JSON.parse(response);
                        var pcode = result.code;
                        var pmsg = result.msg;
                        if (200 == pcode) {
                            $.fn.dialog('success', pmsg, function () {
                                //并且返回
                                window.location.href = backUri;
                            });
                        } else {
                            $.fn.dialog('fail', pmsg);
                        }
                    }
                },
                error: function() {
                    $.fn.dialog('close', loadIndex);
                    $.fn.dialog('error');
                }
            });
        },
        /**
         * 删除数据
         * @param id
         */
        deleteById: function (uri, backUri, id) {
            $.fn.dialog('delete', function(index) {
                //关闭对话框
                $.fn.dialog('close', index);

                //显示加载
                var loadIndex = $.fn.dialog('load');
                var param = {};
                param['id'] = id;
                $.ajax({
                    url: uri,
                    type: 'POST',
                    data: param,
                    success: function ( response ) {
                        $.fn.dialog('close', loadIndex);
                        if (!("" == response)) {
                            var result;
                            try {
                                result = JSON.parse(response);
                            } catch ($e) {
                                $.fn.dialog('fail', '解析结果异常。');
                                console.error(response);
                                return false;
                            }

                            var pcode = result.code;
                            var pmsg = result.msg;
                            if (200 == pcode) {
                                $.fn.dialog('success', pmsg, function () {
                                    //并且返回
                                    window.location.href = backUri;
                                });
                            } else {
                                $.fn.dialog('fail', pmsg);
                            }
                        }
                    },
                    error: function() {
                        $.fn.dialog('close', loadIndex);
                        $.fn.dialog('error');
                    }
                });
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