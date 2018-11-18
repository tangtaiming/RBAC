var zrteeSetting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "parentId",
            rootPId: 0
        },
        key: {
            url:"nourl"
        }
    },
    async: {
        enable: true,
        url:"fetchMenuByParentId",
        type: "post",
        autoParam:["id"]
    },
    check: {
        enable: true,
        chkStyle: "radio",
        radioType: "all",
        chkboxType: { "Y" : "ps", "N" : "ps" }
    },
    callback: {
        onCheck: choseMenu
    }
};