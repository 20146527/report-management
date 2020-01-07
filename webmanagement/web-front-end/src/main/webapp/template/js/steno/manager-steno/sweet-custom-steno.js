function fnDelete(data, id) {
    console.log(id);
    swal({
            title: "Thông báo ?",
            text: "Bạn có muốn xóa file này??",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Có, xóa bỏ !!",
            closeOnConfirm: false,
            showLoaderOnConfirm: true,
        },
        function () {
            $.ajax({
                url: "/steno-session-list-file.html",
                type: "post",
                data: {
                    type: "delete",
                    path: data
                },
                success: function () {
                    swal.close();
                    location.reload(true);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(textStatus, errorThrown);
                }
            });
        });
}