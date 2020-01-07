$(".click-noti").click(function () {
    let id = this.id;
    let link = $(this).attr("path");
    let title = $(this).attr("title");
    let content = $(this).attr("content");
    $.ajax({
        url: "/ajax-notification.html",
        type: "get",
        data: {
            type: "clear",
            id: id,
            link: link
        },
        success: function (data) {
            console.log("Link chuyen toi: " + data);
            swal({
                    title: title,
                    text: content,
                    type: "info",
                    confirmButtonColor: "#00a8dd",
                    confirmButtonText: "OK",
                    closeOnConfirm: false
                },
                function(){
                    $(location).attr('href', data);
                });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    });
});