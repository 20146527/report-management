$(".sweet-confirm").click(function () {
    let user_id = this.id;
    let module_id = $(".ver-tab.nav-link.active").attr("id");
    swal({
            title: "Thông báo ?",
            text: "Bạn có muốn thêm người dùng vào module !!",
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
                    data: "user",
                    type: "add",
                    user_id: user_id,
                    module_id: module_id
                },
                success: function () {
                    updateDataUserModule();
                    swal.close();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(textStatus, errorThrown);
                }
            });
        });
});

$(".sweet-delete").click(function () {
    let userModuleId = this.id;
    swal({
            title: "Thông báo ?",
            text: "Bạn có muốn xóa người dùng khỏi module !!",
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
                    data: "user",
                    type: "delete",
                    userModuleId: userModuleId
                },
                success: function () {
                    updateDataUserModule();
                    swal.close();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(textStatus, errorThrown);
                }
            });
        });
});



