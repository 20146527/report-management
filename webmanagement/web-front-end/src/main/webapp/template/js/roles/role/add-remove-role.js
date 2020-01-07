$(".sweet-confirm").click(function () {
    let id = this.id;
    swal({
            title: "Xác nhận thêm ?",
            text: "Bạn có muốn thêm vai trò này cho người dùng !!",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#26ddaf",
            confirmButtonText: "Có, thêm vào !!",
            closeOnConfirm: false,
            showLoaderOnConfirm: true,
        },
        function () {
            $.ajax({
                url: "/ajax-user-edit.html",
                type: "post",
                data: {
                    urlType: "ajax_add_role",
                    roleId: id,
                    userId: userId

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
    let userRoleId = this.id;
    swal({
            title: "Xác nhận xóa ?",
            text: "Bạn có muốn xóa vai trò này khỏi người dùng !!",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Có, xóa bỏ !!",
            closeOnConfirm: false,
            showLoaderOnConfirm: true,
        },
        function () {
            $.ajax({
                url: "/ajax-user-edit.html",
                type: "post",
                data: {
                    urlType: "ajax_delete_role",
                    roleId: userRoleId
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



