function fnOnclickDownload() {
    $("#myModal").modal('show');
    let content = '';
    if ($(".data-content-editor").length > 0) {
        content = $('#data-content').froalaEditor('html.get');
    } else {
        content = $("#txtEditor").val();
    }

    $("#data-convert").val(content);
}

var form_validation = function () {
    var e = function () {
        $.validator.addMethod("regexp", function (value, element) {
            return this.optional(element) || !/[\\/"?*<>|:]/.test(value);
        }, 'Kiểm tra lại tên');

        $(".form-valide").validate({
            ignore: [],
            errorClass: "invalid-feedback animated fadeInDown",
            errorElement: "div",
            errorPlacement: function (e, a) {
                $(a).parents(".form-group > div").append(e)
            },
            highlight: function (e) {
                $(e).closest(".form-group").removeClass("is-invalid").addClass("is-invalid")
            },
            success: function (e) {
                $(e).closest(".form-group").removeClass("is-invalid"), $(e).remove()
            },
            rules: {
                "name-file": {
                    required: true,
                    regexp: true
                }
            },
            messages: {
                "name-file": "Tên nhập vào không hợp lệ"
            }
        })
    };
    return {
        init: function () {
            e(), $(".js-select2").on("change", function () {
                $(this).valid()
            })
        }
    }
}();
$(function () {
    form_validation.init();
});

$(function () {
    $('#data-content').froalaEditor({
        height: 300
    });
});