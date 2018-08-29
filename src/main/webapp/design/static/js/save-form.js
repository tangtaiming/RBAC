var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "menuId",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url:"nourl"
        }
    }
}

$(document).ready(function () {
    /**
     * 保存角色
     */
    saveRole();
    saveUser();
    saveAccess();
    saveSiteAccess();
    saveMenu();
    /**
     * 菜单操作
     */
    $('input[name="saveMenuReVo.type"]').click(function() {
        var type = $(this).val();
        $('#name-group').show();
        $('#orderNum-group').show();
        if (type == "0") {
            $('#icon-group').show();
            $('#url-group').hide();
            $('#perms-group').hide();
        } else if (type == '1') {
            $('#icon-group').show();
            $('#url-group').show();
            $('#perms-group').show();
        } else {
            $('#icon-group').hide();
            $('#url-group').show();
            $('#perms-group').hide();
        }
    });
});


function saveMenu() {
    $("#save-menu").click(function() {
        var typeZero = "0";
        var typeOne = "1";
        var typeTwo = "2";
        var params = {};
        //类型
        var menuType = $('input[name="saveMenuReVo.type"]');
        var typeValue, typeKey;
            menuType.each(function() {
            var menuChecked = this.checked;
            if (menuChecked) {
                typeKey = $(this).attr("name");
                typeValue = $(this).val();
                putParams(params, typeKey, typeValue);
            }
        });
        putInput(params, "saveMenuReVo.menuId");
        //菜单名称
        putInput(params, "saveMenuReVo.name");
        //上级菜单
        putInputById(params, "parentId");
        putInputById(params, "parentName");
        if (typeOne == typeValue || typeTwo == typeValue) {
            //菜单URL
            putInput(params, "saveMenuReVo.url");
        }
        if (typeOne == typeValue) {
            //授权
            putInput(params, "saveMenuReVo.perms");
        }
        //排序
        putInput(params, "saveMenuReVo.orderNum");
        if (typeZero == typeValue || typeOne == typeValue) {
            //图标
            putInput(params, "saveMenuReVo.icon");
        }
        console.log(params);
        // $.ajax({
        //     url : "/admin/saveMenu",
        //     data : params,
        //     type : 'post',
        //     success : function ( result ) {
        //         console.log("Show response success" + result);
        //         if (result && result == "success") {
        //             window.location.href = "/admin/menuManagement";
        //         } else {
        //             alert(result.trim());
        //         }
        //     },
        //     error : function () {
        //         alert('Save menu error');
        //     }
        // });
    });
}

function putInputById(params, id) {
    var nameKey = '#' + id;
    var obj = $(nameKey);
    if (obj.length < 0) {
        return;
    }
    var key = obj.attr("name");
    var value = obj.val();
    putParams(params, key, value);
}

function putInput(params, name) {
    var nameKey = 'input[name="' + name + '"]';
    var obj = $(nameKey);
    if (obj.length < 0) {
        return;
    }
    var key = obj.attr("name");
    var value = obj.val();
    if (!(null == value) && !("" == value.trim())) {
        putParams(params, key, value);
    }
}

function putParams(params, key, value) {
    params[key] = value;
}

function saveSiteAccess() {
    $("#save-site-access").click(function () {
        var role = $('#form-site-access').serialize();
        console.log(role);
        $.ajax({
            url : "/admin/saveSiteAccess",
            data : role,
            type : 'post',
            success : function ( result ) {
                console.log("Show response success" + result);
                if (result && result == "success") {
                    window.location.href = "/admin/roleManagement";
                } else {
                    alert(result.trim());
                }
            },
            error : function () {
                alert('Save user error');
            }
        });
    });
}

function saveAccess() {
    $("#save-access").click(function () {
        var role = $('#form-access').serialize();
        console.log(role);
        $.ajax({
            url : "/admin/saveAccess",
            data : role,
            type : 'post',
            success : function ( result ) {
                console.log("Show response success" + result);
                if (result && result == "success") {
                    window.location.href = "/admin/accessManagement";
                } else {
                    alert(result.trim());
                }
            },
            error : function () {
                alert('Save user error');
            }
        });
    });
}

function saveUser() {
    $("#save-user").click(function () {
        var role = $('#form-user').serialize();
        console.log(role);
        $.ajax({
            url : "/admin/saveUser",
            data : role,
            type : 'post',
            success : function ( result ) {
                console.log("Show response success" + result);
                if (result && result == "success") {
                    window.location.href = "/admin/userManagement";
                } else {
                    alert(result.trim());
                }
            },
            error : function () {
                alert('Save user error');
            }
        });
    });
}

function saveRole() {
    $("#save-role").click(function () {
        var role = $('#form-role').serialize();
        console.log(role);
        $.ajax({
            url : "/admin/saveRole",
            data : role,
            type : 'post',
            success : function ( result ) {
                console.log("Show response success" + result);
                if (result && result == "success") {
                    window.location.href = "/admin/roleManagement";
                } else {
                    alert(result.trim());
                }
            },
            error : function () {
                alert('Save role error');
            }
        });
    });
}