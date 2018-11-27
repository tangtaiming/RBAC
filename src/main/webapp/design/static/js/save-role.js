function saveRoleEntity(url) {
    var params = $('#entity-form').serialize();
    $.fn.myAjax('saveEntityFrom', url, params);
}