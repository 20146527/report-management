$(".sweet-confirm").click(function () {
    let objectId = this.id;
    let roleId = $(".ver-tab.nav-link.active").attr("id");
    let operatorId = $('#operator' + objectId).val();
    swal({
            title: "Xác nhận thêm ?",
            text: "Bạn có muốn thêm Permission vào quyền !!",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#26ddaf",
            confirmButtonText: "Có, thêm vào !!",
            closeOnConfirm: false,
            showLoaderOnConfirm: true,
        },
        function () {
            $.ajax({
                url: "/ajax-manager-permission.html",
                type: "post",
                data: {
                    urlType: "url_create",
                    tab: roleId,
                    objectId: objectId,
                    operatorId: operatorId
                },
                success: function () {
                    initTable();
                    swal.close();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(textStatus, errorThrown);
                }
            });
        });
});

$(".sweet-delete").click(function () {
    let rolePermissionId = this.id;
    swal({
            title: "Xác nhận xóa ?",
            text: "Bạn có muốn xóa Permission khỏi quyền  !!",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Có, xóa bỏ !!",
            closeOnConfirm: false,
            showLoaderOnConfirm: true,
        },
        function () {
            $.ajax({
                url: "/ajax-manager-permission.html",
                type: "post",
                data: {
                    urlType: "url_delete",
                    rolePermissionId: rolePermissionId
                },
                success: function () {
                    initTable();
                    swal.close();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(textStatus, errorThrown);
                }
            });
        });
});



