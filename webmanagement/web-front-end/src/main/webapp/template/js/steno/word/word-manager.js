$(document).ready(function () {
    $("#table-word-manger").dataTable();

    $('#customFile').change(function () {
        let input = $('#customFile');
        let file = input[0].files[0].name;
        $(".custom-file-label").text(file);
    });

});

let defaultWord = (id) => {
    window.location.href = "/word-manager.html?type=default&id=" + id;
};

let infoWord = (path, name) => {
    let modal = $("#info-word");
    let body = $("#modal-body");
    body.empty();
    $.get(path + name, function (data) {
        let header = ' <div class="table-responsive">\n' +
            '                    <table id="table-word" class="table table-striped table-bordered" style="width:100%">\n' +
            '                        <thead>\n' +
            '                        <tr>\n' +
            '                            <th>STT</th>\n' +
            '                            <th>Âm tiết</th>\n' +
            '                        </tr>\n' +
            '                        </thead>\n' +
            '                        <tbody>';
        let lines = data.split("\n");
        let html = '';
        for (let i = 0, len = lines.length; i < len; i++) {
            let index = i + 1;
            let word = lines[i];
            html = html + '<tr>\n' +
                '                <td>' + index + '</td>\n' +
                '                <td>' + word + '</td>\n' +
                '          </tr>';
        }
        html = header + html + '</table></div>';
        body.html(html);
        $("#table-word").DataTable({
            "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]]
        });
        modal.modal('show');
    });
};


$("#form-upload-word").submit(function (event) {
    event.preventDefault();
    let formData = new FormData();
    formData.append('file', $('#filer_input')[0].files[0]);
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
                url: "/word-manager.html",
                type: "post",
                data: formData,
                contentType: false,
                enctype: 'multipart/form-data',
                processData: false,
                success: function (response) {
                    let error_code = parseInt(response);
                    if (error_code === -1) {
                        swal({
                                title: "Thông báo",
                                text: "Thêm bộ âm tiết thành công",
                                type: "success"
                            },
                            function () {
                                swal.close();
                                location.reload(true);
                            });
                    } else {
                        swal.close();
                        let tile_error = "";
                        if (error_code === 1) {
                            tile_error = "File nhập vào không đúng định dạng vui lòng tải xuống mẫu";
                        } else if (error_code === 2) {
                            tile_error = "Dữ liệu không hợp lệ. Vui lòng kiểm tra lại!";
                        } else {
                            tile_error = "Dữ liệu nhập vào chỉ được phép là âm tiết đơn. Vui lòng kiểm tra lại!";
                        }
                        toastr.error(tile_error, 'Thông báo', {
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
                        });
                    }
                }
            })
        });
});