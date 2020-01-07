$(".sweet-confirm-object").click(function () {
    let object_id = this.id;
    let module_id = $(".ver-tab.nav-link.active").attr("id");
    swal({
            title: "Thông báo ?",
            text: "Bạn có muốn thêm chức năng này vào module !!",
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
                    data: "object",
                    type: "add",
                    object_id: object_id,
                    module_id: module_id
                },
                success: function () {
                    updateDataObjectModule();
                    swal.close();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(textStatus, errorThrown);
                }
            });
        });
});

$(".sweet-delete-object").click(function () {
    let object_id = this.id;
    let module_id = $(".ver-tab.nav-link.active").attr("id");
    swal({
            title: "Thông báo ?",
            text: "Bạn có muốn xóa chức năng này khỏi module !!",
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
                    data: "object",
                    type: "delete",
                    object_id: object_id,
                    module_id: module_id
                },
                success: function () {
                    updateDataObjectModule();
                    swal.close();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(textStatus, errorThrown);
                }
            });
        });
});

