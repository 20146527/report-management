
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
                "pojo.name": {
                    required: !0
                },
                "pojo.brand": {
                    required: !0
                },
                "pojo.status": {
                    required: !0
                },
                "pojo.dayStart": {
                    required: !0
                }
            },
            messages: {
                "pojo.name": "Không bỏ trống tên thiết bị!",
                "pojo.brand": "Không bỏ trống tên hãng!",
                "pojo.status": "Cần lựa chọn trạng thái!",
                "pojo.dayStart": "Nhập vào ngày bắt đầu sử dụng!"
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