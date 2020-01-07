function fnSave() {
    let objKey = [];
    let objValue = [];


    $(".key").each(function () {
        objKey.push($(this).text());
    });

    $(".value").each(function () {
        objValue.push($(this).text());
    });

    let data = '{';
    for (let i = 0; i < objKey.length; i++) {
        if (i === objKey.length - 1) {
            data += '"' + objKey[i] + '" : "' + objValue[i] + '"';
        } else {
            data += '"' + objKey[i] + '" : "' + objValue[i] + '",';
        }
    }
    data += '}';
    $.ajax({
        url: "/steno-setting-oder.html",
        type: "post",
        data: {
            data: data
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
    })
}