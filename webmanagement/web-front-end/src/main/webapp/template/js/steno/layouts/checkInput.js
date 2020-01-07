let strFist = 'AWPRSHKOST';
let strTwo = 'DPRBGUELSZTF';
let bol = true;


function checkValue(event) {
    bol = true;
    let value = event.val();
    let id = event.attr('id');
    let label_id = id.split('-')[0] + "-label";
    let label = $("#" + label_id);
    if (value.length === 1) {
        if (value !== '*') {
            setValueLabel(label, '* Ký tự không hợp lệ');
        }
    } else if (value.length === 2) {
        let first = value.substring(0, 1);
        let two = value.substring(1, 2);
        if (first === '-') {
            if (strTwo.indexOf(two) === -1) {
                setValueLabel(label, '* Ký tự không hợp lệ');
            }
        } else {
            if (two === '-') {
                if (strFist.indexOf(first) === -1) {
                    setValueLabel(label, '* Ký tự không hợp lệ');
                }
            } else {
                setValueLabel(label, '* Ký tự không hợp lệ');
            }
        }
    } else if (value.length === 0) {
        setValueLabel(label, 'Không được bỏ trống');
    } else {
        setValueLabel(label, '* Ký tự không hợp lệ');
    }

    if (bol) {
        label.css({"display": "none"});
        label.removeClass("error-label");
    }
}

function setValueLabel(label, s) {
    bol = false;
    label.css({"display": "block"});
    label.text(s);
    label.addClass("error-label");
}

function updateLayout() {
    let error = $(".error-label").length;
    if (error > 0) {
        toastr.error('Có lỗi xảy ra! Vui lòng kiểm tra lại!', 'Thông báo', {
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
        let items = '{';
        $('#steno-content tbody tr').each(function () {
            items += '"' + $(this).find('td.keyQwerty').text() + '" : "' + $(this).find('td.form-group').find('input').val() + '",';
        });
        items = items.substring(0, items.length - 1) + "}";

        $.ajax({
            url: "/manager-layout.html",
            type: "post",
            data: {
                content: items
            },
            success: function () {
                swal({
                        title: "Thông báo",
                        text: "Dữ liệu đã được cập nhật!\n Để ứng dụng hoạt động đúng vui lòng xóa session và cooki của trang web!",
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

    }
}