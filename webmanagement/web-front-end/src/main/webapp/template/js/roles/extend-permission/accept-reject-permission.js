$(".sweet-accept").click(function () {
    let exPermissionId = this.id;
    let count = $('#count' + exPermissionId).val();
    let operatorId = $('#operator' + exPermissionId).val();
    swal({
            title: "Xác nhận thêm ?",
            text: "Bạn có muốn thêm quyền này cho người dùng !!",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#26ddaf",
            confirmButtonText: "Có, thêm vào !!",
            closeOnConfirm: false,
            showLoaderOnConfirm: true,
        },
        function () {
            $.ajax({
                url: "/ajax-extend-permission.html",
                type: "post",
                data: {
                    urlType: "url_accept",
                    exPermissionId: exPermissionId,
                    count: count,
                    operatorId: operatorId,
                    tab: 1
                },
                success: function (data) {
                    $(location).attr('href', '/manager-extend-permission.html?tab=1');
                    swal.close();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(textStatus, errorThrown);
                }
            });
        });
});

$(".sweet-reject").click(function () {
    let exPermissionId = this.id;
    swal({
            title: "Xác nhận xóa ?",
            text: "Từ chối cấp quyền này  !!",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Có, từ chối !!",
            closeOnConfirm: false,
            showLoaderOnConfirm: true,
        },
        function () {
            $.ajax({
                url: "/ajax-extend-permission.html",
                type: "post",
                data: {
                    urlType: "url_reject",
                    exPermissionId: exPermissionId,
                    tab: 1
                },
                success: function (data) {
                    $(location).attr('href', '/manager-extend-permission.html?tab=1');
                    swal.close();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(textStatus, errorThrown);
                }
            });
        });
});

$(".sweet-cancel").click(function () {
    let exPermissionId = this.id;
    swal({
            title: "Xác nhận hủy ?",
            text: "Hủy cấp quyền này  !!",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Có, hủy !!",
            closeOnConfirm: false,
            showLoaderOnConfirm: true,
        },
        function () {
            $.ajax({
                url: "/ajax-extend-permission.html",
                type: "post",
                data: {
                    urlType: "url_cancel",
                    exPermissionId: exPermissionId,
                    tab: 2
                },
                success: function (data) {
                    $(location).attr('href', '/manager-extend-permission.html?tab=2');
                    swal.close();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(textStatus, errorThrown);
                }
            });
        });
});


