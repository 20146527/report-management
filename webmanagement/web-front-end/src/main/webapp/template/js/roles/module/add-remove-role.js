$(".sweet-confirm-role").click(function () {
    let roleId = this.id;
    let module_id = $(".ver-tab.nav-link.active").attr("id");
    swal({
            title: "Thông báo ?",
            text: "Bạn có muốn thêm vai trò vào module !!",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Có, thêm vào !!",
            closeOnConfirm: false,
            showLoaderOnConfirm: true,
        },
        function () {
            $.ajax({
                url: "/manager-module.html",
                type: "post",
                data: {
                    data: "role",
                    type: "add",
                    roleId: roleId,
                    module_id: module_id
                },
                success: function () {
                    initTableRole();
                    swal.close();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(textStatus, errorThrown);
                }
            });
        });
});

$(".sweet-delete-role").click(function () {
    let roleModuleId = this.id;
    swal({
            title: "Thông báo ?",
            text: "Bạn có muốn xóa vai trò khỏi module !!",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Có, xóa bỏ !!",
            closeOnConfirm: false,
            showLoaderOnConfirm: true,
        },
        function () {
            $.ajax({
                url: "/manager-module.html",
                type: "post",
                data: {
                    data: "role",
                    type: "delete",
                    roleModuleId: roleModuleId
                },
                success: function () {
                    initTableRole();
                    swal.close();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(textStatus, errorThrown);
                }
            });
        });
});



