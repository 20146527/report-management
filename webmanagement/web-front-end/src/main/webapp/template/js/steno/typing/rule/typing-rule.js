function checkFirst() {
    let type = parseInt($(".modal-type").text());
    let first = "";
    let id_label = "value-error-steno";
    let id_input = "steno-value";

    switch (type) {
        case 1:
            first = oder.substring(0, 6);
            break;
        case 2:
            first = oder.substring(6, 17);
            break;
        case 3:
            first = oder.substring(17, oder.length);
            break;
    }
    let input = $("#" + id_input);
    let label = $("#" + id_label);
    let value = input.val().toUpperCase();

    if (check(value)) {
        let checker = 0;
        if (value.length < 1)
            checker = 1;
        for (let i = 0; i < value.length; i++) {
            let data = value.charAt(i);
            if (first.indexOf(data) > -1) {
                first = first.substring(first.indexOf(data) + 1, first.length);
            } else {
                checker = 2;
                break;
            }
        }


        if (checker === 0) {
            label.css({"display": "none"});
        } else if (checker === 1) {
            label.css({"display": "block"});
            label.text("* Không được bỏ trống giá trị");
        } else if (checker === 2) {
            label.css({"display": "block"});
            label.text("* Tồn tại ký tự không được cho phép");
        }
    } else {
        label.css({"display": "block"});
        label.text("* Không được tồn tại khoảng trắng");
    }


}

let check = function (string) {
    return string.indexOf(' ') === -1;
};

$(document).ready(function () {
    $("#table-list-first").DataTable({
        "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]]
    });

    $("#table-list-main").DataTable({
        "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]]
    });

    $("#table-list-last").DataTable({
        "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]]
    });
});

function openModalEit(type, event, id) {
    let modal = $("#edit-steno-rule");

    let suggestions = "";
    switch (type) {
        case 1:
            suggestions = '<h5>Thứ tự và các từ hợp lệ: <span style="font-style: italic; color: red">' + oder.substring(0, 6) + '</span></h5>';
            break;
        case 2:
            suggestions = '<h5>Thứ tự và các từ hợp lệ: <span style="font-style: italic; color: red">' + oder.substring(6, 17) + '</span>';
            break;
        case 3:
            suggestions = '<h5>Thứ tự và các từ hợp lệ: <span style="font-style: italic; color: red">' + oder.substring(17, oder.length) + '</span>';
            break;
    }

    let suggestions_elem = $("#content-input-suggestions");
    suggestions_elem.empty();
    suggestions_elem.append(suggestions);

    let code_steno = $(event).closest("tr").find(".value-key-steno").text();
    let word = $(event).closest("tr").find(".value-word").text();

    $("#value-code").val(word);
    $("#steno-value").val(code_steno);

    $(".modal-key").val(code_steno);
    $(".modal-type").text(type);
    $(".modal-idCode").text(id);
    let label = $("#value-error-steno");
    label.css({"display": "none"});

    modal.modal('toggle');
}

function updateRule(id) {
    let modal = $("#edit-steno-rule");
    let type = parseInt($(".modal-type").text());
    let id_label = "value-error-steno";
    let label = $("#" + id_label);
    let key = $("#steno-value").val().toUpperCase();
    let old_key = $(".modal-key").val();
    if (key === old_key) {
        label.css({"display": "block"});
        label.text("* Không có thay đổi gì!!!");
    } else {
        let idCode = $(".modal-idCode").text();
        modal.modal("toggle");
        swal({
                title: "Thông báo !",
                text: "Việc thay đổi có thể khiến bộ luật hoạt động không chính xác!\nBạn có muốn tiếp tục???",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "Có, tiếp tục !!",
                closeOnConfirm: false,
                showLoaderOnConfirm: true,
            },
            function () {
                $.ajax({
                    url: "/typing-detail.html",
                    type: "post",
                    data: {
                        action: "update",
                        type: type,
                        idCode: idCode,
                        id: id,
                        key: key
                    },
                    success: function () {
                        swal({
                                title: "Thông báo",
                                text: "Dữ liệu đã được cập nhật!!!",
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
}

function disableRule(type, idCode, idRule) {
    swal({
            title: "Thông báo !",
            text: "Việc thay đổi có thể khiến bộ luật hoạt động không chính xác!\nBạn có muốn tiếp tục???",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Có, tiếp tục !!",
            closeOnConfirm: false,
            showLoaderOnConfirm: true,
        },
        function () {
            $.ajax({
                url: "/typing-detail.html",
                type: "post",
                data: {
                    action: "toggle",
                    type: type,
                    idCode: idCode,
                    id: idRule,
                },
                success: function () {
                    swal({
                            title: "Thông báo",
                            text: "Dữ liệu đã được cập nhật!!!",
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

function deleteRule(type, idCode, idRule) {
    swal({
            title: "Thông báo !",
            text: "Việc thay đổi có thể khiến bộ luật hoạt động không chính xác!\nBạn có muốn tiếp tục???",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Có, tiếp tục !!",
            closeOnConfirm: false,
            showLoaderOnConfirm: true,
        },
        function () {
            $.ajax({
                url: "/typing-detail.html",
                type: "post",
                data: {
                    action: "delete",
                    type: type,
                    idCode: idCode,
                    id: idRule,
                },
                success: function () {
                    swal({
                            title: "Thông báo",
                            text: "Dữ liệu đã được cập nhật!!!",
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