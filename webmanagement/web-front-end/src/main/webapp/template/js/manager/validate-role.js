var form_validation = function () {
    var e = function () {
        jQuery(".form-valide").validate({
            ignore: [],
            errorClass: "invalid-feedback animated fadeInDown",
            errorElement: "div",
            errorPlacement: function (e, a) {
                jQuery(a).parents(".form-group > div").append(e)
            },
            highlight: function (e) {
                jQuery(e).closest(".form-group").removeClass("is-invalid").addClass("is-invalid")
            },
            success: function (e) {
                jQuery(e).closest(".form-group").removeClass("is-invalid"), jQuery(e).remove()
            },
            rules: {
                "roleName": {
                    required: !0
                },
                "roleDescription": {
                    required: !0
                }
            },
            messages: {
                "roleName": "Không được bỏ trống tên quyền!",
                "roleDescription": "Cần nhập thông tin mô tả!"
            }
        })
    };
    return {
        init: function () {
            e(), jQuery(".js-select2").on("change", function () {
                jQuery(this).valid()
            })
        }
    }
}();
jQuery(function () {
    form_validation.init()
});