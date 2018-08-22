$(document).ready(function () {
    /**
     * 保存角色
     */
    saveRole();
    saveUser();
    saveAccess();
    saveSiteAccess();
});

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
    $("#access-save").click(function () {
        var role = $('#access-form').serialize();
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