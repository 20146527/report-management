let btn = $("#btn-submit-dict");

function script_delete_block() {
    return '<script>$(".delete-block").click(function () {\n' +
        '        let data = $(this).attr("id");\n' +
        '        let id = "#add-dict-" + data;\n' +
        '        $(id).remove();\n' +
        '    })</script>';
}

let onChangeDataInput = (path, id) => {
    let value = $("#key-steno-" + id).val().toUpperCase();
    let bool = false;
    if (check(value)) {
        let res = value.split("/");
        $.each(res, function (key, value) {
            if (value.length > 10) {
                display_error(1, id);
                return false;
            } else {
                if (!isPrefix(value)) {
                    let result = checkIfNotPrefix(value);
                    if (!result) {
                        display_error(2, id);
                        bool = false;
                        return bool;
                    } else {
                        bool = true;
                    }
                } else {
                    let result = checkIfPrefix(value);
                    if (!result) {
                        bool = false;
                        display_error(2, id);
                        return bool;
                    } else {
                        bool = true;
                    }
                }
            }
        });
        if (bool) {
            let dict_default;
            let dict_person;
            let dict;
            $.ajax({
                url: "/query.html?type=dict-default",
                type: "post",
                success: function (response) {
                    $.ajax({
                        url: "/file/dict/" + response,
                        async: true,
                        success: function (data) {
                            dict_default = data;
                            $.ajax({
                                url: path,
                                async: true,
                                success: function (data) {
                                    dict_person = data;
                                    dict = $.extend(dict_default, dict_person);
                                    let key_code = Object.keys(dict);
                                    if ($.inArray(value, key_code) >= 0) {
                                        display_error(3, id);
                                    } else {
                                        display_error(0, id);
                                    }
                                    checkDisplay(id);
                                }
                            })
                        }
                    })
                }
            });

        }
    } else {
        display_error(4, id);
        checkDisplay(id);
    }

};

let onchangeInputWord = (path, id) => {
    let notification = $("#label-value-" + id);
    let value = $("#value-" + id).val();
    let dict_default;
    let dict_person;
    let dict;
    $.ajax({
        url: "/query.html?type=dict-default",
        type: "post",
        success: function (response) {
            $.ajax({
                url: "/file/dict/" + response,
                async: true,
                success: function (data) {
                    dict_default = data;
                    $.ajax({
                        url: path,
                        async: true,
                        success: function (data) {
                            dict_person = data;
                            dict = $.extend(dict_default, dict_person);
                            let key_code = Object.values(dict);
                            if ($.inArray(value, key_code) >= 0) {
                                notification.css({"display": "block"});
                                notification.html('<i class="fa fa-exclamation-triangle class-warning" id="word-' + id + '" aria-hidden="true" style="color: yellow"></i><span style="color: yellow"> Từ này đã được thể hiện trong bộ luật</span>')
                            } else {
                                notification.css({"display": "none"});
                                notification.empty();
                            }
                            checkDisplay(id);
                        }
                    })
                }
            })
        }
    });

};

function event_submit_button(number) {
    let number_error = $("#add-new-dict", ".error-dict").prevObject.length;
    let bool = true;
    for (let i = 0; i < number; i++) {
        if (number_error !== 0 || $("#key-steno-" + i).val() === '' || $("#value-" + i).val() === '') {
            bool = false;
            break;
        }
    }
    return bool;
}

function onClickUpdateDict() {
    let number_key = $("#add-new-dict", ".key-steno").prevObject.length;
    if (!event_submit_button(number_key)) {
        $('#notification').modal('show');
    } else {
        let overlay = $("#overlay");
        overlay.css({"display": "block"});
        let json = '';
        for (let i = 0; i < number_key; i++) {
            let key = $("#key-steno-" + i).val();
            let value = $("#value-" + i).val();
            if (i < number_key - 1) {
                json = json + '"' + key + '"' + ':"' + value + '",';
            } else {
                json = json + '"' + key + '"' + ':"' + value + '"';
            }
        }
        json = "{" + json + "}";
        $.ajax({
            url: "/query.html?type=add-new-person-dict",
            type: "post",
            data: {
                dataPersonDict: json
            },
            success: function () {
                overlay.css({"display": "none"});
                location.reload(true);
            }
        });

    }
}

let checkDisplay = (id) => {
    disable_button(id);
    $("#id").className;
    let value = $("#value-" + id).val().toUpperCase();
    let key = $("#key-steno-" + id).val();
    if (key !== '' && value !== '') {
        let m = -1, n = -1, p = -1, q = -1;
        if ($("#key-" + id).length > 0) {
            n = $("#key-" + id).attr("class").indexOf("class-error");
        }

        if ($("#word-" + id).length > 0) {
            m = $("#word-" + id).attr("class").indexOf("class-error");
        }

        if ($("#key-" + id).length > 0) {
            p = $("#key-" + id).attr("class").indexOf("class-warning");
        }

        if ($("#word-" + id).length > 0) {
            q = $("#word-" + id).attr("class").indexOf("class-warning");
        }

        if (n > -1 || m > -1) {
            enable_button(3, id, "Có lỗi xảy ra vui lòng kiểm tra lại");
        } else if (p > -1 || q > -1) {
            enable_button(2, id, "Có một số cảnh báo! Bạn có thể kiểm tra lại");
        } else if (n === -1 && m === -1 && p === -1 && q === -1) {
            enable_button(1, id, null);
        }
    } else {
        enable_button(3, id, "Không được bỏ trống các trường")
    }
};

