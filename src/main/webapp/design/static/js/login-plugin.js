(function( $ ) {
    var methods = {
        //提交表单
        submit : function() {
            //获取对应的账号密码值
            //进行ajax请求
            //接受返回数据并且进行处理
            var loginName, password;
            var inputLoginName = $('input[name="loginName"]');
            var inputPassword = $('input[name="password"]');
            if (inputLoginName.length > 0) {
                loginName = inputLoginName.val();
                if (loginName == '') {
                    alert('账号不能为空!');
                    return;
                }
            } else {
                $.error('Login name not exist on form');
            }
            if (inputPassword.length > 0) {
                password = inputPassword.val();
                if (password == '') {
                    alert('密码不能为空!');
                    return;
                }
            } else {
                $.error('Password not exist on form');
            }
            console.info('Show loginName: ' + loginName + ' password: ' + password);
            var params = {};
            params['userVo.loginName'] = loginName.trim();
            params['userVo.password'] = password.trim();
            $.ajax({
                url:'/admin/doLogin',
                data: params,
                success: function( transcation ) {
                    console.log('success: ' + transcation);
                    var response = JSON.parse(transcation);
                    if (200 == response.code) {
                        window.location = '/home';
                    } else {
                        var msg = response.msg;
                        alert(msg);
                    }
                 },
                error: function() {
                    alert('login error');
                }
            });
        }
    }

    $.fn.loginPlugin = function( method ) {
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