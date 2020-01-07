function getDate(dateString) {
    // dateString = '27/12/2019 08:00 AM';
    let date = dateString.split(" ")[0];
    let time = dateString.split(" ")[1];
    let a = dateString.split(" ")[2];
    let ddmmyyyy = date.split("/");

    let hh = parseInt(time.split(":")[0]);
    let mm = time.split(":")[1];

    let datetime = ddmmyyyy[2] + "-" + ddmmyyyy[1] + "-" + ddmmyyyy[0] + "T";
    if(a === 'PM'){
        if(hh != 12){
            hh += 12;
        }
        datetime += hh +":" + mm + ":00Z";
    }else {
        if(hh == 12){
            datetime += "00:" + mm + ":00Z";
        }else {
            datetime += time + ":00Z";
        }
    }
    // console.log(datetime);
    return datetime;

}

var form_validation = function() {
    var e = function() {
        $.validator.addMethod("timeEndAfter", function (value, element) {
            let status = true;
            let inputTimeStart = $('#search-time-start').val();
            if(inputTimeStart !== ''){
                let timeStart = new Date(getDate(inputTimeStart));
                let timeEnd = new Date(getDate(value));
                if(timeStart > timeEnd){
                    status = false;
                }
            }
            return this.optional(element) || status;
        }, "Thời gian kết thúc phải sau thời gian bắt đầu!");

        $.validator.addMethod("timeStartBefore", function (value, element) {
            let status = true;
            let inputTimeEnd = $('#search-time-end').val();
            if(inputTimeEnd !== ''){
                let timeStart = new Date(getDate(value));
                let timeEnd = new Date(getDate(inputTimeEnd));
                if(timeStart > timeEnd){
                    status = false;
                }
            }
            return this.optional(element) || status;
        }, "Thời gian bắt đầu phải trước thời gian kết thúc!");

        $(".form-validate-search-time").validate({
            ignore: [],
            errorClass: "invalid-feedback animated fadeInDown",
            errorElement: "div",
            errorPlacement: function(e, a) {
                $(a).parents(".form-group > div").append(e)
            },
            highlight: function(e) {
                $(e).closest(".form-group").removeClass("is-invalid").addClass("is-invalid")
            },
            success: function(e) {
                $(e).closest(".form-group").removeClass("is-invalid"), $(e).remove()
            },
            rules: {
                "timeStart": {
                    timeStartBefore: true
                },
                "timeEnd": {
                    timeEndAfter: true
                }
            },
            messages: {
                "timeStart": "Thời gian bắt đầu phải trước thời gian kết thúc!",
                "timeEnd": "Thời gian kết thúc phải sau thời gian bắt đầu!"

            }
        })
    }
    return {
        init: function() {
            e(), $(".js-select2").on("change", function() {
                $(this).valid()
            })
        }
    }
}();
$(function() {
    form_validation.init()
});