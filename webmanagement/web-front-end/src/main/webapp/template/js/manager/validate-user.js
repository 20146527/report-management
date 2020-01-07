
var form_validation = function() {
    var e = function() {
        jQuery(".form-valide").validate({
            ignore: [],
            errorClass: "invalid-feedback animated fadeInDown",
            errorElement: "div",
            errorPlacement: function(e, a) {
                jQuery(a).parents(".form-group > div").append(e)
            },
            highlight: function(e) {
                jQuery(e).closest(".form-group").removeClass("is-invalid").addClass("is-invalid")
            },
            success: function(e) {
                jQuery(e).closest(".form-group").removeClass("is-invalid"), jQuery(e).remove()
            },
            rules: {
                "pojo.userName": {
                    required: !0,
                },
                "pojo.passWord": {
                    required: !0,
                    minlength: 5
                },
                "pojo.fullName": {
                    required: !0
                },
                "pojo.email": {
                    required: !0,
                    email: !0
                },
                "pojo.gender": {
                    required: !0
                }
            },
            messages: {
                "pojo.userName": {
                    required: "Tên người dùng không được bỏ trống!"
                },
                "pojo.passWord": {
                    required: "Không bỏ trống mật khẩu!",
                    minlength: "Đặt mật khẩu dài hơn 6 ký tự!"
                },
                "pojo.fullName": "Không được bỏ trống!",
                "pojo.email": {
                    required: "Không bỏ trống email!",
                    email: "Nhập đúng định dạng email!"
                },
                "pojo.gender": "Không được bỏ trống!",

            }
        })
    }
    return {
        init: function() {
            e(), jQuery(".js-select2").on("change", function() {
                jQuery(this).valid()
            })
        }
    }
}();
jQuery(function() {
    form_validation.init()
});
