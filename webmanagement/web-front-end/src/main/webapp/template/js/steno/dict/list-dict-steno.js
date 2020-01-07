$(document).ready(function () {
    $("#table-person-dict").DataTable({
        "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]]
    });
});

$(document).ready(function () {
    $('#steno-content').DataTable({
        "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]]
    });

});

$(document).ready(function () {
    $('#modalCart').on('hidden', function () {
        clear();
    });
});

function redirectFunction(type, elem) {
    let name = $(elem).closest("tr").find(".name-dict").text();
    let data = $.base64.encode(name);
    window.location.href = "manager-dict-steno-list.html?type=" + type + "&name=" + data;
}

function fnDelete(type, elem) {
    let name = $(elem).closest("tr").find(".name-dict").text();
    let data = $.base64.encode(name);

    swal({
            title: "Thông báo ?",
            text: "Bạn có muốn xóa tệp tin này???",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Có, xóa bỏ !!",
            closeOnConfirm: false,
            showLoaderOnConfirm: true,
        },
        function () {
            $.ajax({
                url: "/manager-dict-steno-list.html",
                type: "get",
                data: {
                    type: type,
                    name: data
                },
                success: function () {
                    swal({
                            title: "Thông báo",
                            text: "Dữ liệu đã được xóa bỏ!!!",
                            type: "success",
                        },
                        function () {
                            swal.close();
                            location.reload(true);
                        });
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(textStatus, errorThrown);
                }
            });
        });
}


function downloadDict(name) {
    window.location.href = "/manager-dict-steno-list.html?data=download&nameFile=" + name + ".json";
}

function createDict() {
    swal({
            title: "Thông báo ?",
            text: "Bạn có muốn tiếp tục",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Có, tiếp tuc !!",
            closeOnConfirm: false,
            showLoaderOnConfirm: true,
        },
        function () {
            $.ajax({
                url: "/manager-dict-steno-list.html",
                type: "post",
                data: {
                    type: "createDict"
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

$("#form-upload-dict").submit(function (event) {
    event.preventDefault();
    let bool = true;
    let formData = new FormData();
    let name_file = "empty";
    formData.append('file', $('#filer_input')[0].files[0]);
    let isDisabled = $('#dictName').prop('disabled');
    if (!isDisabled) {
        name_file = $('#dictName').val();
        if ($.trim(name_file) === "") {
            bool = false;
        }
    }
    let isDefault = $('#checkDictDefault').is(':checked');
    if (bool) {
        swal({
                title: "Thông báo ?",
                text: "Bạn có muốn tiếp tục",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "Có, tiếp tuc !!",
                closeOnConfirm: false,
                showLoaderOnConfirm: true,
            },
            function () {
                $.ajax({
                    url: "/manager-dict-steno-list.html?type=upload&name_file=" + name_file + "&isDefault=" + isDefault,
                    type: "post",
                    data: formData,
                    contentType: false,
                    enctype: 'multipart/form-data',
                    processData: false,
                    success: function (response) {
                        if (parseInt(response) === -1) {
                            swal.close();
                            toastr.error('File nhập vào không chính xác, vui lòng kiểm tra lại!', 'Thông báo', {
                                timeOut: 5000,
                                "closeButton": true,
                                "debug": false,
                                "newestOnTop": true,
                                "progressBar": true,
                                "positionClass": "toast-top-right",
                                "preventDuplicates": true,
                                "onclick": null,
                                "showDuration": "300",
                                "hideDuration": "1000",
                                "extendedTimeOut": "1000",
                                "showEasing": "swing",
                                "hideEasing": "linear",
                                "showMethod": "fadeIn",
                                "hideMethod": "fadeOut",
                                "tapToDismiss": false
                            })
                        } else {
                            swal({
                                    title: "Thông báo",
                                    text: "Thêm bộ từ điển thành công",
                                    type: "success"
                                },
                                function () {
                                    swal.close();
                                    location.reload(true);
                                });
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.log(textStatus, errorThrown);
                    }
                });
            });
    } else {
        swal({
                title: "Thông báo",
                text: "Không được bỏ trống các trường dữ liệu",
                type: "warning",
                showLoaderOnConfirm: false
            },
            function () {
                swal.close();
            });
    }
});

