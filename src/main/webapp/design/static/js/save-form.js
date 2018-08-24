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
});


function saveMenu() {
    $("#save-menu").click(function() {
        var params = {};
        //类型
        var menuType = $('input[name="saveMenuReVo.type"]');
        menuType.each(function() {
            var menuChecked = this.checked;
            if (menuChecked) {
                var typeKey = $(this).attr("name");
                var typeValue = true;
                putParams(params, typeKey, typeValue);
            }
        });
        //菜单名称
        putInput(params, "saveMenuReVo.name");
        //上级菜单
        // putInput(params, "saveMenuReVo.parentId");
        // var parentMenu = $('select[name="saveMenuReVo.parentId"]');
        // var menuNameKey = 'saveMenuReVo.parentName';
        // var menuNameValue = parentMenu.find("option:selected").text();
        // putParams(params, menuNameKey, menuNameValue);
        //菜单URL
        putInput(params, "saveMenuReVo.url");
        //授权
        putInput(params, "saveMenuReVo.perms");
        //排序
        putInput(params, "saveMenuReVo.orderNum");
        //图标
        putInput(params, "saveMenuReVo.icon");
        console.log(params);
        $.ajax({
            url : "/admin/saveMenu",
            data : params,
            type : 'post',
            success : function ( result ) {
                console.log("Show response success" + result);
                if (result && result == "success") {
                    window.location.href = "/admin/menuManagement";
                } else {
                    alert(result.trim());
                }
            },
            error : function () {
                alert('Save menu error');
            }
        });
    });
}

function putInput(params, name) {
    var nameKey = 'input[name="' + name + '"]';
    var obj = $(nameKey);
    if (obj.length < 0) {
        return;
    }
    var key = obj.attr("name");
    var value = obj.val();
    putParams(params, key, value);
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