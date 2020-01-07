$(".sweet-confirm").click(function () {
    swal({
            title: "Thông báo",
            text: "Bạn có muốn thay đổi?",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Có, thêm vào !!",
            closeOnConfirm: false,
            showLoaderOnConfirm: true,
        },
        function () {
            fnSave();
        });
});