function disable_button(id) {
    $("#success-" + id).css({"display": "none"});
    $("#warning-" + id).css({"display": "none"});
    let danger = $("#danger-" + id);
    danger.css({"display": "none"});
    danger.removeClass("error-dict");
}

let display_error = (index, id) => {
    let error = $("#label-key-steno-" + id);
    let html = "";
    switch (index) {
        case 0:
            error.css({"display": "none"});
            error.empty();
            break;
        case 1:
            error.css({"display": "block"});
            html = '<i class="fa fa-ban class-error" id="key-' + id + '" aria-hidden="true" style="color: red"></i><span style="color: red"> Độ dài không được vượt quá 10 ký tự</span>';
            break;
        case 2:
            error.css({"display": "block"});
            html = '<i class="fa fa-ban class-error" id="key-' + id + '" aria-hidden="true" style="color: red"></i><span style="color: red"> Tổ hợp phím không đúng</span>';
            break;
        case 3:
            error.css({"display": "block"});
            html = '<i class="fa fa-exclamation-triangle class-warning"  id="key-' + id + '" aria-hidden="true" style="color: yellow"></i><span style="color: yellow"> Tổ hợp phím đã được dùng để thể hiện một âm tiết khác</span>';
            break;
        case 4:
            error.css({"display": "block"});
            html = '<i class="fa fa-ban class-error" aria-hidden="true"  id="key-' + id + '" style="color: red"></i><span style="color: red"> Tồn tại ký tự không được cho phép</span>';
            break;
    }

    error.html(html);
};

function enable_button(index, id, tooltip) {
    switch (index) {
        case 1:
            let success = $("#success-" + id);
            success.css({"display": "block"});
            success.attr("data-tooltip", tooltip);
            break;
        case 2:
            let warning = $("#warning-" + id);
            warning.css({"display": "block"});
            warning.attr("data-tooltip", tooltip);
            break;
        case 3:
            let danger = $("#danger-" + id);
            danger.css({"display": "block"});
            danger.attr("data-tooltip", tooltip);
            danger.addClass("error-dict");
            break;
    }
}


let fullStr = "STKPWHRAO*EUFRPBLGTSDZ";
let subStr = "FRPBLGTSDZ";


function isPrefix(str) {
    return str.charAt(0) === '-';
}

function checkIfNotPrefix(str) {
    let processString = String(fullStr);
    for (i = 0; i < str.length; i++) {
        let j = processString.indexOf(str.charAt(i));
        if (j >= 0) {
            processString = processString.substring(j + 1);
        } else if (j < 0 && str.charAt(i) === '-') {
            processString = processString.substring(1, processString.length);
        } else return false;
    }
    return true;
}


function checkIfPrefix(str) {
    let processString = String(subStr);
    for (let i = 1; i < str.length; i++) {
        let j = processString.indexOf(str.charAt(i));
        if (j >= 0) {
            processString = processString.substring(j + 1);
        } else return false;
    }
    return true;
}

function check(string) {
    return /^[stph*fldkwrbgzaoeu/-]+$/i.test(string);
}

function updatePersonCodeSteno() {
    let bool = false;
    let code = $("#steno-code").val().toUpperCase();
    let value = $("#steno-value").val();
    let error_code = $(".code-error-steno");
    let error_value = $(".value-error-steno");
    error_value.css({"display": "none"});
    error_code.css({"display": "none"});
    if (code === '') {
        error_code.text(" *Không được bỏ trống dữ liệu");
        error_code.css({"display": "contents"});
    } else if (value === '') {
        error_value.text(" *Không được bỏ trống dữ liệu");
        error_value.css({"display": "contents"});
    } else {
        if (check(code)) {
            let res = code.split("/");
            $.each(res, function (key, value) {
                if (value.length > 10) {
                    error_code.text(" *Mỗi tổ hợp không được dài quá 10 ký tự");
                    error_code.css({"display": "contents"});
                    return false;
                } else {
                    if (!isPrefix(value)) {
                        let result = checkIfNotPrefix(value);
                        if (!result) {
                            bool = false;

                            error_code.text(" *Tổ hợp không đúng thứ tự");
                            error_code.css({"display": "contents"});

                            return bool;
                        } else {
                            bool = true;
                        }
                    } else {
                        let result = checkIfPrefix(value);
                        if (!result) {
                            bool = false;
                            error_code.text(" *Tổ hợp không đúng thứ tự");
                            error_code.css({"display": "contents"});
                            return bool;
                        } else {
                            bool = true;
                        }
                    }
                }
            });
        } else {
            error_code.text(" *Tồn tại ký tự không được cho phép!");
            error_code.css({"display": "contents"});
        }
    }


    if (value !== '') {
        error_value.css({"display": "none"});
    }

    if (bool) {
        if (code !== '') {

            if ($(".modal-code").text() === code && $(".modal-steno-value").text() === value) {
                error_code.text(" *Không có thay đổi gì, vu lòng kiểm tra lại!");
                error_code.css({"display": "contents"});
            } else {
                error_code.css({"display": "none"});
                let data_code = $(".modal-value").text();
                $.ajax({
                    url: "/query.html?type=edit",
                    type: "post",
                    data: {
                        stenoCode: code,
                        value: value,
                        dataEdit: data_code
                    },
                    success: function () {
                        $('#edit-person-dict').modal('toggle');
                        location.reload(true);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.log(textStatus, errorThrown);
                    }
                });
            }
        }
    }

}






