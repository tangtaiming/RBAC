$(document).ready(function () {
    /**
     * 保存角色
     */
    saveRole();
    saveUser();
});

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
        var role = $('#role-form').serialize();
        console.log(role);
        $.ajax({
            url : "/role/saveRole",
            data : role,
            type : 'post',
            success : function ( result ) {
                console.log("Show response success" + result);
                if (result && result == "success") {
                    window.location.href = "/role/roleManagement";
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